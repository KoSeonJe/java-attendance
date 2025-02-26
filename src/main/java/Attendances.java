import java.time.LocalDate;
import java.util.List;

public class Attendances {

    private final List<Attendance> attendances;

    private Attendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public static Attendances create(List<Attendance> attendances) {
        return new Attendances(attendances);
    }

    public void add(Attendance attendance) {
        validateAlreadyExistByDate(attendance);
        attendances.add(attendance);
    }

    private void validateAlreadyExistByDate(Attendance attendance) {
        boolean alreadyExist = attendances.stream()
                .anyMatch(originAttendance -> originAttendance.isEqualDate(attendance));
        if (alreadyExist) {
            throw new IllegalArgumentException("[ERROR] 해당 날짜에 출석 기록이 이미 존재합니다");
        }
    }

    public Attendance retrieveAttendanceByDate(LocalDate attendanceDate) {
        return attendances.stream()
                .filter(attendance -> attendance.isEqualDate(attendanceDate))
                .findFirst()
                .orElse(null);
    }
}
