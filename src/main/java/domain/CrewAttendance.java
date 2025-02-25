package domain;

import java.util.List;
import java.util.Map;

public class CrewAttendance {

    private final Crew crew;
    private final Attendance attendance;

    public CrewAttendance(Crew crew, Attendance attendance) {
        this.crew = crew;
        this.attendance = attendance;
    }

    public void addAttendance(AttendanceDateTime attendanceDateTime) {
        attendance.addDateTime(attendanceDateTime);
    }

    public void updateAttendance(AttendanceDateTime updateAttendanceDateTime) {
        attendance.updateDateTime(updateAttendanceDateTime);
    }

    public AttendanceDateTime retrieveDateTime(Date date) {
        return attendance.retrieveDateTime(date);
    }

    public List<AttendanceDateTime> retrieveDateTimesOrderByDate() {
        return attendance.retrieveDateTimesOrderByDate();
    }

    public AttendanceStatus retrieveAttendanceStatus(Date date) {
        return attendance.retrieveAttendanceStatus(date);
    }

    public Map<AttendanceStatus, Integer> retrieveAttendanceStatusCount() {
        return attendance.calculateAttendanceStatusCount();
    }

    public boolean isPenalty() {
        return !retrievePenalty().equals(Penalty.NONE);
    }

    public Penalty retrievePenalty() {
        return Penalty.calculatePenalty(attendance.retrieveAttendanceStatuses());
    }

    public Crew getCrew() {
        return crew;
    }
}
