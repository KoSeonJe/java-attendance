package controller.dto;

import domain.AttendanceStatus;
import domain.CrewAttendance;
import domain.Penalty;
import java.util.List;
import java.util.Map;

public record PenaltyCrewDto(
        String name,
        int absenceCount,
        int perceptionCount,
        String penaltyName
) {

    public static PenaltyCrewDto from(CrewAttendance crewAttendance) {
        List<AttendanceStatus> attendanceStatuses = crewAttendance.calculateAttendanceStatuses();
        Map<AttendanceStatus, Integer> attendanceStatusCount = AttendanceStatus.calculateAttendanceStatusCount(attendanceStatuses);

        return new PenaltyCrewDto(
                crewAttendance.getCrew().getName(),
                attendanceStatusCount.get(AttendanceStatus.ABSENCE),
                attendanceStatusCount.get(AttendanceStatus.PERCEPTION),
                Penalty.calculatePenalty(attendanceStatuses).getName()
        );
    }
}
