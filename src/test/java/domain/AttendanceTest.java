package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            softly.assertThat(crewAttendance.retrieveDateTimesOrderByDate().size())
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
        assertThatThrownBy(() -> crewAttendance.addDateTime(new AttendanceDateTime(
                new Date(LocalDate.of(2024, 12, 2)),
                new Time(13, 4)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 날짜의 출석 정보가 이미 존재합니다.");
    }

    @Test
    void 출석을_한다() {
        // given
        Map<Date, Time> dateTimes = new HashMap<>();
        Attendance attendance = new Attendance(dateTimes);

        // when
        attendance.addDateTime(new AttendanceDateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4)));

        // then
        assertThat(attendance.retrieveDateTimesOrderByDate().get(0))
                .isEqualTo(new AttendanceDateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(13, 4)));
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
                new AttendanceDateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(10, 5))
        );

        //when
        assertThat(attendance.retrieveDateTimesOrderByDate().getFirst())
                .isEqualTo(new AttendanceDateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(10, 5)));
    }

    @Test
    void 출석이_없으면_수정하지_못한다() {
        // given
        Map<Date, Time> dateTimes = new HashMap<>();
        Attendance attendance = new Attendance(dateTimes);

        // when & then
        assertThatThrownBy(() -> attendance.updateDateTime(
                new AttendanceDateTime(new Date(LocalDate.of(2024, 12, 2)), new Time(10, 5)
                )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 날짜의 출석 정보가 없습니다.");
    }

    @Test
    void 원하는_날짜를_입력받아_저장되어있는_출석날짜와_시간을_생성하여_반환한다() {
        // given
        LocalDate rawDate = LocalDate.of(2024, 12, 2);
        Time attendanceTime = new Time(13, 4);
        Date attendanceDate = new Date(rawDate);
        Map<Date, Time> dateTimes = Map.of(attendanceDate, attendanceTime);
        Attendance attendance = new Attendance(dateTimes);

        // when
        AttendanceDateTime attendanceDateTime = attendance.retrieveDateTime(new Date(rawDate));

        // then
        assertSoftly(softly -> {
                    softly.assertThat(attendanceDateTime.getDate()).isEqualTo(attendanceDate);
                    softly.assertThat(attendanceDateTime.getTime()).isEqualTo(attendanceTime);
                }
        );
    }

    @Test
    void 모든_출석_날짜와_시간을_조회할_수_있다() {
        // given
        Date attendanceDate = new Date(LocalDate.of(2024, 12, 2));
        Time attendanceTime = new Time(13, 4);
        Date attendanceDate2 = new Date(LocalDate.of(2024, 12, 3));
        Time attendanceTime2 = new Time(10, 4);
        Map<Date, Time> dateTimes = Map.of(
                attendanceDate, attendanceTime,
                attendanceDate2, attendanceTime2
        );
        Attendance attendance = new Attendance(dateTimes);

        //when
        List<AttendanceDateTime> attendanceDateTimes = attendance.retrieveDateTimesOrderByDate();

        //then
        assertSoftly(softly -> {
            softly.assertThat(attendanceDateTimes.size()).isEqualTo(2);
            softly.assertThat(attendanceDateTimes.getFirst().getDate()).isEqualTo(attendanceDate);
            softly.assertThat(attendanceDateTimes.getFirst().getTime()).isEqualTo(attendanceTime);
            softly.assertThat(attendanceDateTimes.get(1).getDate()).isEqualTo(attendanceDate2);
            softly.assertThat(attendanceDateTimes.get(1).getTime()).isEqualTo(attendanceTime2);
        });
    }

    @Test
    void 원하는_날짜의_출석상태를_조회할_수_있다() {
        // given
        Date attendanceDate = new Date(LocalDate.of(2024, 12, 2));
        Time attendanceTime = new Time(13, 4);
        Map<Date, Time> dateTimes = Map.of(attendanceDate, attendanceTime);
        Attendance attendance = new Attendance(dateTimes);

        // when
        AttendanceStatus attendanceStatus = attendance.retrieveAttendanceStatus(attendanceDate);

        // then
        assertThat(attendanceStatus).isEqualTo(AttendanceStatus.ATTENDANCE);
    }

    @Test
    void 모든_출석_상태를_조회할_수_있다() {
        // given
        Date attendanceDate = new Date(LocalDate.of(2024, 12, 2));
        Time attendanceTime = new Time(13, 8);
        Date attendanceDate2 = new Date(LocalDate.of(2024, 12, 3));
        Time attendanceTime2 = new Time(10, 35);
        Map<Date, Time> dateTimes = Map.of(
                attendanceDate, attendanceTime,
                attendanceDate2, attendanceTime2
        );
        Attendance attendance = new Attendance(dateTimes);

        // when
        List<AttendanceStatus> attendanceStatuses = attendance.retrieveAttendanceStatuses();

        //then
        assertSoftly(softly -> {
            softly.assertThat(attendanceStatuses.size()).isEqualTo(2);
            softly.assertThat(attendanceStatuses.getFirst()).isEqualTo(AttendanceStatus.PERCEPTION);
            softly.assertThat(attendanceStatuses.get(1)).isEqualTo(AttendanceStatus.ABSENCE);
        });
    }

    @Test
    void 각_출석_상태의_횟수를_조회할_수_있다() {
        // given
        Date attendanceDate = new Date(LocalDate.of(2024, 12, 2));
        Time attendanceTime = new Time(13, 8);
        Date attendanceDate2 = new Date(LocalDate.of(2024, 12, 3));
        Time attendanceTime2 = new Time(10, 35);
        Map<Date, Time> dateTimes = Map.of(
                attendanceDate, attendanceTime,
                attendanceDate2, attendanceTime2
        );
        Attendance attendance = new Attendance(dateTimes);

        //when
        Map<AttendanceStatus, Integer> attendanceStatusCount = attendance.retrieveAttendanceStatusCount();

        //then
        assertSoftly(softly -> {
            softly.assertThat(attendanceStatusCount.get(AttendanceStatus.ATTENDANCE)).isEqualTo(0);
            softly.assertThat(attendanceStatusCount.get(AttendanceStatus.PERCEPTION)).isEqualTo(1);
            softly.assertThat(attendanceStatusCount.get(AttendanceStatus.ABSENCE)).isEqualTo(1);
        });
    }
}
