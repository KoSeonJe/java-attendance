import controller.AttendanceController;
import domain.CrewAttendanceRepository;
import util.ApplicationTime;
import util.AttendanceApplicationTime;
import util.DataInitializer;
import view.InputView;
import view.OutputView;

public final class ApplicationConfig {

    public DataInitializer dataInitializer() {
        return new DataInitializer(applicationTime(), crewAttendanceRepository());
    }

    public AttendanceController attendanceController() {
        return new AttendanceController(new InputView(), new OutputView(), applicationTime());
    }

    private CrewAttendanceRepository crewAttendanceRepository() {
        return CrewAttendanceRepository.getInstance();
    }

    private ApplicationTime applicationTime() {
        return new AttendanceApplicationTime();
    }
}
