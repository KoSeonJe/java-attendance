import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PenaltyTest {

    @DisplayName("결석 5회 초과이면 제적이다")
    @Test
    void isWeeding() {
        //given
        int absenceCount = 6;

        //when
        Penalty penalty = Penalty.judge(absenceCount);

        //then
        Assertions.assertThat(penalty).isEqualTo(Penalty.WEEDING);
    }

    @DisplayName("결석 3회 이상이면 면담이다")
    @Test
    void isInterview() {
        //given
        int absenceCount = 4;

        //when
        Penalty penalty = Penalty.judge(absenceCount);

        //then
        Assertions.assertThat(penalty).isEqualTo(Penalty.INTERVIEW);
    }

    @DisplayName("결석 2회 이상이면 경고이다")
    @Test
    void isWarning() {
        //given
        int absenceCount = 2;

        //when
        Penalty penalty = Penalty.judge(absenceCount);

        //then
        Assertions.assertThat(penalty).isEqualTo(Penalty.WARNING);
    }
}
