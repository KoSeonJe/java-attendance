package domain;

import java.util.List;

public enum Penalty {
    NONE("없음", 0),
    WARNING("경고", 2),
    INTERVIEW("면담", 3),
    WEEDING("제적", 5);

    private static final int INCREMENT_COUNT = 1;
    private static final int NON_INCREMENT = 0;
    private static final int RESET_COUNT = 0;
    private static final int CONVERT_COUNT = 3;

    private final String name;
    private final int penaltyCount;

    Penalty(String name, int penaltyCount) {
        this.name = name;
        this.penaltyCount = penaltyCount;
    }

    public static Penalty calculatePenalty(List<AttendanceStatus> attendanceStatuses) {
        int absenceCount = countTotalAbsences(attendanceStatuses);
        return determinePenalty(absenceCount);
    }

    private static int countTotalAbsences(List<AttendanceStatus> attendanceStatuses) {
        int absenceCount = 0;
        int perceptionCount = 0;

        for (AttendanceStatus status : attendanceStatuses) {
            absenceCount += countAbsence(status);
            perceptionCount = updatePerceptionCount(status, perceptionCount);

            absenceCount += convertPerceptionToAbsence(perceptionCount);
            perceptionCount = resetPerceptionIfConverted(perceptionCount);
        }
        return absenceCount;
    }

    private static int countAbsence(AttendanceStatus status) {
        return status.isAbsence() ? INCREMENT_COUNT : NON_INCREMENT;
    }

    private static int updatePerceptionCount(AttendanceStatus status, int perceptionCount) {
        return status.isPerception() ? perceptionCount + INCREMENT_COUNT : perceptionCount;
    }

    private static int convertPerceptionToAbsence(int perceptionCount) {
        return perceptionCount >= CONVERT_COUNT ? INCREMENT_COUNT : NON_INCREMENT;
    }

    private static int resetPerceptionIfConverted(int perceptionCount) {
        return perceptionCount >= CONVERT_COUNT ? RESET_COUNT : perceptionCount;
    }

    private static Penalty determinePenalty(int absenceCount) {
        if (absenceCount > Penalty.WEEDING.getPenaltyCount()) {
            return WEEDING;
        }
        if (absenceCount >= Penalty.INTERVIEW.getPenaltyCount()) {
            return INTERVIEW;
        }
        if (absenceCount >= Penalty.WARNING.getPenaltyCount()) {
            return WARNING;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }

    public int getPenaltyCount() {
        return penaltyCount;
    }
}
