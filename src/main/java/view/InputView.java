package view;

import domain.Day;
import domain.MenuOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public MenuOption readMenuOption(LocalDateTime localDateTime) {
        System.out.printf("오늘은 %d월 %d일 %s요일입니다. 기능을 선택해 주세요.%n",
                localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth(),
                Day.findByDayOfWeek(localDateTime.getDayOfWeek()).getDayOfWeekKorean());
        MenuOption.findAll()
                .forEach(menuOption -> System.out.println(menuOption.getCode() + ". " + menuOption.getDescription()));

        String input = readInput();
        return MenuOption.findByCode(input);
    }

    public String readNickName() {
        System.out.println("닉네임을 입력해 주세요.");
        return readInput();
    }

    public LocalTime readArriveTime() {
        try {
            System.out.println("등교 시간을 입력해 주세요.");
            String arriveTime = readInput().trim();
            return LocalTime.parse(arriveTime, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 시간 형식입니다. 올바른 형식 (HH:mm)으로 다시 입력해 주세요.");
        }
    }

    private String readInput() {
        return scanner.nextLine();
    }
}
