package controller.dto;

import domain.AttendanceStatus;
import domain.Penalty;
import java.util.List;
import java.util.Map;

public record AttendanceResultDto(
        String name,
        int attendanceCount,
        int absenceCount,
        int perceptionCount,
        String penaltyName
) {
    public static AttendanceResultDto of(String name, List<AttendanceStatus> attendanceStatuses) {
        Map<AttendanceStatus, Integer> attendanceStatusCount = AttendanceStatus.calculateAttendanceStatusCount(
                attendanceStatuses);
        return new AttendanceResultDto(
                name,
                attendanceStatusCount.get(AttendanceStatus.ATTENDANCE),
                attendanceStatusCount.get(AttendanceStatus.ABSENCE),
                attendanceStatusCount.get(AttendanceStatus.PERCEPTION),
                Penalty.calculatePenalty(attendanceStatuses).getName()
        );
    }

}
