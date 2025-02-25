package controller.dto;

import domain.AttendanceDateTime;
import domain.AttendanceStatus;
import domain.CrewAttendance;

public record BeforeAttendanceDto(
        AttendanceDateTime attendanceDateTime,
        AttendanceStatus attendanceStatus
) {

    public static BeforeAttendanceDto of(AttendanceDateTime attendanceDateTime, CrewAttendance crewAttendance) {
        AttendanceDateTime beforeAttendanceDateTime = crewAttendance.retrieveDateTime(attendanceDateTime.getDate());
        AttendanceStatus beforeAttendanceStatus = crewAttendance.retrieveAttendanceStatus(attendanceDateTime.getDate());
        return new BeforeAttendanceDto(beforeAttendanceDateTime, beforeAttendanceStatus);
    }
}
