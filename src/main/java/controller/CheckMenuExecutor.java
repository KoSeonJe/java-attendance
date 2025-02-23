package controller;

import domain.AttendanceStatus;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.AttendanceDateTime;
import domain.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import util.ApplicationTime;
import view.InputView;
import view.OutputView;

public class CheckMenuExecutor implements MenuExecutor {

    private final ApplicationTime applicationTime;
    private final InputView inputView;
    private final OutputView outputView;
    private final CrewAttendanceRepository crewAttendanceRepository;

    public CheckMenuExecutor(
            ApplicationTime applicationTime,
            InputView inputView,
            OutputView outputView,
            CrewAttendanceRepository crewAttendanceRepository
    ) {
        this.applicationTime = applicationTime;
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewAttendanceRepository = crewAttendanceRepository;
    }

    @Override
    public void execute() {
        String nickName = inputView.readNickName();
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsNickName(nickName);

        AttendanceDateTime arriveAttendanceDateTime = createDateTime(applicationTime.getApplicationTime().toLocalDate(), inputView.readArriveTime());
        crewAttendance.addAttendance(arriveAttendanceDateTime);
        AttendanceStatus attendanceStatus = crewAttendance.retrieveAttendanceStatus(arriveAttendanceDateTime.getDate());

        outputView.printArriveResult(arriveAttendanceDateTime, attendanceStatus.getName());
    }

    private AttendanceDateTime createDateTime(LocalDate localDate, LocalTime arriveTime) {
        return new AttendanceDateTime(new Date(localDate), new Time(arriveTime.getHour(), arriveTime.getMinute()));
    }
}
