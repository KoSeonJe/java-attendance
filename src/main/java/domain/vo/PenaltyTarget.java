package domain.vo;

import domain.Penalty;

public record PenaltyTarget(
        String nickName,
        int lateCount,
        int absenceCount,
        Penalty penalty
) {

}
