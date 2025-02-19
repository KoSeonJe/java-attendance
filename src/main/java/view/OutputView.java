package view;

import domain.AttendanceStatus;
import java.time.LocalDateTime;

public class OutputView {
    public void printArriveResult(LocalDateTime dateTime, String dayOfWeek, String name) {
        System.out.printf("%s월 %s일 %s요일 %s:%s (%s)\n", dateTime.getMonthValue(), dateTime.getDayOfMonth(), dayOfWeek,
                convertTime(dateTime.getHour()), convertTime(dateTime.getMinute()), name);
    }

    public void printUpdateResult(String dayOfWeek, LocalDateTime beforeTime, AttendanceStatus beforeAttendanceStatus,
                                  LocalDateTime updateTime, AttendanceStatus updateAttendanceStatus) {
        //12월 03일 화요일 10:07 (지각) -> 09:58 (출석) 수정 완료!
        System.out.printf("%s월 %s일 %s요일 %s:%s (%s) -> %s:%s (%s) 수정 완료!\n", beforeTime.getMonthValue(),
                beforeTime.getDayOfMonth(), dayOfWeek, convertTime(beforeTime.getHour()),
                convertTime(beforeTime.getMinute()), beforeAttendanceStatus.getName(),
                convertTime(updateTime.getHour()),
                convertTime(updateTime.getMinute()), updateAttendanceStatus.getName());
    }

    private String convertTime(int time) {
        String before = String.valueOf(time);

        if (before.length() < 2) {
            return "0" + before;
        }

        return before;
    }
}
