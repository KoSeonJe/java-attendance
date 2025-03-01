package view;

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

    public static ConsoleView create(InputView inputView, OutputView outputView, InputParser inputParser) {
        if (consoleView == null) {
            consoleView = new ConsoleView(inputView, outputView, inputParser);
            return consoleView;
        }
        return consoleView;
    }
}
