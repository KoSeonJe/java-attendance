package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void 주말에_출석하면_예외를_발생시킨다() {
        // given & when & then
        assertThatThrownBy(() ->
                new Date(LocalDate.of(2024, 12, 14))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공휴일 및 주말에는 날짜를 등록할 수 없습니다.");
    }

    @Test
    void 공휴일에_출석하면_예외를_발생시킨다() {
        // given & when & then
        assertThatThrownBy(() ->
                new Date(LocalDate.of(2024, 12, 25))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공휴일 및 주말에는 날짜를 등록할 수 없습니다.");
    }

    @Test
    void 요구하는_기간_내가_아니라면_예외_발생시킨다() {
        // given & when & then
        assertThatThrownBy(() ->
                new Date(LocalDate.of(2024, 1, 25))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("2024년 12월이 아닌 날짜는 등록할 수 없습니다.");
    }
}
