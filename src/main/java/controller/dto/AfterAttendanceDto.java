package controller.dto;

import domain.AttendanceDateTime;
import domain.AttendanceStatus;
import domain.CrewAttendance;

public record AfterAttendanceDto(
        AttendanceDateTime attendanceDateTime,
        AttendanceStatus attendanceStatus
) {

    public static AfterAttendanceDto of(AttendanceDateTime updatedDateTime, CrewAttendance crewAttendance) {
        AttendanceStatus attendanceStatus = crewAttendance.calculateAttendanceStatus(updatedDateTime.getDate());
        return new AfterAttendanceDto(updatedDateTime, attendanceStatus);
    }
}
