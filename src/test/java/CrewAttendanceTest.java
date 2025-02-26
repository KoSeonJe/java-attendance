import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewAttendanceTest {

    @DisplayName("닉네임을 통해 입력한 날짜 전날까지 출석 기록을 조회할 수 있다")
    @Test
    void retrieveAllAttendanceByName() {
        // given
        String crewName = "웨이드";
        LocalDate date = LocalDate.of(2024, 12, 10);
        LocalDate date2 = LocalDate.of(2024, 12, 11);
        LocalDate date3 = LocalDate.of(2024, 12, 12);
        AttendanceTime time = AttendanceTime.create(10, 0);
        AttendanceStatus attendanceStatus = AttendanceStatus.ATTENDANCE;

        Attendance attendance1 = Attendance.create(date, time, attendanceStatus);
        Attendance attendance2 = Attendance.create(date2, time, attendanceStatus);
        Attendance attendance3 = Attendance.create(date3, time, attendanceStatus);

        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(
                List.of(attendance1, attendance2, attendance3)
        ));

        CrewAttendance crewAttendance = CrewAttendance.create(crewName, attendanceRecords);

        // when
        List<Attendance> attendances = crewAttendance.retrieveAllByDate(date3);

        //then
        assertThat(attendances).contains(attendance1, attendance2);
    }
}
