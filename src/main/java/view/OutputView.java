package view;

import domain.AttendanceTime;
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
                "%d월 %d일 %s %s (%s)\n",
                date.getMonthValue(),
                date.getDayOfMonth(),
                dayOfWeekKorean,
                time,
                attendanceStatusMessage
                );
    }
}
