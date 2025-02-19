package domain;

import java.time.LocalDateTime;

public class AttendanceResult implements Comparable<AttendanceResult> {
    private final LocalDateTime dateTime;

    public AttendanceResult(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static AttendanceResult from(LocalDateTime dateTime) {
        return new AttendanceResult(dateTime);
    }

    public AttendanceStatus calculateAttendanceStatus() {
        Day updateDay = Day.findByDayOfWeek(dateTime.getDayOfWeek());
        return AttendanceStatus.calculateDiscriminator(updateDay, dateTime);
    }

    public String calculateDayOfWeekKorean() {
        Day updateDay = Day.findByDayOfWeek(dateTime.getDayOfWeek());
        return updateDay.getDayOfWeekKorean();
    }

    public int getMonthValue() {
        return dateTime.getMonthValue();
    }

    public int getDayOfMonth() {
        return dateTime.getDayOfMonth();
    }

    public int getHour() {
        return dateTime.getHour();
    }

    public int getMinute() {
        return dateTime.getMinute();
    }

    @Override
    public int compareTo(AttendanceResult other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
