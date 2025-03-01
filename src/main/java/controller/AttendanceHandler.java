package controller;

import domain.CrewAttendanceBook;
import util.ApplicationTime;
import view.ConsoleView;
import view.InputView;
import view.OutputView;

public class AttendanceHandler {

    private final ConsoleView consoleView;
    private final CrewAttendanceBook initCrewAttendanceBook;
    private final ApplicationTime applicationTime;


    public AttendanceHandler(
            ConsoleView consoleView,
            CrewAttendanceBook initCrewAttendanceBook,
            ApplicationTime applicationTime
    ) {
        this.consoleView = consoleView;
        this.initCrewAttendanceBook = initCrewAttendanceBook;
        this.applicationTime = applicationTime;
    }
}
