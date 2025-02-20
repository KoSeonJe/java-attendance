package util;

import domain.Attendance;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.DateTime;
import domain.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataInitializer {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final LocalDate START_DATE = LocalDate.of(2024, 12, 2);

    public void initialize(LocalDateTime todayLocalDateTime, String filePath) {
        validateDecember2024(todayLocalDateTime);
        Set<String> crewNames = extractCrewNames(filePath);
        Map<Date, Time> initialDateTimes = initializeAttendanceMap(todayLocalDateTime);
        storeCrewData(crewNames, initialDateTimes, filePath, todayLocalDateTime);
    }

    private void validateDecember2024(LocalDateTime todayLocalDateTime) {
        if (todayLocalDateTime.getYear() != 2024 || todayLocalDateTime.getMonthValue() != 12) {
            throw new IllegalArgumentException("입력된 날짜는 2024년 12월이어야 합니다");
        }
    }

    private Set<String> extractCrewNames(String filePath) {
        List<String> lines = FileDataLoader.loadLines(filePath);
        Set<String> crewNames = new HashSet<>();
        lines.stream().skip(1)
                .map(line -> splitItems(line)[0])
                .forEach(crewNames::add);
        return crewNames;
    }

    private Map<Date, Time> initializeAttendanceMap(LocalDateTime todayLocalDateTime) {
        Map<Date, Time> dateTimes = new HashMap<>();
        LocalDate currentDate = START_DATE;

        while (!currentDate.isAfter(todayLocalDateTime.toLocalDate())) {
            Date date = new Date(currentDate);
            if (!date.isHoliday()) {
                dateTimes.put(date, new Time(null, null));
            }
            currentDate = currentDate.plusDays(1);
        }

        return dateTimes;
    }

    private void storeCrewData(Set<String> crewNames, Map<Date, Time> initialDateTimes,
                               String filePath, LocalDateTime todayLocalDateTime) {
        CrewAttendanceRepository crewAttendanceRepository = CrewAttendanceRepository.getInstance();

        for (String name : crewNames) {
            Crew crew = new Crew(name);
            CrewAttendance crewAttendance = new CrewAttendance(crew, new Attendance(new HashMap<>(initialDateTimes)));

            loadAndApplyAttendance(crewAttendance, filePath, todayLocalDateTime);
            crewAttendanceRepository.save(crewAttendance);
        }
    }

    private void loadAndApplyAttendance(CrewAttendance crewAttendance, String filePath,
                                        LocalDateTime todayLocalDateTime) {
        List<String> lines = FileDataLoader.loadLines(filePath);

        for (String line : lines.subList(1, lines.size())) {
            String[] items = splitItems(line);
            validateSize(items);

            String name = items[0];
            LocalDateTime localDateTime = parseToDate(items[1]);
            DateTime dateTime = DateTime.of(localDateTime);

            if (!crewAttendance.getCrew().getName().equals(name)) {
                continue;
            }

            if (!dateTime.getDate().isAfter(new Date(todayLocalDateTime.toLocalDate())) &&
                    !dateTime.getDate().isHoliday()) {
                crewAttendance.addAttendance(dateTime);
            }
        }
    }

    private String[] splitItems(final String input) {
        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .toArray(String[]::new);
    }

    private void validateSize(final String[] inputs) {
        if (inputs.length != 2) {
            throw new IllegalArgumentException("잘못된 데이터 형식: " + Arrays.toString(inputs));
        }
    }

    private LocalDateTime parseToDate(final String dateString) {
        try {
            return LocalDateTime.parse(dateString, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 날짜 형식: " + dateString);
        }
    }
}
