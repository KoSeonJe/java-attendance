package controller.dto;

import domain.AttendanceStatus;
import domain.CrewAttendance;
import java.util.Map;

public record PenaltyCrewDto(
        String name,
        int absenceCount,
        int perceptionCount,
        String penaltyName
) {

    public static PenaltyCrewDto from(CrewAttendance crewAttendance) {
        Map<AttendanceStatus, Integer> attendanceStatusCount = crewAttendance.retrieveAttendanceStatusCount();

        return new PenaltyCrewDto(
                crewAttendance.getCrew().getName(),
                attendanceStatusCount.get(AttendanceStatus.ABSENCE),
                attendanceStatusCount.get(AttendanceStatus.PERCEPTION),
                crewAttendance.retrievePenalty().getName()
        );
    }
}
