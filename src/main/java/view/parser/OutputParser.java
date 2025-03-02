package view.parser;

import domain.AttendanceStatus;
import domain.Penalty;
import java.util.Map;

public class OutputParser {

    private static final Map<AttendanceStatus, String> ATTENDANCE_STATUS_MESSAGE = Map.of(
            AttendanceStatus.ATTENDANCE, "출석",
            AttendanceStatus.LATE, "지각",
            AttendanceStatus.ABSENCE, "결석"
    );

    private static final Map<Penalty, String> PENALTY_STATUS_MESSAGE = Map.of(
            Penalty.WARNING, "경고",
            Penalty.INTERVIEW, "면담",
            Penalty.WEEDING, "제적",
            Penalty.NONE, "해당없음"
    );

    public String parseAttendanceStatusToMessage(AttendanceStatus attendanceStatus) {
        return ATTENDANCE_STATUS_MESSAGE.get(attendanceStatus);
    }

    public String parsePenaltyToMessage(Penalty penalty) {
        return PENALTY_STATUS_MESSAGE.get(penalty);
    }
}
