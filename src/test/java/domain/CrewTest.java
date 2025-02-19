package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CrewTest {
    
    @Test
    void 출석을_여러개_생성한다() {
        // given
        String name = "이름";
        List<LocalDateTime> dateTimes = List.of(LocalDateTime.now(), LocalDateTime.now().plusDays(1));

        // when
        Crew crew = new Crew(name, new Attendance(dateTimes));

        // then
        assertSoftly(softly -> {
            softly.assertThat(crew.getName())
                    .isEqualTo(name);
            softly.assertThat(crew.getDateTimes())
                    .isEqualTo(dateTimes);
        });
    }

    @Test
    void 출석을_다시_할_수_없다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Crew crew = new Crew("이름", new Attendance(List.of(dateTime)));

        // when & then
        Assertions.assertThatThrownBy(() -> crew.addAttendance(dateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 날짜의 출석 정보가 이미 존재합니다.");
    }

    @Test
    void 출석을_한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Crew crew = new Crew("이름", new Attendance(List.of()));

        // when
        crew.addAttendance(dateTime);

        // then
        Assertions.assertThat(crew.getDateTimes())
                .isEqualTo(List.of(dateTime));
    }

    @Test
    void 출석을_수정한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Crew crew = new Crew("이름", new Attendance(List.of(dateTime)));

        //given
        LocalDateTime updateDateTime = LocalDateTime.of(2024, 12, 2, 13, 6);
        crew.updateAttendance(updateDateTime);

        //when
        Assertions.assertThat(crew.getDateTimes())
                .isEqualTo(List.of(updateDateTime));
    }

    @Test
    void 출석이_없으면_수정하지_못한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Crew crew = new Crew("이름", new Attendance(List.of()));

        // when & then
        Assertions.assertThatThrownBy(() -> crew.updateAttendance(dateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 날짜의 출석 정보가 없습니다.");
    }
}
