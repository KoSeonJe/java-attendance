public enum AttendanceStatus {
    ATTENDANCE,
    LATE;

    private static final int LATE_LIMIT_TIME = 5;

    public static AttendanceStatus findByStartHourAndAttendanceTime(int dayStartHour, AttendanceTime attendanceTime) {
        if (isLate(dayStartHour, attendanceTime)) {
            return LATE;
        }
        return null;
    }

    private static boolean isLate(int dayStartHour, AttendanceTime attendanceTime) {
        return attendanceTime.equalsHour(dayStartHour) && attendanceTime.isAfterMinute(LATE_LIMIT_TIME);
    }
}
