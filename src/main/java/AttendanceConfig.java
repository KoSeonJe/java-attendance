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
import util.ApplicationDate;
import domain.AttendanceApplicationDate;
import util.DataInitializer;
import util.FileLoader;
import util.StringParser;
import view.ConsoleView;
import view.InputView;
import view.Menu;
import view.OutputView;
import view.parser.InputParser;
import view.parser.OutputParser;

public final class AttendanceConfig {

    public AttendanceApplication attendanceApplication(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceApplication(attendanceHandler(initCrewAttendanceBook), consoleView(), applicationTime());
    }

    public DataInitializer dataInitializer() {
        return new DataInitializer(new FileLoader(), new StringParser(), attendanceManager(CrewAttendanceBook.createEmpty()));
    }

    private AttendanceHandler attendanceHandler(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceHandler(initExecutor(initCrewAttendanceBook));
    }

    private ConsoleView consoleView() {
        return ConsoleView.create(new InputView(), new OutputView(), new InputParser(), new OutputParser());
    }

    private ApplicationDate applicationTime() {
        return AttendanceApplicationDate.getInstance();
    }

    private Map<Menu, MenuExecutor> initExecutor(CrewAttendanceBook initCrewAttendanceBook) {
        return Map.of(
                Menu.CHECK_ATTENDANCE, attendanceCheckExecutor(initCrewAttendanceBook),
                Menu.UPDATE_ATTENDANCE, attendanceUpdateExecutor(initCrewAttendanceBook),
                Menu.CREW_ATTENDANCE_RECORD, crewAttendanceRetrieveExecutor(),
                Menu.PENALTY_RECORD, penaltyRetrieveExecutor(),
                Menu.QUIT, quitExecutor()
        );
    }

    private AttendanceManager attendanceManager(CrewAttendanceBook crewAttendanceBook) {
        return new AttendanceManager(crewAttendanceBook);
    }

    private MenuExecutor attendanceCheckExecutor(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceCheckExecutor(consoleView(), applicationTime(), attendanceManager(initCrewAttendanceBook));
    }

    private MenuExecutor attendanceUpdateExecutor(CrewAttendanceBook initCrewAttendanceBook) {
        return new AttendanceUpdateExecutor(consoleView(), applicationTime(), attendanceManager(initCrewAttendanceBook));
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
