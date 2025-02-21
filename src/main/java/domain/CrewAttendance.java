package domain;

import java.util.List;

public class CrewAttendance {
    private final Crew crew;
    private final Attendance attendance;

    public CrewAttendance(Crew crew, Attendance attendance) {
        this.crew = crew;
        this.attendance = attendance;
    }

    public void addAttendance(DateTime dateTime) {
        attendance.addDateTime(dateTime);
    }

    public void updateAttendance(DateTime updateDateTime) {
        attendance.updateDateTime(updateDateTime);
    }

    public DateTime retrieveDateTime(Date date) {
        return attendance.retrieveDateTime(date);
    }

    public List<DateTime> retrieveDateTimesOrderByDate() {
        return attendance.retrieveDateTimesOrderByDate();
    }

    public AttendanceStatus calculateAttendanceStatus(Date date) {
        return attendance.calculateAttendanceStatus(date);
    }

    public List<AttendanceStatus> calculateAttendanceStatuses() {
        return attendance.retrieveDateTimes().stream()
                .map(datetime -> attendance.calculateAttendanceStatus(datetime.getDate()))
                .toList();
    }

    public boolean isPenalty() {
        return !retrievePenalty().equals(Penalty.NONE);
    }
    
    public Penalty retrievePenalty() {
        return Penalty.calculatePenalty(calculateAttendanceStatuses());
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public Crew getCrew() {
        return crew;
    }
}
