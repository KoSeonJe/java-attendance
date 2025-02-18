package domain;

import java.util.HashMap;
import java.util.Map;

public class AttendanceRepository {
    private final Map<String, Attendance> attendances = new HashMap<>();
    
    public void save(Attendance attendance) {
        attendances.put(attendance.getName(), attendance);
    }

    public Attendance findByName(String name) {
        return null;
    }

    public Attendance get(String name) {
        return attendances.get(name);
    }
}
