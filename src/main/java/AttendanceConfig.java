import controller.AttendanceApplication;
import controller.AttendanceHandler;
import domain.AttendanceManager;
import domain.CrewAttendanceBook;
import util.ApplicationTime;
import domain.AttendanceApplicationTime;
import util.DataInitializer;
import util.FileLoader;
import util.StringParser;
import view.InputView;
import view.OutputView;

public final class AttendanceConfig {

    public AttendanceApplication attendanceApplication(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceApplication(attendanceHandler(initCrewAttendanceBook));
    }

    public DataInitializer dataInitializer() {
        return new DataInitializer(new FileLoader(), new StringParser(), new AttendanceManager(CrewAttendanceBook.createEmpty()));
    }

    private AttendanceHandler attendanceHandler(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceHandler(new InputView(), new OutputView(), initCrewAttendanceBook, applicationTime());
    }

    private ApplicationTime applicationTime() {
        return new AttendanceApplicationTime();
    }
}
