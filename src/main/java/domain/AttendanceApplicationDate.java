package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import util.ApplicationDate;

public final class AttendanceApplicationDate implements ApplicationDate {

    private static final AttendanceApplicationDate INSTANCE = new AttendanceApplicationDate();

    private AttendanceApplicationDate() {
    }

    public static AttendanceApplicationDate getInstance() {
        return INSTANCE;
    }

    @Override
    public LocalDate getApplicationDate() {
        return LocalDate.of(2024, 12, 13);
    }
}
