package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AttendanceDiscriminatorTest {

    @Test
    void 출석_여부를_판단한다() {
        // given & when
        AttendanceDiscriminator attendanceDiscriminator = AttendanceDiscriminator.calculateDiscriminator(
                Day.MONDAY, LocalDateTime.of(2024, 12, 2, 13, 4));

        // then
        assertThat(attendanceDiscriminator).isEqualTo(AttendanceDiscriminator.ATTENDANCE);
    }

    @Test
    void 지각_여부를_판단한다() {
        // given & when
        AttendanceDiscriminator attendanceDiscriminator = AttendanceDiscriminator.calculateDiscriminator(
                Day.MONDAY, LocalDateTime.of(2024, 12, 2, 13, 10));

        // then
        assertThat(attendanceDiscriminator).isEqualTo(AttendanceDiscriminator.PERCEPTION);
    }

    @Test
    void 결석_여부를_판단한다() {
        // given & when
        AttendanceDiscriminator attendanceDiscriminator = AttendanceDiscriminator.calculateDiscriminator(
                Day.MONDAY, LocalDateTime.of(2024, 12, 2, 14, 4));

        // then
        assertThat(attendanceDiscriminator).isEqualTo(AttendanceDiscriminator.ABSENCE);
    }
}
