package domain;

import java.util.List;

public enum Penalty {
    NONE("없음"),
    WARNING("경고"),
    INTERVIEW("면담"),
    WEEDING("제적");

    private final String name;

    Penalty(String name) {
        this.name = name;
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
        return status.isAbsence() ? 1 : 0;
    }

    private static int updatePerceptionCount(AttendanceStatus status, int perceptionCount) {
        return status.isPerception() ? perceptionCount + 1 : perceptionCount;
    }

    private static int convertPerceptionToAbsence(int perceptionCount) {
        return perceptionCount >= 3 ? 1 : 0;
    }

    private static int resetPerceptionIfConverted(int perceptionCount) {
        return perceptionCount >= 3 ? 0 : perceptionCount;
    }

    private static Penalty determinePenalty(int absenceCount) {
        if (absenceCount > 5) {
            return WEEDING;
        }
        if (absenceCount >= 3) {
            return INTERVIEW;
        }
        if (absenceCount >= 2) {
            return WARNING;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
