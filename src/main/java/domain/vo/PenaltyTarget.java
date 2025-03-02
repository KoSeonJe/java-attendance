package domain.vo;

import domain.Penalty;

public record PenaltyTarget(
        String nickName,
        int lateCount,
        int absenceCount,
        Penalty penalty
) {

    public boolean isNone() {
        return penalty.isNone();
    }
}
