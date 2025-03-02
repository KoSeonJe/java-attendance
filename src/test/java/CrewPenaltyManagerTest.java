import static org.assertj.core.api.Assertions.assertThat;

import domain.Attendance;
import domain.AttendanceRecords;
import domain.AttendanceStatusCalculator;
import domain.CrewAttendance;
import domain.CrewAttendanceBook;
import domain.CrewPenaltyManager;
import domain.Penalty;
import domain.vo.PenaltyTarget;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewPenaltyManagerTest {

    @DisplayName("크루의 제적 위험자 정보를 조회할 수 있다")
    @Test
    void retrieveAllPenaltyTarget() {
        // given
        String nickName = "웨이드";
        Attendance attendance1 = Attendance.createAbsenceAttendance(LocalDate.of(2024, 12, 10));
        Attendance attendance2 = Attendance.createAbsenceAttendance(LocalDate.of(2024, 12, 11));
        Attendance attendance3 = Attendance.createAbsenceAttendance(LocalDate.of(2024, 12, 12));
        Attendance attendance4 = Attendance.createAbsenceAttendance(LocalDate.of(2024, 12, 13));
        List<Attendance> attendances = List.of(attendance1, attendance2, attendance3, attendance4);

        CrewPenaltyManager crewPenaltyManager = new CrewPenaltyManager(new AttendanceStatusCalculator());
        // when
        PenaltyTarget penaltyTarget = crewPenaltyManager.retrieveAllPenaltyTarget(nickName, attendances);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(penaltyTarget.penalty()).isEqualTo(Penalty.INTERVIEW);
        });
    }

}
