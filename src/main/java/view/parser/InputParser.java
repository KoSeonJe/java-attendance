package view.parser;


public class InputParser {

    public int parseToInt(String inputMenuNumber) {
        try {
            return Integer.parseInt(inputMenuNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자로 변환할 수 없는 입력값입니다");
        }
    }
}
