package controller;

import controller.dto.CrewAttendanceRecord;
import domain.Attendance;
import domain.AttendanceManager;
import domain.AttendanceStatus;
import domain.AttendanceStatusCalculator;
import domain.AttendanceStatusCounter;
import domain.Penalty;
import java.time.LocalDate;
import java.util.List;
import util.ApplicationDate;
import view.ConsoleView;

public class CrewAttendanceRetrieveExecutor implements MenuExecutor{

    private final ConsoleView consoleView;
    private final ApplicationDate applicationDate;
    private final AttendanceManager attendanceManager;
    private final AttendanceStatusCalculator attendanceStatusCalculator;

    public CrewAttendanceRetrieveExecutor(
            ConsoleView consoleView,
            ApplicationDate applicationDate,
            AttendanceManager attendanceManager,
            AttendanceStatusCalculator attendanceStatusCalculator
    ) {
        this.consoleView = consoleView;
        this.applicationDate = applicationDate;
        this.attendanceManager = attendanceManager;
        this.attendanceStatusCalculator = attendanceStatusCalculator;
    }

    @Override
    public void execute() {
        String nickName = consoleView.requestNickName();
        LocalDate date = applicationDate.getApplicationDate();

        List<Attendance> attendances = retrieveSortedFillAttendances(nickName, date);
        AttendanceStatusCounter attendanceStatusCounter = attendanceStatusCalculator.calculateAllCount(attendances);
        Penalty penalty = calculatePenalty(attendanceStatusCounter);

        consoleView.printCrewAttendanceRecord(CrewAttendanceRecord.of(attendances, attendanceStatusCounter, penalty));
    }

    private List<Attendance> retrieveSortedFillAttendances(String nickName, LocalDate date) {
        List<Attendance> attendances = attendanceManager.retrieveFilledAttendanceUntilDate(nickName, date);
        return attendances.stream()
                .sorted()
                .toList();
    }

    private Penalty calculatePenalty(AttendanceStatusCounter attendanceStatusCounter) {
        int lateCount = attendanceStatusCounter.retrieveAttendanceStatusCount(AttendanceStatus.LATE);
        int absenceCount = attendanceStatusCounter.retrieveAttendanceStatusCount(AttendanceStatus.ABSENCE);
        int totalAbsenceCount = Penalty.calculateTotalAbsence(lateCount, absenceCount);
        return Penalty.judge(totalAbsenceCount);
    }
}
