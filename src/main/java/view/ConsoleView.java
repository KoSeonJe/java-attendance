package view;

import java.time.LocalDateTime;
import view.parser.InputParser;

public final class ConsoleView {

    private static ConsoleView consoleView;

    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;

    public ConsoleView(InputView inputView, OutputView outputView, InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public Menu requestMenu(LocalDate applicationDate) {
        String inputMenuSelector = inputView.inputMenuSelector(applicationDate);
        return Menu.findBySelector(inputMenuSelector);
    }

    public void printMessage(String message) {
        outputView.printMessage(message);
    }

    public static ConsoleView create(InputView inputView, OutputView outputView, InputParser inputParser) {
        if (consoleView == null) {
            consoleView = new ConsoleView(inputView, outputView, inputParser);
            return consoleView;
        }
        return consoleView;
    }
}
