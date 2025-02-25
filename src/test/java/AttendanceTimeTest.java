import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("입력한 시간이 출석 시간과 동일하다면 true를 반환한다")
    @Test
    void isEqualHour() {
        // given
        int hour = 10;
        int minute = 3;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);
        int compareHour = 10;

        //when
        boolean isEqualsHour = attendanceTime.isEqualHour(compareHour);

        //then
        assertThat(isEqualsHour).isTrue();
    }

    @DisplayName("입력한 시간이 출석 시간과 동일하지 않다면 false를 반환한다")
    @Test
    void isNotEqualHour() {
        // given
        int hour = 10;
        int minute = 3;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);
        int compareHour = 9;

        //when
        boolean isNotEqualsHour = attendanceTime.isEqualHour(compareHour);

        //then
        assertThat(isNotEqualsHour).isFalse();
    }

    @DisplayName("출석 분이 입력한 분 이후라면 true를 반환한다")
    @Test
    void isAfterMinute() {
        // given
        int hour = 10;
        int minute = 5;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);
        int compareHour = 2;

        //when
        boolean isAfterMinute = attendanceTime.isAfterMinute(compareHour);

        //then
        assertThat(isAfterMinute).isTrue();
    }

    @DisplayName("출석 분이 입력한 분 이후라면 false를 반환한다")
    @Test
    void isNotAfterMinute() {
        // given
        int hour = 10;
        int minute = 5;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);
        int compareHour = 7;

        //when
        boolean isNotAfterMinute = attendanceTime.isAfterMinute(compareHour);

        //then
        assertThat(isNotAfterMinute).isFalse();
    }

    @DisplayName("출석 시간이 입력한 시간과 동일하거나 크다면 true를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {10, 11})
    void isEqualOrAfterHour(int value) {
        // given
        int hour = value;
        int minute = 3;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);
        int compareHour = 10;

        //when
        boolean isEqualOrAfterHour = attendanceTime.isEqualOrAfterHour(compareHour);

        //then
        assertThat(isEqualOrAfterHour).isTrue();
    }

    @DisplayName("출석 시간이 입력한 시간보다 작다면 false를 반환한다")
    @Test
    void isNotEqualOrAfterHour() {
        // given
        int hour = 9;
        int minute = 3;
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);
        int compareHour = 10;

        //when
        boolean isNotEqualOrAfterHour = attendanceTime.isEqualOrAfterHour(compareHour);

        //then
        assertThat(isNotEqualOrAfterHour).isFalse();
    }
}
