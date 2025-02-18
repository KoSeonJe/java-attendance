package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class AttendanceTest {


    @Test
    void 출석을_여러개_생성한다() {
        // given
        String name = "이름";
        List<LocalDateTime> dateTimes = List.of(LocalDateTime.now(), LocalDateTime.now().plusDays(1));

        // when
        Attendance attendance = new Attendance(name, dateTimes);

        // then
        assertSoftly(softly -> {
            softly.assertThat(attendance.getName())
                    .isEqualTo(name);
            softly.assertThat(attendance.getDateTimes())
                    .isEqualTo(dateTimes);
        });
    }
}
