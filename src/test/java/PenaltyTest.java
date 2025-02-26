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

}
