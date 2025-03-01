import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Attendance;
import domain.AttendanceRecords;
import domain.CrewAttendance;
import domain.CrewAttendanceBook;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewAttendanceBookTest {

    @DisplayName("등록되지 않은 크루 닉네임이라면 예외를 발생시킨다")
    @Test
    void notExistingAttendance() {
        // given
        String crewName = "웨이드";
        CrewAttendance crewAttendance = CrewAttendance.create(crewName, AttendanceRecords.create(List.of()));
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(List.of(crewAttendance));

        String anotherName = "웨이드2";

        // when & then
        assertThatThrownBy(
                () -> assertThat(crewAttendanceBook.retrieveAttendanceRecordsByName(anotherName))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 등록되지 않은 크루입니다.");
    }

    @DisplayName("크루 이름을 통해 출석 기록을 조회할 수 있다")
    @Test
    void retrieveAttendanceRecordsByName() {
        // given
        String crewName = "웨이드";
        AttendanceRecords attendanceRecords = AttendanceRecords.create(List.of());
        CrewAttendance crewAttendance = CrewAttendance.create(crewName, attendanceRecords);
        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(List.of(crewAttendance));

        // when
        AttendanceRecords foundRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(crewName);

        // then
        assertThat(foundRecords).isSameAs(attendanceRecords);
    }

    @DisplayName("모든 크루 출석기록 중 가장 오래된 날짜를 찾아 반환한다")
    @Test
    void retrieveOldestDayInBook() {
        // given
        String crewName = "웨이드";
        LocalDate today = LocalDate.of(2024, 12, 13);
        Attendance attendance = AttendanceFixture.createAttendance(today, 10, 0);
        AttendanceRecords attendanceRecords = AttendanceRecords.create(new ArrayList<>(List.of(attendance)));
        CrewAttendance crewAttendance = CrewAttendance.create(crewName, attendanceRecords);

        String crewName2 = "웨이드2";
        LocalDate date = LocalDate.of(2024, 12, 12);
        Attendance attendance2 = AttendanceFixture.createAttendance(date, 10, 0);
        AttendanceRecords attendanceRecords2 = AttendanceRecords.create(new ArrayList<>(List.of(attendance2)));
        CrewAttendance crewAttendance2 = CrewAttendance.create(crewName2, attendanceRecords2);

        String crewName3 = "웨이드3";
        LocalDate oldest = LocalDate.of(2024, 12, 11);
        Attendance attendance3 = AttendanceFixture.createAttendance(oldest, 10, 0);
        AttendanceRecords attendanceRecords3 = AttendanceRecords.create(new ArrayList<>(List.of(attendance3)));
        CrewAttendance crewAttendance3 = CrewAttendance.create(crewName3, attendanceRecords3);

        CrewAttendanceBook crewAttendanceBook = CrewAttendanceBook.create(
                List.of(crewAttendance, crewAttendance2, crewAttendance3));
        // when
        LocalDate oldestDayInBook = crewAttendanceBook.retrieveOldestDayInBook();

        // then
        assertThat(oldestDayInBook).isSameAs(oldest);
    }
}
