package view;

import java.time.LocalDateTime;

public class OutputView {
    public void printArriveResult(LocalDateTime dateTime, String dayOfWeek, String name) {
        System.out.printf("%s월 %s일 %s요일 %s:%s (%s)\n", dateTime.getMonthValue(), dateTime.getDayOfMonth(), dayOfWeek,
                convertTime(dateTime.getHour()), convertTime(dateTime.getMinute()), name);
    }

    private String convertTime(int time) {
        String before = String.valueOf(time);

        if (before.length() < 2) {
            return "0" + before;
        }

        return before;
    }
}
