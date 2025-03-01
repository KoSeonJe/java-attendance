package controller;

import util.ApplicationTime;
import view.ConsoleView;
import view.Menu;

public class AttendanceApplication {

    private final AttendanceHandler attendanceHandler;
    private final ConsoleView consoleView;
    private final ApplicationTime applicationTime;

    public AttendanceApplication(
            AttendanceHandler attendanceHandler,
            ConsoleView consoleView,
            ApplicationTime applicationTime
    ) {
        this.attendanceHandler = attendanceHandler;
        this.consoleView = consoleView;
        this.applicationTime = applicationTime;
    }

    public void execute() {
        Menu menu = null;
        while (!Menu.isQuit(menu)) {
            try {
                menu = consoleView.requestMenu(applicationTime.getApplicationTime());
                attendanceHandler.handle(menu);
            } catch (Exception e) {
                consoleView.printMessage(e.getMessage());
            }
        }
    }
}
