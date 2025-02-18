package domain;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AttendanceRepositoryTest {

    @Test
    void 출석을_저장한다() {
        // given
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        Attendance attendance = new Attendance("이름", List.of(LocalDateTime.now()));

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
        Attendance attendance = new Attendance("이름", List.of(LocalDateTime.now()));
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
        Attendance attendance1 = new Attendance("이름1", List.of(LocalDateTime.now()));
        attendanceRepository.save(attendance1);
        Attendance attendance2 = new Attendance("이름2", List.of(LocalDateTime.now()));
        attendanceRepository.save(attendance2);

        // when
        List<Attendance> attendances = attendanceRepository.findAll();

        // then
        Assertions.assertThat(attendances)
                .isEqualTo(List.of(attendance1, attendance2));
    }

    @Test
    void 존재하지_않는_닉네임은_찾지_못한다() {
        // given
        AttendanceRepository attendanceRepository = new AttendanceRepository();

        // when & then
        Assertions.assertThatThrownBy(() -> attendanceRepository.findByName("이름"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 이름의 출석 정보가 없습니다.");
    }
}
