package controller;

import domain.AttendanceStatus;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import domain.Date;
import domain.DateTime;
import domain.MenuOption;
import domain.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import view.InputView;
import view.OutputView;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(LocalDateTime localDateTime) {
        while (true) {
            MenuOption menuOption = inputView.readMenuOption(localDateTime);

            if (menuOption == MenuOption.CHECK) {
                String nickName = inputView.readNickName();
                Crew crew = new Crew(nickName);
                CrewAttendance crewAttendance = CrewAttendanceRepository.getInstance().findByCrew(crew);

                LocalTime arriveTime = inputView.readArriveTime();
                DateTime dateTime = new DateTime(new Date(localDateTime.toLocalDate()),
                        new Time(arriveTime.getHour(), arriveTime.getMinute()));

                crewAttendance.addAttendance(dateTime);
                AttendanceStatus attendanceStatus = crewAttendance.calculateAttendanceStatus(dateTime.getDate());

                outputView.printArriveResult(dateTime, attendanceStatus);
            }

            if (menuOption == MenuOption.EDIT) {
                String updateNickName = inputView.readUpdateNickName();
                Crew updateCrew = new Crew(updateNickName);
                CrewAttendance crewAttendance = CrewAttendanceRepository.getInstance().findByCrew(updateCrew);

                int updateDate = inputView.readUpdateDate();
                LocalTime updateArriveTime = inputView.readUpdateArriveTime();
                DateTime afterDateTime = new DateTime(
                        new Date(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), updateDate)),
                        new Time(updateArriveTime.getHour(), updateArriveTime.getMinute()));

                DateTime beforeDateTime = crewAttendance.retrieveDateTime(afterDateTime.getDate());
                AttendanceStatus beforeAttendanceStatus = crewAttendance.calculateAttendanceStatus(
                        beforeDateTime.getDate());

                crewAttendance.updateAttendance(afterDateTime);
                AttendanceStatus afterAttendanceStatus = crewAttendance.calculateAttendanceStatus(
                        afterDateTime.getDate());

                outputView.printUpdateResult(beforeDateTime, beforeAttendanceStatus, afterDateTime,
                        afterAttendanceStatus);
            }

            if (menuOption == MenuOption.RECORD) {
                String nickName = inputView.readNickName();
                Crew crew = new Crew(nickName);
                CrewAttendance crewAttendance = CrewAttendanceRepository.getInstance().findByCrew(crew);

                List<DateTime> dateTimes = crewAttendance.retrieveDateTimesUntilDate(
                        new Date(localDateTime.toLocalDate()));

                outputView.printTotalAttendanceStatus(dateTimes);
            }
        }
    }
}
