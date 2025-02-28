import static org.assertj.core.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SchoolDayTest {

    @DisplayName("입력 날짜가 출석 가능다면 true를 반환한다")
    @Test
    void isPossibleAttendance() {
        // given
        LocalDate localDate = LocalDate.of(2024, 12, 13);

        // when
        boolean isPossibleAttendance = SchoolDay.isPossibleAttendance(localDate);

        // then
        assertThat(isPossibleAttendance).isTrue();
    }

    @DisplayName("입력 날짜가 출석 가능하지 않다면 false를 반환한다")
    @Test
    void isNotPossibleAttendance() {
        // given
        LocalDate localDate = LocalDate.of(2024, 12, 14);

        // when
        boolean isPossibleAttendance = SchoolDay.isPossibleAttendance(localDate);

        // then
        assertThat(isPossibleAttendance).isFalse();
    }

    @DisplayName("입력 날짜가 공휴일일 경우 false를 반환한다")
    @Test
    void isNotPossibleAttendanceWithHoliday() {
        // given
        LocalDate holiday = LocalDate.of(2024, 12, 25);

        // when
        boolean isPossibleAttendance = SchoolDay.isPossibleAttendance(holiday);

        // then
        assertThat(isPossibleAttendance).isFalse();
    }

    @DisplayName("입력 날짜의 출석 시작 시각을 조회할 수 있다")
    @Test
    void retrieveAttendanceStartHour() {
        // given
        LocalDate attendanceDate = LocalDate.of(2024, 12, 13);

        // when
        int startHour = SchoolDay.retrieveStartHourByDate(attendanceDate);

        // then
        assertThat(startHour).isEqualTo(10);
    }
}
