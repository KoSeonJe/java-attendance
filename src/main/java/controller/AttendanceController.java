package controller;

import controller.dto.AttendanceRecodeDto;
import controller.dto.AttendanceResultDto;
import controller.dto.PenaltyCrewDto;
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
    private final CrewAttendanceRepository crewAttendanceRepository;

    public AttendanceController(InputView inputView, OutputView outputView,
                                CrewAttendanceRepository crewAttendanceRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewAttendanceRepository = crewAttendanceRepository;
    }

    public void run(LocalDateTime localDateTime) {
        boolean isRunning = true;

        while (isRunning) {
            try {
                MenuOption menuOption = inputView.readMenuOption(localDateTime);
                isRunning = shouldContinue(menuOption);
                executeMenu(menuOption, localDateTime);
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private boolean shouldContinue(MenuOption menuOption) {
        return !menuOption.isExit();
    }

    private void executeMenu(MenuOption menuOption, LocalDateTime localDateTime) {
        if (menuOption == MenuOption.CHECK) {
            handleCheckAttendance(localDateTime);
            return;
        }
        if (menuOption == MenuOption.EDIT) {
            handleEditAttendance(localDateTime);
            return;
        }
        if (menuOption == MenuOption.RECORD) {
            handleRecordAttendance();
            return;
        }
        if (menuOption == MenuOption.RISK) {
            handleRiskAttendance();
        }
    }

    private void handleCheckAttendance(LocalDateTime localDateTime) {
        String nickName = inputView.readNickName();
        Crew crew = new Crew(nickName);
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsCrew(crew);

        LocalTime arriveTime = inputView.readArriveTime();
        DateTime dateTime = createDateTime(localDateTime.toLocalDate(), arriveTime);
        AttendanceStatus.findByDateTime(dateTime);
        crewAttendance.addAttendance(dateTime);
        AttendanceStatus attendanceStatus = crewAttendance.calculateAttendanceStatus(dateTime.getDate());

        outputView.printArriveResult(dateTime, attendanceStatus.getName());
    }

    private void handleEditAttendance(LocalDateTime localDateTime) {
        String updateNickName = inputView.readUpdateNickName();
        Crew updateCrew = new Crew(updateNickName);
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsCrew(updateCrew);

        int updateDate = inputView.readUpdateDate();
        LocalTime updateArriveTime = inputView.readUpdateArriveTime();
        DateTime afterDateTime = createDateTime(localDateTime, updateDate, updateArriveTime);

        DateTime beforeDateTime = crewAttendance.retrieveDateTime(afterDateTime.getDate());
        AttendanceStatus beforeAttendanceStatus = crewAttendance.calculateAttendanceStatus(beforeDateTime.getDate());

        crewAttendance.updateAttendance(afterDateTime);
        AttendanceStatus afterAttendanceStatus = crewAttendance.calculateAttendanceStatus(afterDateTime.getDate());

        outputView.printUpdateResult(beforeDateTime, beforeAttendanceStatus.getName(),
                afterDateTime, afterAttendanceStatus.getName());
    }

    private void handleRecordAttendance() {
        String nickName = inputView.readNickName();
        Crew crew = new Crew(nickName);
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsCrew(crew);

        List<AttendanceRecodeDto> attendanceRecodeDtos = crewAttendance.retrieveDateTimesOrderByDate()
                .stream()
                .map(AttendanceRecodeDto::from)
                .toList();

        AttendanceResultDto attendanceResultDto = AttendanceResultDto.of(
                nickName, crewAttendance.calculateAttendanceStatuses());

        outputView.printTotalAttendanceStatus(attendanceRecodeDtos, attendanceResultDto);
    }

    private void handleRiskAttendance() {
        List<CrewAttendance> crewAttendances = crewAttendanceRepository.findAll();
        List<PenaltyCrewDto> penaltyCrewDtos = crewAttendances.stream()
                .filter(CrewAttendance::isPenalty)
                .map(PenaltyCrewDto::from)
                .toList();
        outputView.printPenaltyCrews(penaltyCrewDtos);
    }

    private DateTime createDateTime(LocalDate localDate, LocalTime arriveTime) {
        return new DateTime(new Date(localDate),
                new Time(arriveTime.getHour(), arriveTime.getMinute()));
    }

    private DateTime createDateTime(LocalDateTime localDateTime, int day, LocalTime arriveTime) {
        return new DateTime(new Date(LocalDate.of(localDateTime.getYear(), localDateTime.getMonth(), day)),
                new Time(arriveTime.getHour(), arriveTime.getMinute()));
    }
}
