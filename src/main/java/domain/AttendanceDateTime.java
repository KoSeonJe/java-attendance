package domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class AttendanceDateTime implements Comparable<AttendanceDateTime> {

    private static final int CAMPUS_OPEN_TIME = 8;
    private static final int CAMPUS_CLOSE_TIME = 18;

    private final Date date;
    private final Time time;

    public AttendanceDateTime(Date date, Time time) {
        validateTime(time);
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public boolean isTimeNull() {
        return time == null;
    }

    public static AttendanceDateTime of(LocalDateTime localDateTime) {
        return new AttendanceDateTime(new Date(localDateTime.toLocalDate()),
                new Time(localDateTime.toLocalTime().getHour(), localDateTime.toLocalTime().getMinute()));
    }

    private static void validateTime(Time time) {
        int hour = time.getHour();
        if (hour < CAMPUS_OPEN_TIME || hour > CAMPUS_CLOSE_TIME) {
            throw new IllegalArgumentException("캠퍼스 운영 시간이 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttendanceDateTime attendanceDateTime = (AttendanceDateTime) o;
        return Objects.equals(date, attendanceDateTime.date) && Objects.equals(time, attendanceDateTime.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time);
    }

    @Override
    public int compareTo(AttendanceDateTime o) {
        return date.getDayValue() - (o.date.getDayValue());
    }
}
