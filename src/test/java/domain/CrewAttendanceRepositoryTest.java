package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CrewAttendanceRepositoryTest {

    @BeforeEach
    void setUp() {
        CrewAttendanceRepository crewAttendanceRepository = CrewAttendanceRepository.getInstance();
        crewAttendanceRepository.clear();
    }

    @Test
    void 출석을_저장한다() {
        // given
        CrewAttendanceRepository crewAttendanceRepository = CrewAttendanceRepository.getInstance();
        Crew crew = new Crew("이름");
        CrewAttendance crewAttendance = new CrewAttendance(
                crew,
                new Attendance(Map.of(new Date(LocalDate.of(2024, 12, 13)), new Time(10, 0)))
        );

        // when
        crewAttendanceRepository.save(crewAttendance);

        // then
        CrewAttendance savedCrewAttendance = crewAttendanceRepository.findByEqualsNickName("이름");
        Assertions.assertThat(crewAttendance)
                .isEqualTo(savedCrewAttendance);
    }

    @Test
    void 출석을_찾는다() {
        // given
        CrewAttendanceRepository crewAttendanceRepository = CrewAttendanceRepository.getInstance();
        Crew crew = new Crew("이름");
        CrewAttendance crewAttendance = new CrewAttendance(
                crew,
                new Attendance(Map.of(new Date(LocalDate.of(2024, 12, 13)), new Time(10, 0)))
        );
        crewAttendanceRepository.save(crewAttendance);

        // when
        CrewAttendance foundCrewAttendance = crewAttendanceRepository.findByEqualsNickName("이름");

        // then
        Assertions.assertThat(crewAttendance)
                .isEqualTo(foundCrewAttendance);
    }


    @Test
    void 출석을_모두_찾는다() {
        // given
        CrewAttendanceRepository crewAttendanceRepository = CrewAttendanceRepository.getInstance();
        Crew crew = new Crew("이름");
        CrewAttendance crewAttendance = new CrewAttendance(
                crew,
                new Attendance(Map.of(new Date(LocalDate.of(2024, 12, 13)), new Time(10, 0)))
        );
        Crew crew2 = new Crew("이름2");
        CrewAttendance crewAttendance2 = new CrewAttendance(
                crew2,
                new Attendance(Map.of(new Date(LocalDate.of(2024, 12, 12)), new Time(10, 0)))
        );

        crewAttendanceRepository.save(crewAttendance);
        crewAttendanceRepository.save(crewAttendance2);

        // when
        List<CrewAttendance> crewAttendances = crewAttendanceRepository.findAll();

        // then
        Assertions.assertThat(crewAttendances)
                .containsExactlyInAnyOrder(crewAttendance, crewAttendance2);
    }

    @Test
    void 존재하지_않는_크루는_찾지_못한다() {
        // given
        CrewAttendanceRepository crewAttendanceRepository = CrewAttendanceRepository.getInstance();

        // when & then
        Assertions.assertThatThrownBy(() -> crewAttendanceRepository.findByEqualsNickName("이름"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 이름의 출석 정보가 없습니다.");
    }
}
