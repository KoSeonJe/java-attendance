import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
