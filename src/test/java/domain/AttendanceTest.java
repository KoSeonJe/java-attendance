package domain;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AttendanceTest {

    @Test
    void 출석을_여러개_생성한다() {
        // given
        Map<Date, Time> dateTimes = Map.of(
                new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4),
                new Date(LocalDate.of(2024, 12, 3)), new Time(13, 5)
        );

        // when
        Attendance crewAttendance = new Attendance(dateTimes);

        // then
        assertSoftly(softly -> {
            softly.assertThat(crewAttendance.retrieveDateTimes().size())
                    .isEqualTo(dateTimes.size());
        });
    }

    @Test
    void 출석을_다시_할_수_없다() {
        // given
        Map<Date, Time> dateTimes = Map.of(
                new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4),
                new Date(LocalDate.of(2024, 12, 3)), new Time(13, 5)
        );
        Attendance crewAttendance = new Attendance(dateTimes);

        // when & then
        Assertions.assertThatThrownBy(() -> crewAttendance.addDateTime(new DateTime(
                        new Date(LocalDate.of(2024, 12, 2)),
                        new Time(13, 4)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 날짜의 출석 정보가 이미 존재합니다.");
    }

    @Test
    void 출석을_한다() {
        // given
        Map<Date, Time> dateTimes = Map.of(new Date(LocalDate.of(2024, 12, 2)), new Time(null, null));
        Attendance attendance = new Attendance(dateTimes);

        // when
        attendance.addDateTime(new DateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4)));

        // then
        Assertions.assertThat(attendance.retrieveDateTimes().get(0))
                .isEqualTo(new DateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4)));
    }

    @Test
    void 출석을_수정한다() {
        // given
        Map<Date, Time> dateTimes = Map.of(
                new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4)
        );
        Attendance attendance = new Attendance(dateTimes);

        //given
        attendance.updateDateTime(
                new DateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(10, 5))
        );

        //when
        Assertions.assertThat(attendance.retrieveDateTimes().getFirst())
                .isEqualTo(new DateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(10, 5)));
    }

    @Test
    void 출석이_없으면_수정하지_못한다() {
        // given
        Map<Date, Time> dateTimes = Map.of(
                new Date(LocalDate.of(2024, 12, 2)), new Time(null, null)
        );
        Attendance attendance = new Attendance(dateTimes);

        // when & then
        Assertions.assertThatThrownBy(() -> attendance.updateDateTime(
                        new DateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(10, 5)
                        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 날짜의 출석 정보가 없습니다.");
    }
}
