import controller.AttendanceController;
import domain.CrewAttendanceRepository;
import java.time.LocalDateTime;
import util.DataInitializer;
import view.InputView;
import view.OutputView;

public final class ApplicationConfig {

    public static final LocalDateTime applicationTime = LocalDateTime.of(2024, 12, 13, 10, 5);

    public DataInitializer dataInitializer() {
        return new DataInitializer(crewAttendanceRepository());
    }

    public CrewAttendanceRepository crewAttendanceRepository() {
        return CrewAttendanceRepository.getInstance();
    }

    public AttendanceController attendanceController() {
        return new AttendanceController(new InputView(), new OutputView(), crewAttendanceRepository());
    }
}
