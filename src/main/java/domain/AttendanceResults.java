package domain;

import java.time.LocalDateTime;
import java.util.List;

public class AttendanceResults {
    private final List<AttendanceResult> attendanceResults;

    public AttendanceResults(List<AttendanceResult> attendanceResults) {
        this.attendanceResults = attendanceResults;
    }

    public static AttendanceResults from(List<LocalDateTime> localDateTimes) {
        List<AttendanceResult> attendanceResults = localDateTimes.stream()
                .map(AttendanceResult::from)
                .toList();

        return new AttendanceResults(attendanceResults);
    }

    public List<AttendanceResult> getSortedAttendanceResults() {
        return attendanceResults.stream()
                .sorted()
                .toList();
    }

    public int calculateAttendanceCount() {
        int attendanceCount = 0;
        for (AttendanceResult attendanceResult : attendanceResults) {
            if (attendanceResult.calculateAttendanceStatus().equals(AttendanceStatus.ATTENDANCE)) {
                attendanceCount++;
            }
        }

        return attendanceCount;
    }

    public int calculatePerceptionCount() {
        int lateCount = 0;
        for (AttendanceResult attendanceResult : attendanceResults) {
            if (attendanceResult.calculateAttendanceStatus().equals(AttendanceStatus.PERCEPTION)) {
                lateCount++;
            }
        }

        return lateCount;
    }

    public int calculateAbsentCount() {
        int absentCount = 0;
        for (AttendanceResult attendanceResult : attendanceResults) {
            if (attendanceResult.calculateAttendanceStatus().equals(AttendanceStatus.ABSENCE)) {
                absentCount++;
            }
        }

        return absentCount;
    }

    public Penalty calculatePenalty() {
        List<AttendanceStatus> attendanceStatuses = attendanceResults.stream()
                .map(AttendanceResult::calculateAttendanceStatus)
                .toList();

        return Penalty.calculatePenalty(attendanceStatuses);
    }
}
