import java.time.LocalDate;
import java.util.List;

public class AttendanceManager {

    private final CrewAttendanceBook crewAttendanceBook;

    public AttendanceManager(CrewAttendanceBook crewAttendanceBook) {
        this.crewAttendanceBook = crewAttendanceBook;
    }

    public void processAttendance(String crewName, LocalDate attendanceDate, AttendanceTime attendanceTime) {
        validateAttendanceDay(attendanceDate);
        AttendanceRecords attendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);
        Attendance attendance = createAttendance(attendanceDate, attendanceTime);
        attendanceRecords.add(attendance);
    }

    public List<Attendance> retrieveAllWithEmptyUntilDate(String crewName, LocalDate date) {
        AttendanceRecords attendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);
        return attendanceRecords.retrieveAllAttendanceUntilDate(date);
    }

    private Attendance createAttendance(LocalDate attendanceDate, AttendanceTime attendanceTime) {
        int startHour = SchoolDay.retrieveStartHourByDate(attendanceDate);
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(startHour, attendanceTime);
        return Attendance.create(attendanceDate, attendanceTime, attendanceStatus);
    }

    private static void validateAttendanceDay(LocalDate attendanceDate) {
        if (SchoolDay.isNotSchoolDay(attendanceDate)) {
            throw new IllegalArgumentException("[ERROR] 출석할 수 없는 날입니다.");
        }
    }
}
