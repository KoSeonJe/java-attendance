package controller.dto;

import domain.Attendance;
import domain.AttendanceRecords;
import domain.AttendanceStatus;
import domain.AttendanceStatusCounter;
import domain.Penalty;
import java.util.List;

public record CrewAttendanceRecord(
        List<AttendanceResult> attendanceResults,
        int attendanceCount,
        int lateCount,
        int absenceCount,
        Penalty penalty
) {
    public static CrewAttendanceRecord of(List<Attendance> attendances, AttendanceStatusCounter counter, Penalty penalty) {
        List<AttendanceResult> attendanceResults = attendances.stream()
                .map(AttendanceResult::from)
                .toList();
        int attendanceCount = counter.retrieveAttendanceStatusCount(AttendanceStatus.ATTENDANCE);
        int lateCount = counter.retrieveAttendanceStatusCount(AttendanceStatus.LATE);
        int absenceCount = counter.retrieveAttendanceStatusCount(AttendanceStatus.ABSENCE);
        return new CrewAttendanceRecord(attendanceResults, attendanceCount, lateCount, absenceCount, penalty);
    }
}
