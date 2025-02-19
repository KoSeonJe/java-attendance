package controller;

import domain.AttendanceStatus;
import domain.CrewRepository;
import domain.Day;
import domain.MenuOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import view.InputView;
import view.OutputView;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(LocalDateTime dateTime) {
        while (true) {
            MenuOption menuOption = inputView.readMenuOption(dateTime);

            if (menuOption == MenuOption.CHECK) {
                String nickName = inputView.readNickName();
                LocalTime arriveTime = inputView.readArriveTime();
                Day today = Day.findByDayOfWeek(dateTime.getDayOfWeek());
                AttendanceStatus attendanceStatus = AttendanceStatus.calculateDiscriminator(today, dateTime);

                LocalDateTime attendanceDateTime = LocalDateTime.of(dateTime.toLocalDate(), arriveTime);
                CrewRepository.getInstance().findByName(nickName).addAttendance(attendanceDateTime);
                outputView.printArriveResult(dateTime, today.getDayOfWeekKorean(), attendanceStatus.getName());
            }
        }
    }
}
