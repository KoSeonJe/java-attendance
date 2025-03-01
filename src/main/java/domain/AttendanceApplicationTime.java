package domain;

import java.time.LocalDateTime;
import util.ApplicationTime;

public final class AttendanceApplicationTime implements ApplicationTime {

    private static final AttendanceApplicationTime INSTANCE = new AttendanceApplicationTime();

    private AttendanceApplicationTime() {
    }

    public static AttendanceApplicationTime getInstance() {
        return INSTANCE;
    }

    @Override
    public LocalDateTime getApplicationTime() {
        return LocalDateTime.of(2024, 12, 13, 10, 0);
    }
}
