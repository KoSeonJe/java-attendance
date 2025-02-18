package domain;

import java.time.LocalDate;

public class Attendance {
    private final String name;
    private final LocalDate date;

    public Attendance(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }
}
