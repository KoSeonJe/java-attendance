package view;

import domain.Attendance;
import domain.AttendanceStatus;
import domain.AttendanceTime;
import java.time.LocalDate;
import java.time.LocalTime;
import view.parser.InputParser;
import view.parser.OutputParser;

public final class ConsoleView {

    private static ConsoleView consoleView;

    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;
    private final OutputParser outputParser;

    public ConsoleView(InputView inputView, OutputView outputView, InputParser inputParser, OutputParser outputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
        this.outputParser = outputParser;
    }

    public Menu requestMenu(LocalDate applicationDate) {
        String inputMenuSelector = inputView.inputMenuSelector(applicationDate);
        return Menu.findBySelector(inputMenuSelector);
    }

    public void printMessage(String message) {
        outputView.printMessage(message);
    }

    public String requestNickName() {
        return inputView.inputNickName();
    }

    public LocalTime requestAttendanceTime() {
        String rawInputAttendanceTime = inputView.inputAttendanceTime();
        return inputParser.parseToLocalTime(rawInputAttendanceTime);
    }

    public void printAttendanceResult(Attendance attendance) {
        LocalDate date = attendance.getAttendanceDate();
        AttendanceTime time = attendance.getAttendanceTime();
        AttendanceStatus attendanceStatus = attendance.getAttendanceStatus();

        outputView.printAttendanceResult(date, inputParser.parseToTimeMessage(time), outputParser.parseAttendanceStatusToMessage(attendanceStatus));
    }

    public static ConsoleView create(InputView inputView, OutputView outputView, InputParser inputParser, OutputParser outputParser) {
        if (consoleView == null) {
            consoleView = new ConsoleView(inputView, outputView, inputParser, outputParser);
            return consoleView;
        }
        return consoleView;
    }
}
