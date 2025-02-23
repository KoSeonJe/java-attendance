package controller;

import domain.AttendanceStatus;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.DateTime;
import domain.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import util.ApplicationTime;
import util.AttendanceApplicationTime;
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
        Crew crew = new Crew(nickName);
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsCrew(crew);

        LocalTime arriveTime = inputView.readArriveTime();
        DateTime dateTime = createDateTime(applicationTime.getApplicationTime().toLocalDate(), arriveTime);
        AttendanceStatus.findByDateTime(dateTime);
        crewAttendance.addAttendance(dateTime);
        AttendanceStatus attendanceStatus = crewAttendance.calculateAttendanceStatus(dateTime.getDate());

        outputView.printArriveResult(dateTime, attendanceStatus.getName());
    }

    private DateTime createDateTime(LocalDate localDate, LocalTime arriveTime) {
        return new DateTime(new Date(localDate),
                new Time(arriveTime.getHour(), arriveTime.getMinute()));
    }
}
