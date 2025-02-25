public class AttendanceTime {

    private int hour;
    private int minute;

    private AttendanceTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }


    public static AttendanceTime create(int hour, int minute) {
        return new AttendanceTime(hour, minute);
    }
}
