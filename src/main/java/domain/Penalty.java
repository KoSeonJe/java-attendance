package domain;

import java.util.List;

public enum Penalty {
    WARNING("경고"),
    INTERVIEW("면담"),
    WEEDING("제적");

    private final String name;

    Penalty(String name) {
        this.name = name;
    }

    public static Penalty calculatePenalty(List<AttendanceStatus> attendanceStatuses) {
        int absenceCount = 0;
        int perceptionCount = 0;
        for (AttendanceStatus status : attendanceStatuses) {
            if (status.isAbsence()) {
                absenceCount++;
            }

            if (status.isPerception()) {
                perceptionCount++;
            }

            if (perceptionCount >= 3) {
                absenceCount++;
                perceptionCount = 0;
            }
        }

        if (absenceCount > 5) {
            return WEEDING;
        }

        if (absenceCount >= 3) {
            return INTERVIEW;
        }

        if (absenceCount >= 2) {
            return WARNING;
        }

        return null;
    }

    public String getName() {
        return name;
    }
}
