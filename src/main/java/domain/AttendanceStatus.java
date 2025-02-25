package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AttendanceStatus {
    ATTENDANCE("출석", 0),
    PERCEPTION("지각", 5),
    ABSENCE("결석", 30);

    private final String name;
    private final int limitTime;

    AttendanceStatus(String name, int limitMinute) {
        this.name = name;
        this.limitTime = limitMinute;
    }

    public static AttendanceStatus findByDateTime(AttendanceDateTime attendanceDateTime) {
        if (attendanceDateTime.isTimeNull()) {
            return ABSENCE;
        }
        WorkDay today = attendanceDateTime.getDate().getWorkDay();
        Time time = attendanceDateTime.getTime();
        return determineAttendanceStatus(today, time);
    }

    public boolean isAbsence() {
        return this == ABSENCE;
    }

    public boolean isPerception() {
        return this == PERCEPTION;
    }

    public String getName() {
        return name;
    }

    private static AttendanceStatus determineAttendanceStatus(WorkDay today, Time time) {
        int startHour = today.retrieveWeekdaysStartHour();
        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour > startHour || (hour == startHour && minute > ABSENCE.limitTime)) {
            return ABSENCE;
        }
        if (hour == startHour && minute > PERCEPTION.limitTime) {
            return PERCEPTION;
        }
        return ATTENDANCE;
    }
}
