package util;

import domain.Attendance;
import domain.Crew;
import domain.CrewRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void initialize(String filePath) {
        loadCrews(filePath);
    }

    private void loadCrews(String filePath) {
        List<String> lines = FileDataLoader.loadLines(filePath);
        lines.stream().skip(1).forEach(this::processEntry);
    }

    private void processEntry(String line) {
        String[] items = splitItems(line);
        validateSize(items);

        String name = items[0];
        LocalDateTime dateTime = parseToDate(items[1]);

        registerOrUpdateCrew(name, dateTime);
    }

    private void registerOrUpdateCrew(String name, LocalDateTime dateTime) {
        // ✅ 기존 Crew가 있으면 출석 추가, 없으면 새로 생성
        CrewRepository crewRepository = CrewRepository.getInstance();
        Crew crew = crewRepository.findOptionalByName(name)
                .orElse(null);

        if (crew == null) {
            crewRepository.save(new Crew(name, new Attendance(new ArrayList<>())));
            return;
        }
        crew.addAttendance(dateTime);
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
