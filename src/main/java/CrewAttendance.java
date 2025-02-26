import java.time.LocalDate;
import java.util.List;

public class CrewAttendance {

    private String crewName;
    private AttendanceRecords attendanceRecords;

    private CrewAttendance(String crewName, AttendanceRecords attendanceRecords) {
        this.crewName = crewName;
        this.attendanceRecords = attendanceRecords;
    }

    public List<Attendance> retrieveAllByDate(LocalDate date) {
        return attendanceRecords.retrieveAllAttendanceUntilDate(date);
    }

    public static CrewAttendance create(String crewName, AttendanceRecords attendanceRecords) {
        return new CrewAttendance(crewName, attendanceRecords);
    }
}
