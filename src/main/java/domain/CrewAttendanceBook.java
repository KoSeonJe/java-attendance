package domain;

import java.time.LocalDate;
import java.util.List;

public class CrewAttendanceBook {

    private final List<CrewAttendance> crewAttendances;

    private CrewAttendanceBook(List<CrewAttendance> crewAttendances) {
        this.crewAttendances = crewAttendances;
    }

    public AttendanceRecords retrieveAttendanceRecordsByName(String crewName) {
        return crewAttendances.stream()
                .filter(crewAttendance -> crewAttendance.equalName(crewName))
                .map(CrewAttendance::getAttendanceRecords)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 등록되지 않은 크루입니다."));
    }

    public LocalDate retrieveOldestDayInBook() {
        return crewAttendances.stream()
                .map(crewAttendance -> crewAttendance.getAttendanceRecords().retrieveOldestDate())
                .min(LocalDate::compareTo)
                .orElseThrow();
    }

    public static CrewAttendanceBook create(List<CrewAttendance> crewAttendances) {
        return new CrewAttendanceBook(crewAttendances);
    }
}
