package domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public enum SchoolDay {

    MONDAY(true, 13, 18),
    TUESDAY(true, 10, 18),
    WEDNESDAY(true, 10, 18),
    THURSDAY(true, 10, 18),
    FRIDAY(true, 10, 18),
    SATURDAY(false, -1, -1),
    SUNDAY(false, -1, -1);

    private static final List<Integer> HOLIDAY = List.of(25);

    private final boolean isPossibleAttendance;
    private final int startHour;

    SchoolDay(boolean isPossibleAttendance, int startHour, int endHour) {
        this.isPossibleAttendance = isPossibleAttendance;
        this.startHour = startHour;
    }

    public boolean isPossibleAttendance() {
        return isPossibleAttendance;
    }

    public int getStartHour() {
        return startHour;
    }

    public static int retrieveStartHourByDate(LocalDate date) {
        if (isNotSchoolDay(date)) {
            throw new IllegalArgumentException("[ERROR] 주말 혹은 공휴일에는 출석 시각을 조회할 수 없습니다");
        }

        return Arrays.stream(SchoolDay.values())
                .filter(schoolDay -> equalDayOfWeek(schoolDay, date.getDayOfWeek()))
                .map(SchoolDay::getStartHour)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 요일을 찾지 못했습니다"));
    }

    public static void validateSchoolDay(LocalDate date) {
        if (!isPossibleAttendance(date)) {
            throw new IllegalArgumentException(String.format("[ERROR] %s은(는) 등교일이 아닙니다.", customFormat(date)));
        }
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

    public static boolean isNotSchoolDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return isHoliday(date) || dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private static String customFormat(LocalDate date) {
        return String.format("%d월 %d일 %s",
                date.getMonthValue(),
                date.getDayOfMonth(),
                date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN)
        );
    }

    private static boolean isHoliday(LocalDate date) {
        return HOLIDAY.contains(date.getDayOfMonth());
    }

    private static boolean equalDayOfWeek(SchoolDay schoolDay, DayOfWeek dayOfWeek) {
        return Objects.equals(schoolDay.name(), dayOfWeek.name());
    }
}
