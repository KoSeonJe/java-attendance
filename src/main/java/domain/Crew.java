package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Crew {
    private final String name;
    private final Attendance attendance;

    public Crew(String name, Attendance attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public List<LocalDateTime> getDateTimesUntilDate(LocalDateTime dateTime) {
        return attendance.getDateTimesUntilDate(dateTime);
    }

    public List<LocalDateTime> getDateTimes() {
        return attendance.getDateTimes();
    }

    public void addAttendance(LocalDateTime dateTime) {
        attendance.addAttendance(dateTime);
    }

    public LocalDateTime updateAttendance(LocalDateTime updateDateTime) {
        return attendance.updateAttendance(updateDateTime);
    }

    public Attendance getAttendance() {
        return attendance;
    }
}
