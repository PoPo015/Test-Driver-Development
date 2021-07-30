package chap02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

    @Test
    void 더하기예제(){
        //given(준비)
        int result = Calculator.plus(4,1);
        //when(실행)

        //then(단언)
        assertThat(result).isEqualTo(5);

    }

}
