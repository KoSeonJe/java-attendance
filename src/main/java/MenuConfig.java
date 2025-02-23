import controller.CheckMenuExecutor;
import controller.EditMenuExecutor;
import controller.ExitMenuExecutor;
import controller.MenuExecutor;
import controller.RecordMenuExecutor;
import controller.RiskMenuExecutor;
import domain.CrewAttendanceRepository;
import util.ApplicationTime;
import util.AttendanceApplicationTime;
import view.InputView;
import view.OutputView;

public class MenuConfig {

    public static MenuExecutor checkMenuExecutor() {
        return new CheckMenuExecutor(applicationTime(), inputView(), outputView(), crewAttendanceRepository());
    }

    public static MenuExecutor editMenuExecutor() {
        return new EditMenuExecutor(applicationTime(), inputView(), outputView(), crewAttendanceRepository());
    }

    public static MenuExecutor recordMenuExecutor() {
        return new RecordMenuExecutor(inputView(), outputView(), crewAttendanceRepository());
    }

    public static MenuExecutor riskMenuExecutor() {
        return new RiskMenuExecutor(crewAttendanceRepository(), outputView());
    }

    public static MenuExecutor exitMenuExecutor() {
        return new ExitMenuExecutor();
    }

    private static InputView inputView() {
        return new InputView();
    }

    private static OutputView outputView() {
        return new OutputView();
    }

    private static CrewAttendanceRepository crewAttendanceRepository() {
        return CrewAttendanceRepository.getInstance();
    }

    private static ApplicationTime applicationTime() {
        return new AttendanceApplicationTime();
    }
}
