import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendanceManagerTest {

    @DisplayName("출석을 처리한다")
    @Test
    void throwExceptionIfAttendingOnWeekendOrHoliday() {
        // given
        String crewName = "웨이드";
        LocalDate attendanceDate = LocalDate.of(2024, 12, 13);
        AttendanceTime attendanceTime = AttendanceTime.create(10, 0);

        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(List.of()));
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(
                List.of(CrewAttendance.create(crewName, attendanceRecords))
        );
        AttendanceManager attendanceManager = new AttendanceManager(crewAttendanceBook);

        //when
        attendanceManager.processAttendance(crewName, attendanceDate, attendanceTime);

        //then
        AttendanceRecords foundAttendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);
        Attendance attendance = foundAttendanceRecords.retrieveAttendanceByDate(attendanceDate);

        assertThat(attendance.equals(
                Attendance.create(attendanceDate, attendanceTime, AttendanceStatus.ATTENDANCE))).isTrue();
    }

    @DisplayName("입력 날짜까지 모든 출석 기록을 조회한다")
    @Test
    void retrieveAllAttendanceRecordsWithEmpty() {
        // given
        String crewName = "웨이드";
        LocalDate localDate1 = LocalDate.of(2024, 12, 10);
        LocalDate localDate2 = LocalDate.of(2024, 12, 11);
        LocalDate localDate3 = LocalDate.of(2024, 12, 12);

        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(List.of(
                AttendanceFixture.createAttendance(localDate1, 10, 0),
                AttendanceFixture.createAttendance(localDate2, 10, 0),
                AttendanceFixture.createAttendance(localDate3, 10, 0)
        )));
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(
                List.of(CrewAttendance.create(crewName, attendanceRecords))
        );
        AttendanceManager attendanceManager = new AttendanceManager(crewAttendanceBook);

        // when
        List<Attendance> attendances = attendanceManager.retrieveAllWithEmptyUntilDate(crewName, localDate3);

        // then
        assertThat(attendances).hasSize(2);
    }
}
