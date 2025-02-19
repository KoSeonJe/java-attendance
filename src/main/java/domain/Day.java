package domain;

import java.time.DayOfWeek;

public enum Day {
    MONDAY(DayOfWeek.MONDAY, false, 13, 18),
    TUESDAY(DayOfWeek.TUESDAY, false, 10, 18),
    WEDNESDAY(DayOfWeek.WEDNESDAY, false, 10, 18),
    THURSDAY(DayOfWeek.THURSDAY, false, 10, 18),
    FRIDAY(DayOfWeek.FRIDAY, false, 10, 18),
    SATURDAY(DayOfWeek.SATURDAY, true, null, null),
    SUNDAY(DayOfWeek.SUNDAY, true, null, null);

    private final DayOfWeek dayOfWeek;
    private final boolean isWeekend;
    private final Integer startHour;
    private final Integer endHour;

    Day(DayOfWeek dayOfWeek, boolean isWeekend, Integer startHour, Integer endHour) {
        this.dayOfWeek = dayOfWeek;
        this.isWeekend = isWeekend;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}
