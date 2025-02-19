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

    public List<LocalDateTime> getDateTimes() {
        return attendance.getDateTimes();
    }

    public void addAttendance(LocalDateTime dateTime) {
        attendance.addAttendance(dateTime);
    }

    public void updateAttendance(LocalDateTime updateDateTime) {
        attendance.updateAttendance(updateDateTime);
    }
}
