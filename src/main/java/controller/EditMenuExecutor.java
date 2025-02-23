package controller;

import domain.AttendanceStatus;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.AttendanceDateTime;
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
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsNickName(updateNickName);

        int updateDate = inputView.readUpdateDate();
        LocalTime updateArriveTime = inputView.readUpdateArriveTime();
        AttendanceDateTime afterAttendanceDateTime = createDateTime(updateDate, updateArriveTime);

        AttendanceDateTime beforeAttendanceDateTime = crewAttendance.retrieveDateTime(afterAttendanceDateTime.getDate());
        AttendanceStatus beforeAttendanceStatus = crewAttendance.calculateAttendanceStatus(beforeAttendanceDateTime.getDate());

        crewAttendance.updateAttendance(afterAttendanceDateTime);
        AttendanceStatus afterAttendanceStatus = crewAttendance.calculateAttendanceStatus(afterAttendanceDateTime.getDate());

        outputView.printUpdateResult(beforeAttendanceDateTime, beforeAttendanceStatus.getName(),
                afterAttendanceDateTime, afterAttendanceStatus.getName());
    }

    private AttendanceDateTime createDateTime(int day, LocalTime arriveTime) {
        LocalDateTime time = applicationTime.getApplicationTime();
        return new AttendanceDateTime(new Date(LocalDate.of(time.getYear(), time.getMonth(), day)),
                new Time(arriveTime.getHour(), arriveTime.getMinute()));
    }
}
