package view;

import domain.AttendanceResult;
import domain.AttendanceResults;
import domain.AttendanceStatus;
import domain.Day;
import domain.Penalty;
import java.time.LocalDateTime;
import java.util.List;

public class OutputView {
    public void printArriveResult(LocalDateTime dateTime, String dayOfWeek, String name) {
        System.out.printf("%s월 %s일 %s요일 %s:%s (%s)\n", dateTime.getMonthValue(), dateTime.getDayOfMonth(), dayOfWeek,
                convertTime(dateTime.getHour()), convertTime(dateTime.getMinute()), name);
    }

    public void printUpdateResult(String dayOfWeek, LocalDateTime beforeTime, AttendanceStatus beforeAttendanceStatus,
                                  LocalDateTime updateTime, AttendanceStatus updateAttendanceStatus) {
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

    public void printAttendanceResults(AttendanceResults attendanceResults) {
        List<AttendanceResult> sortedResults = attendanceResults.getSortedAttendanceResults();

        for (int i = 0; i < sortedResults.size(); i++) {
            AttendanceResult current = sortedResults.get(i);

            if (i > 0) {
                AttendanceResult previous = sortedResults.get(i - 1);
                printAbsentDays(previous.getDayOfMonth(), current.getDayOfMonth(), current.getMonthValue());
            }

            printAttendanceResult(current);
        }

        System.out.printf("출석: %d\n", attendanceResults.calculateAttendanceCount());
        System.out.printf("지각: %d\n", attendanceResults.calculatePerceptionCount());
        System.out.printf("결석: %d\n", attendanceResults.calculateAbsentCount());

        Penalty penalty = attendanceResults.calculatePenalty();
        if (penalty != null) {
            System.out.printf("%s 대상자입니다.\n", penalty.getName());
        }
    }

    private void printAbsentDays(int previousDay, int currentDay, int month) {
        for (int missingDay = previousDay + 1; missingDay < currentDay; missingDay++) {
            LocalDateTime missingDate = LocalDateTime.of(2024, month, missingDay, 0, 0);
            Day day = Day.findByDayOfWeek(missingDate.getDayOfWeek());

            if (!day.equals(Day.SATURDAY) && !day.equals(Day.SUNDAY)) {
                System.out.printf("%d월 %02d일 %s요일 --:-- (결석)\n",
                        month, missingDay, day.getDayOfWeekKorean());
            }
        }
    }

    private void printAttendanceResult(AttendanceResult result) {
        String hour = convertTime(result.getHour());
        String minute = convertTime(result.getMinute());
        String timeString = hour + ":" + minute;

        System.out.printf("%d월 %02d일 %s요일 %s (%s)\n",
                result.getMonthValue(),
                result.getDayOfMonth(),
                result.calculateDayOfWeekKorean(),
                timeString,
                result.calculateAttendanceStatus().getName());
    }

}
