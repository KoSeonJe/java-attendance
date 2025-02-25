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

    @DisplayName("분의 범위를 벗어나면 예외를 발생시킨다")
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
}
