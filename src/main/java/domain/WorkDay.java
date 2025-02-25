package domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public enum WorkDay {
    MONDAY("월", false, 13, 18),
    TUESDAY("화", false, 10, 18),
    WEDNESDAY("수", false, 10, 18),
    THURSDAY("목", false, 10, 18),
    FRIDAY("금", false, 10, 18),
    SATURDAY("토", true, null, null),
    SUNDAY("일", true, null, null);

    private static final List<Integer> HOLIDAY = List.of(25);

    private final String dayOfWeekKorean;
    private final boolean isWeekend;
    private final Integer startHour;
    private final Integer endHour; // 사용하지 않지만 요구사항에 포함되어 남겨놓습니다.

    WorkDay(String dayOfWeekKorean, boolean isWeekend, Integer startHour, Integer endHour) {
        this.dayOfWeekKorean = dayOfWeekKorean;
        this.isWeekend = isWeekend;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static WorkDay findByDayOfWeek(DayOfWeek dayOfWeek) {
        return WorkDay.valueOf(dayOfWeek.name());
    }

    public static boolean isHoliday(LocalDate localDate) {
        WorkDay workDay = findByDayOfWeek(localDate.getDayOfWeek());
        return workDay.isWeekend() || HOLIDAY.contains(localDate.getDayOfMonth());
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public String getDayOfWeekKorean() {
        return dayOfWeekKorean;
    }

    public int retrieveWeekdaysStartHour() {
        validateWeekend();
        return startHour;
    }

    private void validateWeekend() {
        if (isWeekend) {
            throw new IllegalArgumentException("주말은 등교시간이 없습니다.");
        }
    }
}
