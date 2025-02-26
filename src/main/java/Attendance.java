import java.time.LocalDate;

public class Attendance {

    private LocalDate attendanceDate;
    private AttendanceTime attendanceTime;
    private AttendanceStatus attendanceStatus;

    private Attendance(LocalDate attendanceDate, AttendanceTime attendanceTime, AttendanceStatus attendanceStatus) {
        this.attendanceDate = attendanceDate;
        this.attendanceTime = attendanceTime;
        this.attendanceStatus = attendanceStatus;
    }

    public static Attendance create(LocalDate attendanceDate, AttendanceTime attendanceTime, AttendanceStatus attendanceStatus) {
        return new Attendance(attendanceDate, attendanceTime, attendanceStatus);
    }

    public void updateAttendanceTime(int updateHour, int updateMinute) {
        attendanceTime.updateTime(updateHour, updateMinute);
    }
}
