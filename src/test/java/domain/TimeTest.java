package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void 캠퍼스_운영_시간이_아니라면_예외를_발생시킨다() {
        assertThatThrownBy(() ->
                new Time(7,30)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("캠퍼스 운영 시간이 아닙니다.");
    }

    @Test
    void 최대_분과_최소_분에_벗어나는_값이면_예외를_발생시킨다() {
        assertThatThrownBy(() ->
                new Time(8,70)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("분은 0 이상 59 이하여야 합니다.");
    }
}
