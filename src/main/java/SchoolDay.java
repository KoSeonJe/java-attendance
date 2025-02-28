import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum SchoolDay {

    MONDAY(true, 13, 18),
    TUESDAY(true, 10, 18),
    WEDNESDAY(true, 10, 18),
    THURSDAY(true, 10, 18),
    FRIDAY(true, 10, 18),
    SATURDAY(false, 10, 18),
    SUNDAY(false, 10, 18);

    private static final List<Integer> HOLIDAY = List.of(25);

    private boolean isPossibleAttendance;
    private int startHour;
    private int endHour;

    SchoolDay(boolean isPossibleAttendance, int startHour, int endHour) {
        this.isPossibleAttendance = isPossibleAttendance;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public boolean isPossibleAttendance() {
        return isPossibleAttendance;
    }

    public int getStartHour() {
        return startHour;
    }

    public static boolean isPossibleAttendance(LocalDate date) {
        if (isHoliday(date)) {
            return false;
        }
        return Arrays.stream(SchoolDay.values())
                .filter(schoolDay -> equalDayOfWeek(schoolDay, date.getDayOfWeek()))
                .map(SchoolDay::isPossibleAttendance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 요일을 찾지 못했습니다"));
    }

    private static boolean isHoliday(LocalDate date) {
        return HOLIDAY.contains(date.getDayOfMonth());
    }

    private static boolean equalDayOfWeek(SchoolDay schoolDay, DayOfWeek dayOfWeek) {
        return Objects.equals(schoolDay.name(), dayOfWeek.name());
    }
}
