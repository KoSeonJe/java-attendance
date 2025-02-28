import java.time.LocalDate;

public class AttendanceManager {

    private final CrewAttendanceBook crewAttendanceBook;

    public AttendanceManager(CrewAttendanceBook crewAttendanceBook) {
        this.crewAttendanceBook = crewAttendanceBook;
    }

    public void processAttendance(String crewName, LocalDate attendanceDate, AttendanceTime attendanceTime) {
        AttendanceRecords attendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);
        Attendance attendance = createAttendance(attendanceDate, attendanceTime);
        attendanceRecords.add(attendance);
    }

    private Attendance createAttendance(LocalDate attendanceDate, AttendanceTime attendanceTime) {
        int startHour = SchoolDay.retrieveStartHourByDate(attendanceDate);
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(startHour, attendanceTime);
        return Attendance.create(attendanceDate, attendanceTime, attendanceStatus);
    }
}
