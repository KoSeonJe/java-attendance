package domain;

import java.util.Arrays;
import java.util.List;

public enum MenuOption {
    CHECK("1", "출석 확인"),
    EDIT("2", "출석 수정"),
    RECORD("3", "크루별 출석 기록 확인"),
    RISK("4", "제적 위험자 확인"),
    EXIT("Q", "종료");

    private final String code;
    private final String description;

    MenuOption(final String code, final String description) {
        this.code = code;
        this.description = description;
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
}
