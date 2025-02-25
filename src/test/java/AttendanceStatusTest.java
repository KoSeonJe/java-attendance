import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendanceStatusTest {

    @DisplayName("해당 요일의 시작 시간으로부터 5분 초과는 지각으로 간주한다")
    @Test
    void isLateByTime() {
        // given
        AttendanceTime attendanceTime = AttendanceTime.create(10, 10);
        int dayStartHour = 10;
        // when
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(dayStartHour, attendanceTime);

        // then
        assertThat(attendanceStatus).isEqualTo(AttendanceStatus.LATE);
    }
}
