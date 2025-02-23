package controller;

import controller.dto.AttendanceRecodeDto;
import controller.dto.AttendanceResultDto;
import domain.Crew;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import java.util.List;
import view.InputView;
import view.OutputView;

public class RecordMenuExecutor implements MenuExecutor {

    private final InputView inputView;
    private final OutputView outputView;
    private final CrewAttendanceRepository crewAttendanceRepository;

    public RecordMenuExecutor(
            InputView inputView,
            OutputView outputView,
            CrewAttendanceRepository crewAttendanceRepository
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.crewAttendanceRepository = crewAttendanceRepository;
    }

    @Override
    public void execute() {
        String nickName = inputView.readNickName();
        CrewAttendance crewAttendance = crewAttendanceRepository.findByEqualsNickName(nickName);

        List<AttendanceRecodeDto> attendanceRecodeDtos = crewAttendance.retrieveDateTimesOrderByDate()
                .stream()
                .map(AttendanceRecodeDto::from)
                .toList();

        AttendanceResultDto attendanceResultDto = AttendanceResultDto.of(
                nickName, crewAttendance.calculateAttendanceStatuses());

        outputView.printTotalAttendanceStatus(attendanceRecodeDtos, attendanceResultDto);
    }
}
