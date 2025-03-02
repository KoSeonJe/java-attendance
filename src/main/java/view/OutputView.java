package view;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public final class OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printAttendanceResult(LocalDate date, String time, String attendanceStatusMessage) {
        String dayOfWeekKorean = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        System.out.printf(
                "%d월 %s일 %s %s (%s)\n",
                date.getMonthValue(),
                parseFullDay(date.getDayOfMonth()),
                dayOfWeekKorean,
                time,
                attendanceStatusMessage
                );
    }

    public void printUpdateResult(
            LocalDate date,
            String beforeTime,
            String beforeAttendanceStatus,
            String afterTime,
            String afterAttendanceStatus
    ) {
        String dayOfWeekKorean = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);

        System.out.printf("%d월 %s일 %s %s (%s) -> %s (%s) 수정 완료!\n",
                date.getMonthValue(),
                parseFullDay(date.getDayOfMonth()),
                dayOfWeekKorean,
                beforeTime,
                beforeAttendanceStatus,
                afterTime,
                afterAttendanceStatus
                );
    }

    private String parseFullDay(int part) {
        if (part < 10) {
            return "0" + part;
        }
        return "" + part;
    }
}
