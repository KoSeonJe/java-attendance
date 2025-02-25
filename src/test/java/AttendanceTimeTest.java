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

        // where
        AttendanceTime attendanceTime = AttendanceTime.create(hour, minute);

        // when
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(attendanceTime)
                    .extracting("hour").isEqualTo(10);
            softly.assertThat(attendanceTime)
                    .extracting("minute").isEqualTo(30);
        });
    }

}
