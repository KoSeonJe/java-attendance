import static org.assertj.core.api.Assertions.*;

import domain.Attendance;
import domain.AttendanceManager;
import domain.AttendanceRecords;
import domain.AttendanceStatus;
import domain.AttendanceTime;
import domain.CrewAttendance;
import domain.CrewAttendanceBook;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AttendanceManagerTest {

    @DisplayName("출석을 처리한다")
    @Test
    void processAttendance() {
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

    @DisplayName("출석 처리시 등록되지 않은 크루원이라면 새로 추가한다")
    @Test
    void notExistCrew() {
        // given
        String crewName = "웨이드";
        LocalDate attendanceDate = LocalDate.of(2024, 12, 13);
        AttendanceTime attendanceTime = AttendanceTime.create(10, 0);

        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.createEmpty();
        AttendanceManager attendanceManager = new AttendanceManager(crewAttendanceBook);

        // when
        attendanceManager.processAttendance(crewName, attendanceDate, attendanceTime);

        // then
        assertThat(crewAttendanceBook.existCrew(crewName)).isTrue();
    }

    @DisplayName("주말이나 공휴일에 출석할 시 예외를 발생시킨다")
    @Test
    void throwExceptionIfAttendingOnWeekendOrHoliday() {
        // given
        String crewName = "웨이드";
        LocalDate attendanceDate = LocalDate.of(2024, 12, 14);
        AttendanceTime attendanceTime = AttendanceTime.create(10, 0);

        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(List.of()));
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(
                List.of(CrewAttendance.create(crewName, attendanceRecords))
        );
        AttendanceManager attendanceManager = new AttendanceManager(crewAttendanceBook);

        //when
        assertThatThrownBy(
                () -> attendanceManager.processAttendance(crewName, attendanceDate, attendanceTime)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출석할 수 없는 날입니다.");
    }

    @DisplayName("입력 날짜까지 모든 출석 기록을 조회한다")
    @Test
    void retrieveAllAttendanceRecords() {
        // given
        String crewName = "웨이드";
        LocalDate oldest = LocalDate.of(2024, 12, 10);
        LocalDate localDate2 = LocalDate.of(2024, 12, 11);
        LocalDate today = LocalDate.of(2024, 12, 12);

        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(List.of(
                AttendanceFixture.createAttendance(oldest, 10, 0),
                AttendanceFixture.createAttendance(localDate2, 10, 0),
                AttendanceFixture.createAttendance(today, 10, 0)
        )));
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(
                List.of(CrewAttendance.create(crewName, attendanceRecords))
        );
        AttendanceManager attendanceManager = new AttendanceManager(crewAttendanceBook);

        // when
        List<Attendance> attendances = attendanceManager.retrieveFilledAttendanceUntilDate(crewName, today);

        // then
        assertThat(attendances).hasSize(2);
    }

    @DisplayName("입력 날짜까지 출석 기록 조회시, 공휴일 및 주말을 제외하고 출석 기록이 없는 날짜에 결석 기록을 추가하여 반환한다")
    @Test
    void retrieveFilledAttendanceUntilDate() {
        // given
        String crewName = "웨이드";
        LocalDate oldest = LocalDate.of(2024, 12, 10);
        LocalDate localDate2 = LocalDate.of(2024, 12, 11);
        LocalDate today = LocalDate.of(2024, 12, 17);

        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(List.of(
                AttendanceFixture.createAttendance(oldest, 10, 0),
                AttendanceFixture.createAttendance(localDate2, 10, 0),
                AttendanceFixture.createAttendance(today, 10, 0)
        )));
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(
                List.of(CrewAttendance.create(crewName, attendanceRecords))
        );
        AttendanceManager attendanceManager = new AttendanceManager(crewAttendanceBook);

        // when
        List<Attendance> attendances = attendanceManager.retrieveFilledAttendanceUntilDate(crewName, today);

        // then
        assertThat(attendances).hasSize(5);
    }
}
