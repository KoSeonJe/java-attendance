package controller;

import domain.Attendance;
import domain.AttendanceManager;
import domain.AttendanceRecords;
import domain.AttendanceTime;
import domain.CrewAttendanceBook;
import java.time.LocalDate;
import java.time.LocalTime;
import util.ApplicationDate;
import view.ConsoleView;

public class AttendanceCheckExecutor implements MenuExecutor{

    private final ConsoleView consoleView;
    private final ApplicationDate applicationDate;
    private final AttendanceManager attendanceManager;

    public AttendanceCheckExecutor(
            ConsoleView consoleView,
            ApplicationDate applicationDate,
            AttendanceManager attendanceManager
    ) {
        this.consoleView = consoleView;
        this.applicationDate = applicationDate;
        this.attendanceManager = attendanceManager;
    }

    @Override
    public void execute() {
        String nickName = consoleView.requestNickName();
        LocalDate date = applicationDate.getApplicationDate();
        AttendanceTime attendanceTime = requestAttendanceTime();

        attendanceManager.processAttendance(nickName, date, attendanceTime);

        Attendance attendance = retrieveAttendance(attendanceManager, nickName, date);
        consoleView.printAttendanceResult(attendance);
    }

    private AttendanceTime requestAttendanceTime() {
        LocalTime inputTime = consoleView.requestAttendanceTime();
        return new AttendanceTime(inputTime.getHour(), inputTime.getMinute());
    }

    private Attendance retrieveAttendance(AttendanceManager attendanceManager, String nickName, LocalDate date) {
        CrewAttendanceBook crewAttendanceBook = attendanceManager.crewAttendanceBook();
        AttendanceRecords attendanceRecords = crewAttendanceBook.retrieveAttendanceRecordsByName(nickName);
        return attendanceRecords.retrieveAttendanceByDate(date);
    }
}
