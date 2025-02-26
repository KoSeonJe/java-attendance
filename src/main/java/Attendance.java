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

    public void updateAttendance(int updateHour, int updateMinute, AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
        if (attendanceTime == null) {
            this.attendanceTime = AttendanceTime.create(updateHour, updateMinute);
            return;
        }
        attendanceTime.updateTime(updateHour, updateMinute);
    }

    public boolean isEqualDate(Attendance attendance) {
        return this.attendanceDate == attendance.attendanceDate;
    }

    public boolean isEqualDate(LocalDate attendanceDate) {
        return this.attendanceDate == attendanceDate;
    }

    public static Attendance create(LocalDate attendanceDate, AttendanceTime attendanceTime, AttendanceStatus attendanceStatus) {
        return new Attendance(attendanceDate, attendanceTime, attendanceStatus);
    }

    public static Attendance createAbsenceAttendance(LocalDate attendanceDate) {
        return new Attendance(attendanceDate, null, AttendanceStatus.ABSENCE);
    }

    public boolean isBefore(LocalDate date) {
        return attendanceDate.isBefore(date);
    }
}
