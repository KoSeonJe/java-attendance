package controller.dto;

import domain.AttendanceStatus;
import domain.AttendanceDateTime;

public record AttendanceRecodeDto(
        AttendanceDateTime attendanceDateTime,
        String attendanceStatusName
) {
    public static AttendanceRecodeDto from(AttendanceDateTime attendanceDateTime) {
        return new AttendanceRecodeDto(attendanceDateTime, AttendanceStatus.findByDateTime(attendanceDateTime).getName());
    }
}
