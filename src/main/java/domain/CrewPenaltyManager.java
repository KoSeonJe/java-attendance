package domain;

import domain.vo.PenaltyTarget;
import java.util.List;

public class CrewPenaltyManager {

    private final AttendanceStatusCalculator attendanceStatusCalculator;

    public CrewPenaltyManager(
            AttendanceStatusCalculator attendanceStatusCalculator
    ) {
        this.attendanceStatusCalculator = attendanceStatusCalculator;
    }

    public PenaltyTarget retrieveAllPenaltyTarget(String crewName, List<Attendance> attendances) {
        AttendanceStatusCounter attendanceStatusCounter = attendanceStatusCalculator.calculateAllCount(attendances);
        int lateCount = attendanceStatusCounter.retrieveAttendanceStatusCount(AttendanceStatus.LATE);
        int absenceCount = attendanceStatusCounter.retrieveAttendanceStatusCount(AttendanceStatus.ABSENCE);
        Penalty penalty = calculatePenalty(lateCount, absenceCount);
        return new PenaltyTarget(crewName, lateCount, absenceCount, penalty);
    }

    private Penalty calculatePenalty(int lateCount, int absenceCount) {
        int totalAbsenceCount = Penalty.calculateTotalAbsence(lateCount, absenceCount);
        return Penalty.judge(totalAbsenceCount);
    }
}
