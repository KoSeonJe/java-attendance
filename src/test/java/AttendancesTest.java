import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendancesTest {

    @DisplayName("출석을 추가하였을 때 이미 출석한 기록이 있다면 예외를 발생시킨다")
    @Test
    void validateExistByDate() {
        //given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        AttendanceTime attendanceTime = AttendanceTime.create(10, 12);
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(10, attendanceTime);
        Attendance attendance = Attendance.create(localDate, attendanceTime, attendanceStatus);

        Attendances attendances = Attendances.create(new ArrayList<>(List.of(attendance)));

        // when && then
        assertThatThrownBy(
                () -> attendances.add(Attendance.create(localDate, attendanceTime, attendanceStatus))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 날짜에 출석 기록이 이미 존재합니다");
    }

    @DisplayName("출석을 추가할 수 있다")
    @Test
    void add() {
        //given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        AttendanceTime attendanceTime = AttendanceTime.create(10, 12);
        AttendanceStatus attendanceStatus = AttendanceStatus.findByStartHourAndAttendanceTime(10, attendanceTime);
        Attendance attendance = Attendance.create(localDate, attendanceTime, attendanceStatus);
        Attendances attendances = Attendances.create(new ArrayList<>(List.of()));

        // when
        attendances.add(attendance);

        // then
        assertThat(attendances).extracting("attendances").asInstanceOf(InstanceOfAssertFactories.LIST)
                .contains(attendance);
    }

    @DisplayName("원하는 날짜의 출석을 조회할 수 있다")
    @Test
    void retrieveByDate() {
        //given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        Attendance attendance = Attendance.create(localDate, null, null);
        Attendances attendances = Attendances.create(new ArrayList<>(List.of(attendance)));

        //when
        Attendance foundAttendance = attendances.retrieveAttendanceByDate(localDate);

        //then
        assertThat(foundAttendance).isSameAs(attendance);
    }

    @DisplayName("조회하고자 하는 날의 출석기록이 없다면 예외를 발생시킨다")
    @Test
    void retrieveException() {
        //given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        Attendance attendance = Attendance.create(localDate, null, null);
        Attendances attendances = Attendances.create(new ArrayList<>(List.of(attendance)));

        LocalDate anotherDate = LocalDate.of(2024, 12, 12);
        //when && then
        assertThatThrownBy(() -> attendances.updateAttendanceByDate(anotherDate, 0, 0, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 날짜의 출석 기록이 없습니다.");
    }

    @DisplayName("원하는 날짜의 출석을 수정할 수 있다")
    @Test
    void updateByDate() {
        //given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        Attendance attendance = Attendance.create(localDate, null, null);
        Attendances attendances = Attendances.create(new ArrayList<>(List.of(attendance)));

        int hour = 10;
        int minute = 0;
        AttendanceStatus updateAttendanceStatus = AttendanceStatus.ATTENDANCE;

        //when
        attendances.updateAttendanceByDate(localDate, hour, minute, updateAttendanceStatus);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(attendance).extracting("attendanceTime").extracting("hour").isEqualTo(10);
            softly.assertThat(attendance).extracting("attendanceTime").extracting("minute").isEqualTo(0);
            softly.assertThat(attendance).extracting("attendanceStatus").isEqualTo(AttendanceStatus.ATTENDANCE);
        });
    }

    @DisplayName("수정하고자 하는 날의 출석기록이 없다면 예외를 발생시킨다")
    @Test
    void updateException() {
        //given
        LocalDate localDate = LocalDate.of(2024, 12, 13);
        Attendance attendance = Attendance.create(localDate, null, null);
        Attendances attendances = Attendances.create(new ArrayList<>(List.of(attendance)));

        int hour = 10;
        int minute = 0;
        AttendanceStatus updateAttendanceStatus = AttendanceStatus.ATTENDANCE;

        LocalDate anotherDate = LocalDate.of(2024, 12, 12);
        //when && then
        assertThatThrownBy(() -> attendances.updateAttendanceByDate(anotherDate, hour, minute, updateAttendanceStatus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 날짜의 출석 기록이 없습니다.");
    }
}
