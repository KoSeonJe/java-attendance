package domain;

import static domain.AttendanceStatus.ABSENCE;
import static domain.AttendanceStatus.ATTENDANCE;
import static domain.AttendanceStatus.PERCEPTION;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attendance {

    private static final int STATUS_DEFAULT_COUNT = 0;

    private final Map<Date, Time> dateTimes;

    public Attendance(Map<Date, Time> dateTimes) {
        this.dateTimes = new HashMap<>(dateTimes);
    }

    public void addDateTime(AttendanceDateTime attendanceDateTime) {
        if (isAlreadyExists(attendanceDateTime)) {
            throw new IllegalArgumentException("해당 날짜의 출석 정보가 이미 존재합니다.");
        }
        dateTimes.put(attendanceDateTime.getDate(), attendanceDateTime.getTime());
    }

    public void updateDateTime(AttendanceDateTime updateAttendanceDateTime) {
        if (!isAlreadyExists(updateAttendanceDateTime)) {
            throw new IllegalArgumentException("해당 날짜의 출석 정보가 없습니다.");
        }
        dateTimes.put(updateAttendanceDateTime.getDate(), updateAttendanceDateTime.getTime());
    }

    private boolean isAlreadyExists(AttendanceDateTime attendanceDateTime) {
        Time time = dateTimes.get(attendanceDateTime.getDate());
        return time != null;
    }

    public AttendanceDateTime retrieveDateTime(Date date) {
        return new AttendanceDateTime(date, dateTimes.get(date));
    }

    public List<AttendanceDateTime> retrieveDateTimesOrderByDate() {
        return dateTimes.keySet().stream()
                .map(date -> new AttendanceDateTime(date, dateTimes.get(date)))
                .sorted()
                .toList();
    }

    public AttendanceStatus retrieveAttendanceStatus(Date date) {
        AttendanceDateTime attendanceDateTime = new AttendanceDateTime(date, dateTimes.get(date));
        return AttendanceStatus.findByDateTime(attendanceDateTime);
    }

    public List<AttendanceStatus> retrieveAttendanceStatuses() {
        return retrieveDateTimesOrderByDate().stream()
                .map(datetime -> retrieveAttendanceStatus(datetime.getDate()))
                .toList();
    }

    public Map<AttendanceStatus, Integer> calculateAttendanceStatusCount() {
        Map<AttendanceStatus, Integer> attendanceStatusCount = initializeAttendanceMap();
        List<AttendanceStatus> attendanceStatuses = retrieveDateTimesOrderByDate().stream()
                .map(dateTime -> new AttendanceDateTime(dateTime.getDate(), dateTimes.get(dateTime.getDate())))
                .map(AttendanceStatus::findByDateTime)
                .toList();
        attendanceStatuses.forEach(status -> attendanceStatusCount.put(status, attendanceStatusCount.get(status) + 1));

        return attendanceStatusCount;
    }

    private static Map<AttendanceStatus, Integer> initializeAttendanceMap() {
        return new HashMap<>(Map.of(
                ATTENDANCE, STATUS_DEFAULT_COUNT,
                PERCEPTION, STATUS_DEFAULT_COUNT,
                ABSENCE, STATUS_DEFAULT_COUNT
        ));
    }
}
