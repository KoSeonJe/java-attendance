package controller;

import domain.AttendanceStatus;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.DateTime;
import domain.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import util.ApplicationTime;
import view.InputView;
import view.OutputView;

public class EditMenuExecutor implements MenuExecutor {

    private final ApplicationTime applicationTime;
    private final InputView inputView;
    private final OutputView outputView;
    private final CrewAttendanceRepository crewAttendanceRepository;

    public EditMenuExecutor(
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
        String updateNickName = inputView.readUpdateNickName();
        Crew updateCrew = new Crew(updateNickName);
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsCrew(updateCrew);

        int updateDate = inputView.readUpdateDate();
        LocalTime updateArriveTime = inputView.readUpdateArriveTime();
        DateTime afterDateTime = createDateTime(updateDate, updateArriveTime);

        DateTime beforeDateTime = crewAttendance.retrieveDateTime(afterDateTime.getDate());
        AttendanceStatus beforeAttendanceStatus = crewAttendance.calculateAttendanceStatus(beforeDateTime.getDate());

        crewAttendance.updateAttendance(afterDateTime);
        AttendanceStatus afterAttendanceStatus = crewAttendance.calculateAttendanceStatus(afterDateTime.getDate());

        outputView.printUpdateResult(beforeDateTime, beforeAttendanceStatus.getName(),
                afterDateTime, afterAttendanceStatus.getName());
    }

    private DateTime createDateTime(int day, LocalTime arriveTime) {
        LocalDateTime time = applicationTime.getApplicationTime();
        return new DateTime(new Date(LocalDate.of(time.getYear(), time.getMonth(), day)),
                new Time(arriveTime.getHour(), arriveTime.getMinute()));
    }
}
