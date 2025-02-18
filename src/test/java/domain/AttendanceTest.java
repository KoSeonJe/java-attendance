package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
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

    @Test
    void 출석을_저장한다() {
        // given
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        Attendance attendance = new Attendance("이름", LocalDate.now());

        // when
        attendanceRepository.save(attendance);

        // then
        Attendance savedAttendance = attendanceRepository.get("이름");
        Assertions.assertThat(attendance)
                .isEqualTo(savedAttendance);
    }
}
