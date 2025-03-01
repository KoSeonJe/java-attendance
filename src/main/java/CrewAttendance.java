import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CrewAttendance {

    private String crewName;
    private AttendanceRecords attendanceRecords;

    private CrewAttendance(String crewName, AttendanceRecords attendanceRecords) {
        this.crewName = crewName;
        this.attendanceRecords = attendanceRecords;
    }

    public AttendanceRecords getAttendanceRecords() {
        return attendanceRecords;
    }

    public static CrewAttendance create(String crewName, AttendanceRecords attendanceRecords) {
        return new CrewAttendance(crewName, attendanceRecords);
    }

    public boolean equalName(String crewName) {
        return Objects.equals(this.crewName, crewName);
    }
}
