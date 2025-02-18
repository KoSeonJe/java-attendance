package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
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

    @Test
    void 출석을_다시_할_수_없다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Attendance attendance = new Attendance("이름", List.of(dateTime));

        // when & then
        Assertions.assertThatThrownBy(() -> attendance.addAttendance(dateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 이름의 출석 정보가 이미 존재합니다.");
    }

    @Test
    void 출석을_한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Attendance attendance = new Attendance("이름", new ArrayList<>(List.of()));

        // when
        attendance.addAttendance(dateTime);

        // then
        Assertions.assertThat(attendance.getDateTimes())
                .isEqualTo(List.of(dateTime));
    }

    @Test
    void 출석을_수정한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Attendance attendance = new Attendance("이름", new ArrayList<>(List.of(dateTime)));

        //given
        LocalDateTime updateDateTime = LocalDateTime.of(2024, 12, 2, 13, 6);
        attendance.updateAttendance(updateDateTime);

        //when
        Assertions.assertThat(attendance.getDateTimes())
                .isEqualTo(List.of(updateDateTime));
    }

    @Test
    void 출석이_없으면_수정하지_못한다() {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2024, 12, 2, 13, 4);
        Attendance attendance = new Attendance("이름", new ArrayList<>(List.of()));

        // when & then
        Assertions.assertThatThrownBy(() -> attendance.updateAttendance(dateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 이름의 출석 정보가 없습니다.");
    }
}
