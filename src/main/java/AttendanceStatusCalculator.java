import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AttendanceStatusCalculator {

    public Map<AttendanceStatus, Integer> calculateAllCount(List<Attendance> attendances) {
        return attendances.stream()
                .map(Attendance::getAttendanceStatus)
                .collect(Collectors.toMap(
                        status -> status,
                        status -> 1,
                        Integer::sum,
                        () -> new EnumMap<>(AttendanceStatus.class)
                ));
    }
}
