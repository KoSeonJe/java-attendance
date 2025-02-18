package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class AttendanceTest {


    @Test
    void 출석을_생성한다() {
        // given
        String name = "이름";
        LocalDate date = LocalDate.now();

        // when
        Attendance attendance = new Attendance(name, date);

        // then
        assertSoftly(softly -> {
            softly.assertThat(attendance.getName())
                .isEqualTo(name);
            softly.assertThat(attendance.getDate())
                .isEqualTo(date);
        });
    }
}
