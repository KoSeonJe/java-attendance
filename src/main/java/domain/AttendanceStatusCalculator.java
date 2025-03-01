package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceStatusCalculator {

    public AttendanceStatusCounter calculateAllCount(List<Attendance> attendances) {
        EnumMap<AttendanceStatus, Integer> attendanceStatusesCount = attendances.stream()
                .map(Attendance::getAttendanceStatus)
                .collect(Collectors.toMap(
                        status -> status,
                        status -> 1,
                        Integer::sum,
                        () -> new EnumMap<>(AttendanceStatus.class)
                ));

        return AttendanceStatusCounter.create(attendanceStatusesCount);
    }
}
