package domain;

import controller.CheckMenuExecutor;
import controller.EditMenuExecutor;
import controller.ExitMenuExecutor;
import controller.MenuExecutor;
import controller.RecordMenuExecutor;
import controller.RiskMenuExecutor;
import java.util.Arrays;
import java.util.List;
import util.AttendanceApplicationTime;
import view.InputView;
import view.OutputView;

public enum MenuOption {
    CHECK("1", "출석 확인",
            new CheckMenuExecutor(
                    new AttendanceApplicationTime(),
                    new InputView(),
                    new OutputView(),
                    CrewAttendanceRepository.getInstance()
            )
    ),
    EDIT("2", "출석 수정",
            new EditMenuExecutor(
                    new AttendanceApplicationTime(),
                    new InputView(),
                    new OutputView(),
                    CrewAttendanceRepository.getInstance()
            )
    ),
    RECORD("3", "크루별 출석 기록 확인",
            new RecordMenuExecutor(
                    new InputView(),
                    new OutputView(),
                    CrewAttendanceRepository.getInstance()
            )
    ),
    RISK("4", "제적 위험자 확인",
            new RiskMenuExecutor(
                    CrewAttendanceRepository.getInstance(),
                    new OutputView()
            )
    ),
    EXIT("Q", "종료", new ExitMenuExecutor());

    private final String code;
    private final String description;
    private final MenuExecutor menuExecutor;

    MenuOption(final String code, final String description, MenuExecutor menuExecutor) {
        this.code = code;
        this.description = description;
        this.menuExecutor = menuExecutor;
    }

    public static MenuOption findByCode(final String code) {
        return Arrays.stream(values())
                .filter(option -> option.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 메뉴가 없습니다."));
    }

    public static List<MenuOption> findAll() {
        return Arrays.stream(MenuOption.values()).toList();
    }

    public boolean isExit() {
        return this == EXIT;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public MenuExecutor getMenuExecutor() {
        return menuExecutor;
    }

}
