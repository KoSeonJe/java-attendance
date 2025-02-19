import controller.AttendanceController;
import view.input.InputView;

public class AttendanceApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        AttendanceController controller = new AttendanceController(inputView);
        controller.run();
    }
}
