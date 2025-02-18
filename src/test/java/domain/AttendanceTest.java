package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDate;
import java.util.List;
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
        Attendance savedAttendance = attendanceRepository.findByName("이름");
        Assertions.assertThat(attendance)
                .isEqualTo(savedAttendance);
    }

    @Test
    void 출석을_찾는다() {
        // given
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        Attendance attendance = new Attendance("이름", LocalDate.now());
        attendanceRepository.save(attendance);

        // when
        Attendance foundAttendance = attendanceRepository.findByName("이름");

        // then
        Assertions.assertThat(attendance)
                .isEqualTo(foundAttendance);
    }

    @Test
    void 출석을_모두_찾는다() {
        // given
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        Attendance attendance1 = new Attendance("이름1", LocalDate.now());
        attendanceRepository.save(attendance1);
        Attendance attendance2 = new Attendance("이름2", LocalDate.now());
        attendanceRepository.save(attendance2);

        // when
        List<Attendance> attendances = attendanceRepository.findAll();

        // then
        Assertions.assertThat(attendances)
                .isEqualTo(List.of(attendance1, attendance2));
    }
}
