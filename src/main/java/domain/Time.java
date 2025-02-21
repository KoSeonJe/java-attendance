package domain;

import java.util.Objects;

public class Time {
    private final int hour;
    private final int minute;

    public Time(int hour, int minute) {
        validateSize(hour, minute);
        this.hour = hour;
        this.minute = minute;
    }

    private void validateSize(int hour, int minute) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("시간은 0 이상 23 이하여야 합니다.");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("분은 0 이상 59 이하여야 합니다.");
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Time time = (Time) o;
        return Objects.equals(hour, time.hour) && Objects.equals(minute, time.minute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }
}
