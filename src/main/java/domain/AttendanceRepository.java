package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AttendanceRepository {
    private final Map<String, Attendance> attendances = new LinkedHashMap<>();

    public void save(Attendance attendance) {
        attendances.put(attendance.getName(), attendance);
    }

    public Attendance findByName(String name) {
        if (attendances.containsKey(name)) {
            return attendances.get(name);
        }
        throw new IllegalArgumentException("해당 이름의 출석 정보가 없습니다.");
    }

    public List<Attendance> findAll() {
        return attendances.values().stream()
                .toList();
    }
}
