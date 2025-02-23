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
        ApplicationConfig applicationConfig = new ApplicationConfig();

        DataInitializer dataInitializer = applicationConfig.dataInitializer();
        dataInitializer.initialize(applicationTime, "src/main/resources/attendances.csv");

        AttendanceController controller = applicationConfig.attendanceController();
        controller.run(applicationTime);
    }
}
