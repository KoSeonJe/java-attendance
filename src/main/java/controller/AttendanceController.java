package controller;

import domain.MenuOption;
import util.ApplicationTime;
import view.InputView;
import view.OutputView;

public class AttendanceController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ApplicationTime applicationTime;

    public AttendanceController(
            InputView inputView,
            OutputView outputView,
            ApplicationTime applicationTime
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.applicationTime = applicationTime;
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            try {
                MenuOption menuOption = inputView.readMenuOption(applicationTime.getApplicationTime());
                menuOption.getMenuExecutor().execute();
                isRunning = shouldContinue(menuOption);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private boolean shouldContinue(MenuOption menuOption) {
        return !menuOption.isExit();
    }
}
