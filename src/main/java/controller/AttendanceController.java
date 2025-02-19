package controller;

import java.time.LocalDateTime;
import view.input.InputView;

public class AttendanceController {
    private final InputView inputView;

    public AttendanceController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run(LocalDateTime dateTime) {
        inputView.readMenuOption(dateTime);
    }
}
