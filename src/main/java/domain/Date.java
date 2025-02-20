package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Date {
    private LocalDate localDate;

    public Date(LocalDate localDate) {
        this.localDate = localDate;
    }

    public boolean isEqual(Date targetDate) {
        return this.localDate.isEqual(targetDate.localDate);
    }

    public boolean isBefore(Date targetDate) {
        return this.localDate.isBefore(targetDate.localDate);
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
        return getWorkDay().isWeekend() || getDayValue() == 25;  // TODO: 공휴일 로직 추가
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return Objects.equals(localDate, date.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(localDate);
    }
}
