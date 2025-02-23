package util;

import domain.Attendance;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.AttendanceDateTime;
import domain.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataInitializer {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDate REQUIREMENT_START_DATE = LocalDate.of(2024, 12, 2);
    private static final int FILE_HEADER_INDEX = 1;
    private static final int CREW_NAME_INDEX = 0;

    private final CrewAttendanceRepository crewAttendanceRepository;
    private final ApplicationTime applicationTime;

    public DataInitializer(ApplicationTime applicationTime, CrewAttendanceRepository crewAttendanceRepository) {
        this.crewAttendanceRepository = crewAttendanceRepository;
        this.applicationTime = applicationTime;
    }

    public void initialize(String filePath) {
        List<String> lines = FileDataLoader.loadLines(filePath);

        Set<String> crewNames = extractCrewNames(lines);
        Map<Date, Time> initialDateTimes = createInitialAttendanceMap();
        registerCrewData(crewNames, initialDateTimes, lines);
    }

    private Set<String> extractCrewNames(List<String> lines) {
        return lines.stream()
                .skip(FILE_HEADER_INDEX)
                .map(line -> splitItems(line)[CREW_NAME_INDEX])
                .collect(Collectors.toSet());
    }

    private Map<Date, Time> createInitialAttendanceMap() {
        Map<Date, Time> dateTimes = new HashMap<>();
        LocalDate currentDate = REQUIREMENT_START_DATE;

        while (!currentDate.isAfter(applicationTime.getApplicationTime().toLocalDate())) {
            addNonHolidayDate(dateTimes, currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dateTimes;
    }

    private void addNonHolidayDate(Map<Date, Time> dateTimes, LocalDate currentDate) {
        Date date = new Date(currentDate);
        if (!date.isHoliday()) {
            dateTimes.put(date, null);
        }
    }

    private void registerCrewData(
            Set<String> crewNames,
            Map<Date, Time> initialDateTimes,
            List<String> lines
    ) {
        crewNames.forEach(name -> {
            CrewAttendance crewAttendance = new CrewAttendance(new Crew(name), new Attendance(initialDateTimes));

            loadAndApplyAttendance(crewAttendance, lines);
            crewAttendanceRepository.save(crewAttendance);
        });
    }

    private void loadAndApplyAttendance(
            CrewAttendance crewAttendance,
            List<String> lines
    ) {
        lines.stream()
                .skip(FILE_HEADER_INDEX)
                .map(this::parseAttendanceData)
                .filter(data -> isValidAttendance(data, crewAttendance))
                .forEach(data -> crewAttendance.addAttendance(data.attendanceDateTime));
    }

    private AttendanceData parseAttendanceData(String line) {
        String[] items = splitItems(line);
        validateSize(items);
        return new AttendanceData(items[0], parseToDate(items[1]));
    }

    private boolean isValidAttendance(
            AttendanceData data,
            CrewAttendance crewAttendance
    ) {
        return crewAttendance.getCrew().getName().equals(data.name) &&
                !data.attendanceDateTime.getDate().isAfter(new Date(applicationTime.getApplicationTime().toLocalDate())) &&
                !data.attendanceDateTime.getDate().isHoliday();
    }

    private String[] splitItems(String input) {
        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .toArray(String[]::new);
    }

    private void validateSize(String[] inputs) {
        if (inputs.length != 2) {
            throw new IllegalArgumentException("잘못된 데이터 형식: " + Arrays.toString(inputs));
        }
    }

    private LocalDateTime parseToDate(String dateString) {
        try {
            return LocalDateTime.parse(dateString, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜 형식: " + dateString);
        }
    }

    private static class AttendanceData {
        private final String name;
        private final AttendanceDateTime attendanceDateTime;

        AttendanceData(String name, LocalDateTime localDateTime) {
            this.name = name;
            this.attendanceDateTime = AttendanceDateTime.of(localDateTime);
        }
    }
}
