package domain;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CrewRepositoryTest {

    @BeforeEach
    void setUp() {
        CrewRepository crewRepository = CrewRepository.getInstance();
        crewRepository.clear();
    }

    @Test
    void 출석을_저장한다() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        Crew crew = new Crew("이름", new Attendance(List.of(LocalDateTime.now())));

        // when
        crewRepository.save(crew);

        // then
        Crew savedCrew = crewRepository.findByName("이름");
        Assertions.assertThat(crew)
                .isEqualTo(savedCrew);
    }

    @Test
    void 출석을_찾는다() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        Crew crew = new Crew("이름", new Attendance(List.of(LocalDateTime.now())));
        crewRepository.save(crew);

        // when
        Crew foundCrew = crewRepository.findByName("이름");

        // then
        Assertions.assertThat(crew)
                .isEqualTo(foundCrew);
    }


    @Test
    void 출석을_모두_찾는다() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        Crew crew1 = new Crew("이름1", new Attendance(List.of(LocalDateTime.now())));
        Crew crew2 = new Crew("이름2", new Attendance(List.of(LocalDateTime.now())));
        crewRepository.save(crew1);
        crewRepository.save(crew2);

        // when
        List<Crew> crews = crewRepository.findAll();

        // then
        Assertions.assertThat(crews)
                .containsExactlyInAnyOrder(crew1, crew2);
    }

    @Test
    void 존재하지_않는_닉네임은_찾지_못한다() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();

        // when & then
        Assertions.assertThatThrownBy(() -> crewRepository.findByName("이름"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 이름의 출석 정보가 없습니다.");
    }
}
