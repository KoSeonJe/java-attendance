import controller.AttendanceController;
import domain.CrewAttendanceRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import util.DataInitializer;
import view.InputView;
import view.OutputView;

public class AttendanceApplication {
    public static void main(String[] args) {
        LocalDateTime applicationTime = ApplicationConfig.applicationTime;

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        DataInitializer dataInitializer = new DataInitializer();
        dataInitializer.initialize(applicationTime, "src/main/resources/attendances.csv");
        AttendanceController controller = new AttendanceController(inputView, outputView,
                CrewAttendanceRepository.getInstance());
        controller.run(applicationTime);
    }
}
