import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendanceStatusCounterTest {


    @DisplayName("출석들의 누적 출석을 조회한다")
    @Test
    void retrieveAttendanceCount() {
        // given
        Map<AttendanceStatus, Integer> attendanceStatusCounts = new HashMap<>(
                Map.of(AttendanceStatus.ATTENDANCE, 1)
        );
        AttendanceStatusCounter attendanceStatusCounter = AttendanceStatusCounter.create(attendanceStatusCounts);

        // when
        int attendanceStatusCount = attendanceStatusCounter.retrieveAttendanceStatusCount(AttendanceStatus.ATTENDANCE);

        //then
        Assertions.assertThat(attendanceStatusCount).isEqualTo(1);
    }
}
