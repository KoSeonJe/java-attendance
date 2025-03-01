import controller.AttendanceApplication;
import controller.AttendanceCheckExecutor;
import controller.AttendanceHandler;
import controller.AttendanceUpdateExecutor;
import controller.CrewAttendanceRetrieveExecutor;
import controller.QuitExecutor;
import controller.MenuExecutor;
import controller.PenaltyRetrieveExecutor;
import domain.AttendanceManager;
import domain.CrewAttendanceBook;
import java.util.Map;
import util.ApplicationTime;
import domain.AttendanceApplicationTime;
import util.DataInitializer;
import util.FileLoader;
import util.StringParser;
import view.ConsoleView;
import view.InputView;
import view.Menu;
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
        return new AttendanceHandler(initExecutor(initCrewAttendanceBook));
    }

    private ConsoleView consoleView() {
        return ConsoleView.create(new InputView(), new OutputView(), new InputParser());
    }

    private ApplicationTime applicationTime() {
        return AttendanceApplicationTime.getInstance();
    }

    private Map<Menu, MenuExecutor> initExecutor(CrewAttendanceBook initCrewAttendanceBook) {
        return Map.of(
                Menu.CHECK_ATTENDANCE, attendanceCheckExecutor(),
                Menu.UPDATE_ATTENDANCE, attendanceUpdateExecutor(),
                Menu.CREW_ATTENDANCE_RECORD, crewAttendanceRetrieveExecutor(),
                Menu.PENALTY_RECORD, penaltyRetrieveExecutor(),
                Menu.QUIT, quitExecutor()
        );
    }

    private MenuExecutor attendanceCheckExecutor() {
        return new AttendanceCheckExecutor();
    }

    private MenuExecutor attendanceUpdateExecutor() {
        return new AttendanceUpdateExecutor();
    }

    private MenuExecutor crewAttendanceRetrieveExecutor() {
        return new CrewAttendanceRetrieveExecutor();
    }

    private MenuExecutor penaltyRetrieveExecutor() {
        return new PenaltyRetrieveExecutor();
    }

    private MenuExecutor quitExecutor() {
        return new QuitExecutor();
    }
}
