import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CrewPenaltyManagerTest {

    @DisplayName("지각 3회당 결석 1번이다")
    @Test
    void convertLateToAbsence() {
        // given
        int late = 7;
        int absence = 3;

        CrewPenaltyManager crewPenaltyManager = new CrewPenaltyManager();

        // when
        int totalAbsence = crewPenaltyManager.calculateTotalAbsence(late, absence);

        // then
        assertThat(totalAbsence).isEqualTo(5);
    }
}
