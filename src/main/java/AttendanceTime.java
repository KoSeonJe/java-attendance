public class AttendanceTime {

    private static final int MIN_MINUTE = 0;
    private static final int MAX_MINUTE = 59;

    private int hour;
    private int minute;

    private AttendanceTime(int hour, int minute) {
        validateMinuteRange(minute);
        this.hour = hour;
        this.minute = minute;
    }

    public static AttendanceTime create(int hour, int minute) {
        return new AttendanceTime(hour, minute);
    }

    private void validateMinuteRange(int minute) {
        if (minute < MIN_MINUTE || minute > 59) {
            throw new IllegalArgumentException("[ERROR] 분의 범위를 벗어났습니다");
        }
    }
}
