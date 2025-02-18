package domain;

import java.time.LocalDateTime;

public enum AttendanceDiscriminator {
    ATTENDANCE("출석", 0),
    PERCEPTION("지각", 5),
    ABSENCE("결석", 30);

    private final String name;
    private final int limitTime;

    AttendanceDiscriminator(String name, int limitMinute) {
        this.name = name;
        this.limitTime = limitMinute;
    }

    public static AttendanceDiscriminator calculateDiscriminator(Day today, LocalDateTime localDateTime) {
        if (today.isWeekend()) {
            throw new IllegalArgumentException("주말에는 출석할 수 없습니다.");
        }

        if (isOutsideCampusHours(localDateTime)) {
            throw new IllegalArgumentException("캠퍼스 운영 시간이 아닙니다.");
        }

        int startHour = today.getStartHour();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();

        if (hour > startHour || (hour == startHour && minute >= ABSENCE.limitTime)) {
            return ABSENCE;
        }

        if (hour == startHour && minute >= PERCEPTION.limitTime) {
            return PERCEPTION;
        }

        return ATTENDANCE;
    }

    private static boolean isOutsideCampusHours(LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        return hour < 8 || hour > 18;
    }
}
