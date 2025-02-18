package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendance {
    private final String name;
    private final List<LocalDateTime> dateTimes;

    public Attendance(String name, List<LocalDateTime> dateTimes) {
        this.name = name;
        this.dateTimes = dateTimes;
    }

    public String getName() {
        return name;
    }

    public List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }
}
