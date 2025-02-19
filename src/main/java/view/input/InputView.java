package view.input;

import domain.Day;
import domain.MenuOption;
import java.time.LocalDateTime;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public MenuOption readMenuOption(LocalDateTime localDateTime) {
        System.out.printf("오늘은 %d월 %d일 %s요일입니다. 기능을 선택해 주세요.%n",
                localDateTime.getMonth().getValue(), localDateTime.getDayOfMonth(),
                Day.findByDayOfWeek(localDateTime.getDayOfWeek()).getDayOfWeekKorean());
        MenuOption.findAll()
                .forEach(menuOption -> System.out.println(menuOption.getCode() + ". " + menuOption.getDescription()));

        String input = readInput();
        return MenuOption.findByCode(input);
    }

    private String readInput() {
        return scanner.nextLine();
    }
}
