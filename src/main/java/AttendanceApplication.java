import controller.AttendanceController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import view.input.InputView;

public class AttendanceApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        AttendanceController controller = new AttendanceController(inputView);

        LocalDateTime dateTime = parseDateTimeFromArgs(args);
        controller.run(dateTime);
    }

    private static LocalDateTime parseDateTimeFromArgs(String[] args) {
        if (args.length < 1) {
            System.out.println("사용법: java AttendanceApplication \"yyyy-MM-dd HH:mm\"");
            System.out.println("예시: java AttendanceApplication \"2024-12-13 10:06\"");
            System.exit(1);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(args[0], formatter);
        } catch (DateTimeParseException e) {
            System.out.println("잘못된 날짜 형식입니다. \"yyyy-MM-dd HH:mm\" 형식으로 입력하세요.");
            System.exit(1);
        }
        return null;
    }
}
