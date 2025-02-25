package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CrewTest {

    @Test
    void 정해진_글자_수를_벗어나면_예외를_발생시킨다() {
        // given & when & then
        assertThatThrownBy(
                () -> new Crew("이름입니다")
        ).isInstanceOf(IllegalArgumentException.class).hasMessage("크루 이름은 2글자 이상 4글자 이하여야 합니다.");
    }
}
