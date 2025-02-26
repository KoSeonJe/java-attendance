import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
}
