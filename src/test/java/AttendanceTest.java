import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @DisplayName("출석 시간을 수정한다")
    @Test
    void updateAttendanceTime() {
        // given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        AttendanceTime attendanceTime = AttendanceTime.create(10, 12);
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(10, attendanceTime);
        Attendance attendance = Attendance.create(localDate, attendanceTime, attendanceStatus);

        int updateHour = 10;
        int updateMinute = 0;
        //when
        attendance.updateAttendanceTime(updateHour, updateMinute);

        // then
        assertSoftly(softly -> {
            softly.assertThat(attendance).extracting("attendanceTime").extracting("hour").isEqualTo(10);
            softly.assertThat(attendance).extracting("attendanceTime").extracting("minute").isEqualTo(0);
        });
    }

}
