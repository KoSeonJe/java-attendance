import domain.CrewAttendanceBook;

public final class AttendanceConfig {

    public AttendanceApplication attendanceApplication(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceApplication(attendanceHandler(initCrewAttendanceBook));
    }

    public DataInitializer dataInitializer() {
        return new DataInitializer();
    }

    private AttendanceHandler attendanceHandler(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceHandler(new InputView(), new OutputView(), initCrewAttendanceBook, applicationTime());
    }

    private ApplicationTime applicationTime() {
        return new AttendanceApplicationTime();
    }
}
