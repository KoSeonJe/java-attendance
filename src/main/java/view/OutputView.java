package view;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class OutputView {
    public void printArriveResult() {
    }

    public void printArriveResult(LocalDateTime dateTime, DayOfWeek dayOfWeek, String name) {
        //12월 13일 금요일 09:59 (출석)
        System.out.printf("%s %s %s %s:%s (%s)\n", dateTime.getMonthValue(), dateTime.getDayOfMonth(), dayOfWeek,
                dateTime.getHour(), dateTime.getMinute(), name);
    }
}
