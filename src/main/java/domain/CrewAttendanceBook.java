package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record CrewAttendanceBook(
        List<CrewAttendance> crewAttendances
) {

    public void createCrewAttendance(String crewName, Attendance attendance) {
        if (existCrew(crewName)) {
            throw new IllegalArgumentException("[ERROR] 이미 등록된 크루입니다");
        }
        CrewAttendance crewAttendance = CrewAttendance.create(crewName, AttendanceRecords.create(List.of(attendance)));
        crewAttendances.add(crewAttendance);
    }

    public boolean existCrew(String crewName) {
        return crewAttendances.stream()
                .anyMatch(crewAttendance -> crewAttendance.equalName(crewName));
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

    public static CrewAttendanceBook createEmpty() {
        return new CrewAttendanceBook(new ArrayList<>());
    }
}
