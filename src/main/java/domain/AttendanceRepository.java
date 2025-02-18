package domain;

import java.util.HashMap;
import java.util.Map;

public class AttendanceRepository {
    private final Map<String, Attendance> attendances = new HashMap<>();

    public void save(Attendance attendance) {
        attendances.put(attendance.getName(), attendance);
    }

    public Attendance findByName(String name) {
        if (attendances.containsKey(name)) {
            return attendances.get(name);
        }
        throw new IllegalArgumentException("해당 이름의 출석 정보가 없습니다.");
    }
}
