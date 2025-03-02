package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.vo.PenaltyTarget;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PenaltyTargetComparatorTest {

    @DisplayName("결석이 많은 순으로 정렬한다.")
    @Test
    void compareByAbsence() {
        // given
        PenaltyTarget target1 = new PenaltyTarget("웨이드1", 5, 2, Penalty.INTERVIEW);
        PenaltyTarget target2 = new PenaltyTarget("웨이드2", 3, 1, Penalty.WARNING);
        PenaltyTarget target3 = new PenaltyTarget("웨이드3", 0, 4, Penalty.INTERVIEW);

        List<PenaltyTarget> targets = new ArrayList<>(List.of(target1, target2, target3));

        // when
        targets.sort(new PenaltyTargetComparator());

        // then
        assertThat(targets).containsExactly(target3, target1, target2);
    }

    @DisplayName("결석 수가 같다면 이름 순으로 정렬한다")
    @Test
    void compareByName() {
        // given
        PenaltyTarget target1 = new PenaltyTarget("웨이드1", 6, 1, Penalty.INTERVIEW);
        PenaltyTarget target2 = new PenaltyTarget("웨이드2", 3, 2, Penalty.INTERVIEW);
        PenaltyTarget target3 = new PenaltyTarget("웨이드3", 3, 2, Penalty.INTERVIEW);

        List<PenaltyTarget> targets = new ArrayList<>(List.of(target1, target2, target3));

        // when
        targets.sort(new PenaltyTargetComparator());

        // then
        assertThat(targets).containsExactly(target1, target2, target3);
    }
}
