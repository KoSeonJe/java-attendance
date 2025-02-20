package view;

import domain.AttendanceStatus;
import domain.Date;
import domain.DateTime;
import domain.Time;
import domain.WorkDay;

public class OutputView {
    public void printArriveResult(DateTime dateTime, AttendanceStatus attendanceStatus) {
        Date date = dateTime.getDate();
        WorkDay workDay = date.getWorkDay();
        Time time = dateTime.getTime();

        System.out.printf("%s월 %s일 %s요일 %s:%s (%s)\n", date.getMonthValue(),
                date.getDayValue(), workDay.getDayOfWeekKorean(),
                convertTime(time.getHour()), convertTime(time.getMinute()), attendanceStatus.getName());
    }

    public void printUpdateResult(DateTime beforeDateTime, AttendanceStatus beforeAttendanceStatus,
                                  DateTime afterDateTime, AttendanceStatus afterAttendanceStatus) {
        Date date = beforeDateTime.getDate();
        WorkDay workDay = date.getWorkDay();
        Time beforeTime = beforeDateTime.getTime();
        Time afterTime = afterDateTime.getTime();
        System.out.printf("%s월 %s일 %s요일 %s:%s (%s) -> %s:%s (%s) 수정 완료!\n",
                date.getMonthValue(), date.getDayValue(), workDay.getDayOfWeekKorean(),
                convertTime(beforeTime.getHour()),
                convertTime(beforeTime.getMinute()), beforeAttendanceStatus.getName(),
                convertTime(afterTime.getHour()),
                convertTime(afterTime.getMinute()), afterAttendanceStatus.getName());
    }

    private String convertTime(int time) {
        String before = String.valueOf(time);

        if (before.length() < 2) {
            return "0" + before;
        }

        return before;
    }
}
