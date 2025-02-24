package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Date {

    private static final int REQUIREMENT_YEAR = 2024;
    private static final int REQUIREMENT_MONTH = 12;
    private static final List<Integer> HOLIDAY = List.of(25);

    private final LocalDate localDate;

    public Date(LocalDate localDate) {
        validateRequirementDate(localDate);
        validateHoliday(localDate);
        this.localDate = localDate;
    }

    public boolean isAfter(Date targetDate) {
        return this.localDate.isAfter(targetDate.localDate);
    }

    public int getMonthValue() {
        return localDate.getMonthValue();
    }

    public int getDayValue() {
        return localDate.getDayOfMonth();
    }

    public WorkDay getWorkDay() {
        return WorkDay.findByDayOfWeek(localDate.getDayOfWeek());
    }

    public boolean isHoliday() {
        return getWorkDay().isWeekend() || HOLIDAY.contains(getDayValue());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return Objects.equals(localDate, date.localDate);
    }

    private void validateHoliday(LocalDate localDate) {
        WorkDay workDay = WorkDay.findByDayOfWeek(localDate.getDayOfWeek());
        if (workDay.isWeekend() || HOLIDAY.contains(localDate.getDayOfMonth())) {
            throw new IllegalArgumentException("공휴일 및 주말에는 날짜를 등록할 수 없습니다.");
        }
    }

    private void validateRequirementDate(LocalDate localDate) {
        if (localDate.getYear() != REQUIREMENT_YEAR || localDate.getMonthValue() != REQUIREMENT_MONTH) {
            throw new IllegalArgumentException("2024년 12월이 아닌 날짜는 등록할 수 없습니다.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(localDate);
    }
}
