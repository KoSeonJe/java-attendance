package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkDayTest {

    @Test
    void 주말의_등교_시간을_입력하면_예외를_발생시킨다() {
        assertThatThrownBy(
                WorkDay.SUNDAY::retrieveWeekdaysStartHour
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주말은 등교시간이 없습니다.");
    }
}
