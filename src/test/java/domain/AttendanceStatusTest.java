package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AttendanceStatusTest {

    @Test
    void 출석_여부를_판단한다() {
        // given & when
        AttendanceStatus attendanceStatus = AttendanceStatus.calculateDiscriminator(
                Day.MONDAY, LocalDateTime.of(2024, 12, 2, 13, 4));

        // then
        assertThat(attendanceStatus).isEqualTo(AttendanceStatus.ATTENDANCE);
    }

    @Test
    void 지각_여부를_판단한다() {
        // given & when
        AttendanceStatus attendanceStatus = AttendanceStatus.calculateDiscriminator(
                Day.MONDAY, LocalDateTime.of(2024, 12, 2, 13, 10));

        // then
        assertThat(attendanceStatus).isEqualTo(AttendanceStatus.PERCEPTION);
    }

    @Test
    void 결석_여부를_판단한다() {
        // given & when
        AttendanceStatus attendanceStatus = AttendanceStatus.calculateDiscriminator(
                Day.MONDAY, LocalDateTime.of(2024, 12, 2, 14, 4));

        // then
        assertThat(attendanceStatus).isEqualTo(AttendanceStatus.ABSENCE);
    }

    @Test
    void 주말에_출석할_수_없다() {
        // given & when & then
        assertThatThrownBy(() -> AttendanceStatus.calculateDiscriminator(Day.SATURDAY, LocalDateTime.now()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주말에는 출석할 수 없습니다.");
    }

    @Test
    void 캠퍼스_운영_시간이_아니다() {
        // given & when & then
        assertThatThrownBy(
                () -> AttendanceStatus.calculateDiscriminator(Day.MONDAY, LocalDateTime.of(2024, 12, 2, 7, 59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("캠퍼스 운영 시간이 아닙니다.");
    }
}
