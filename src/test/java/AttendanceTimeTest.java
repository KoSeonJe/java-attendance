import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendanceTimeTest {

    @DisplayName("입력한 시간과 분으로 출석 시간을 생성한다")
    @Test
    void createByHourAndMinute() {
        // given
        int hour = 10;
        int minute = 30;

        // when
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(attendanceTime)
                    .extracting("hour").isEqualTo(10);
            softly.assertThat(attendanceTime)
                    .extracting("minute").isEqualTo(30);
        });
    }

    @DisplayName("출석시간 생성시 분의 범위를 벗어나면 예외를 발생시킨다")
    @Test
    void minuteOutOfRange() {
        // given
        int hour = 10;
        int minute = 100;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> AttendanceTime.create(hour, minute)
        ).withMessage("[ERROR] 분의 범위를 벗어났습니다");
    }

    @DisplayName("출석시간 생성시 캠퍼스 운영시간외 출석을 하면 예외를 발생시킨다")
    @Test
    void OperationTimeOutOfRange() {
        // given
        int hour = 6;
        int minute = 30;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> AttendanceTime.create(hour, minute)
        ).withMessage("[ERROR] 현재 운영시간이 아닙니다");
    }

    @DisplayName("출석시간과 분의 수정이 가능하다")
    @Test
    void updateTime() {
        // given
        int hour = 10;
        int minute = 30;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);

        int updateHour = 9;
        int updateMinute = 55;

        // when
        attendanceTime.updateTime(updateHour, updateMinute);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(attendanceTime)
                    .extracting("hour").isEqualTo(9);
            softly.assertThat(attendanceTime)
                    .extracting("minute").isEqualTo(55);
        });
    }

    @DisplayName("출석시간 수정시 분의 범위를 벗어나면 예외를 발생시킨다")
    @Test
    void updateMinuteOutOfRange() {
        // given
        int hour = 10;
        int minute = 30;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);

        int updateHour = 9;
        int updateMinute = 100;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> attendanceTime.updateTime(updateHour, updateMinute)
        ).withMessage("[ERROR] 분의 범위를 벗어났습니다");
    }

    @DisplayName("출석시간 수정시 분의 범위를 벗어나면 예외를 발생시킨다")
    @Test
    void updateOperationTimeOutOfRange() {
        // given
        int hour = 10;
        int minute = 30;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);

        int updateHour = 6;
        int updateMinute = 30;

        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> attendanceTime.updateTime(updateHour, updateMinute)
        ).withMessage("[ERROR] 현재 운영시간이 아닙니다");
    }
}
