package domain;

import java.time.DayOfWeek;

public enum Day {
    MONDAY(DayOfWeek.MONDAY, "월", false, 13, 18),
    TUESDAY(DayOfWeek.TUESDAY, "화", false, 10, 18),
    WEDNESDAY(DayOfWeek.WEDNESDAY, "수", false, 10, 18),
    THURSDAY(DayOfWeek.THURSDAY, "목", false, 10, 18),
    FRIDAY(DayOfWeek.FRIDAY, "금", false, 10, 18),
    SATURDAY(DayOfWeek.SATURDAY, "토", true, null, null),
    SUNDAY(DayOfWeek.SUNDAY, "일", true, null, null);

    private final DayOfWeek dayOfWeek;
    private final String dayOfWeekKorean;
    private final boolean isWeekend;
    private final Integer startHour;
    private final Integer endHour;

    Day(DayOfWeek dayOfWeek, String dayOfWeekKorean, boolean isWeekend, Integer startHour, Integer endHour) {
        this.dayOfWeek = dayOfWeek;
        this.dayOfWeekKorean = dayOfWeekKorean;
        this.isWeekend = isWeekend;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static Day findByDayOfWeek(DayOfWeek dayOfWeek) {
        return Day.valueOf(dayOfWeek.name());
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public String getDayOfWeekKorean() {
        return dayOfWeekKorean;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}
