package view.parser;

import domain.AttendanceStatus;
import java.util.Map;

public class OutputParser {

    private static final Map<AttendanceStatus, String> ATTENDANCE_STATUS_MESSAGE = Map.of(
            AttendanceStatus.ATTENDANCE, "출석",
            AttendanceStatus.LATE, "지각",
            AttendanceStatus.ABSENCE, "결석"
    );

    public String parseAttendanceStatusToMessage(AttendanceStatus attendanceStatus) {
        return ATTENDANCE_STATUS_MESSAGE.get(attendanceStatus);
    }
}
