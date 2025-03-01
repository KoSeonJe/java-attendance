package util;

import domain.Attendance;

public record AttendanceInfo(
        String crewName,
        Attendance attendance
) {

}
