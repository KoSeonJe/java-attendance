package controller;

import controller.dto.PenaltyCrewDto;
import domain.CrewAttendance;
import domain.CrewAttendanceRepository;
import java.util.List;
import view.OutputView;

public class RiskMenuExecutor implements MenuExecutor {

    private final CrewAttendanceRepository crewAttendanceRepository;
    private final OutputView outputView;

    public RiskMenuExecutor(CrewAttendanceRepository crewAttendanceRepository, OutputView outputView) {
        this.crewAttendanceRepository = crewAttendanceRepository;
        this.outputView = outputView;
    }

    @Override
    public void execute() {
        List<CrewAttendance> crewAttendances = crewAttendanceRepository.findAll();
        List<PenaltyCrewDto> penaltyCrewDtos = crewAttendances.stream()
                .filter(CrewAttendance::isPenalty)
                .map(PenaltyCrewDto::from)
                .toList();
        outputView.printPenaltyCrews(penaltyCrewDtos);
    }
}
