package domain;

import java.util.Objects;

public class Time {

    private static final int MAX_MINUTE = 59;
    private static final int MIN_MINUTE = 0;
    private static final int CAMPUS_OPEN_HOUR = 8;
    private static final int CAMPUS_CLOSE_HOUR = 18;

    private final int hour;
    private final int minute;

    public Time(int hour, int minute) {
        validateMinuteSize(minute);
        validateHour(hour);
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    private void validateHour(int hour) {
        if (hour < CAMPUS_OPEN_HOUR || hour > CAMPUS_CLOSE_HOUR) {
            throw new IllegalArgumentException("캠퍼스 운영 시간이 아닙니다.");
        }
    }

    private void validateMinuteSize(int minute) {
        if (minute < MIN_MINUTE || minute > MAX_MINUTE) {
            throw new IllegalArgumentException("분은 0 이상 59 이하여야 합니다.");
        }
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
