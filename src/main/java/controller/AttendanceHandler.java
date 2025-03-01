package controller;

import domain.CrewAttendanceBook;
import util.ApplicationTime;
import view.InputView;
import view.OutputView;

public class AttendanceHandler {

    private final InputView inputView;
    private final OutputView outputView;
    private final CrewAttendanceBook initCrewAttendanceBook;
    private final ApplicationTime applicationTime;


    public AttendanceHandler(
            InputView inputView,
            OutputView outputView,
            CrewAttendanceBook initCrewAttendanceBook,
            ApplicationTime applicationTime
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.initCrewAttendanceBook = initCrewAttendanceBook;
        this.applicationTime = applicationTime;
    }
}
