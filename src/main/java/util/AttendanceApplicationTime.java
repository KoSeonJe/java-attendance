package util;

import java.time.LocalDateTime;

public final class AttendanceApplicationTime implements ApplicationTime {

    private static final LocalDateTime APPLICATION_TIME = LocalDateTime.of(2024, 12, 13, 10, 5);

    @Override
    public LocalDateTime getApplicationTime() {
        return APPLICATION_TIME;
    }
}
