package controller.dto;

import domain.AttendanceStatus;
import domain.CrewAttendance;
import java.util.Map;

public record AttendanceResultDto(
        String name,
        int attendanceCount,
        int absenceCount,
        int perceptionCount,
        String penaltyName
) {

    public static AttendanceResultDto of(CrewAttendance crewAttendance) {
        Map<AttendanceStatus, Integer> attendanceStatusCount = crewAttendance.retrieveAttendanceStatusCount();
        return new AttendanceResultDto(
                crewAttendance.getCrew().getName(),
                attendanceStatusCount.get(AttendanceStatus.ATTENDANCE),
                attendanceStatusCount.get(AttendanceStatus.ABSENCE),
                attendanceStatusCount.get(AttendanceStatus.PERCEPTION),
                crewAttendance.retrievePenalty().getName()
        );
    }
}
