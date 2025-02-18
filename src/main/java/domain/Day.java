package domain;

public enum Day {
    MONDAY("월", false, 13, 18),
    TUESDAY("화", false, 10, 18),
    WEDNESDAY("수", false, 10, 18),
    THURSDAY("목", false, 10, 18),
    FRIDAY("금", false, 10, 18),
    SATURDAY("토", true, 0, 0),
    SUNDAY("일", true, 0, 0);

    private final String name;
    private final boolean isWeekend;
    private final Integer startHour;
    private final Integer endHour;

    Day(String name, boolean isWeekend, Integer startHour, Integer endHour) {
        this.name = name;
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
