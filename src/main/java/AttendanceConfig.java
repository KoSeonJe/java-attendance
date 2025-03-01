import controller.AttendanceApplication;
import controller.AttendanceHandler;
import domain.AttendanceManager;
import domain.CrewAttendanceBook;
import util.ApplicationTime;
import domain.AttendanceApplicationTime;
import util.DataInitializer;
import util.FileLoader;
import util.StringParser;
import view.ConsoleView;
import view.InputView;
import view.OutputView;
import view.parser.InputParser;

public final class AttendanceConfig {

    public AttendanceApplication attendanceApplication(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceApplication(attendanceHandler(initCrewAttendanceBook), consoleView(), applicationTime());
    }

    public DataInitializer dataInitializer() {
        return new DataInitializer(new FileLoader(), new StringParser(), new AttendanceManager(CrewAttendanceBook.createEmpty()));
    }

    private AttendanceHandler attendanceHandler(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceHandler(consoleView(), initCrewAttendanceBook, applicationTime());
    }

    private ConsoleView consoleView() {
        return ConsoleView.create(new InputView(), new OutputView(), new InputParser());
    }

    private ApplicationTime applicationTime() {
        return AttendanceApplicationTime.getInstance();
    }
}
