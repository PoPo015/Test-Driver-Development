package chap02.Test.PasswordStrength;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordStrengthMeterTest {

    //중복코드 리팩토링 // 암호 객체 생성
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    //중복된 암호 검증 메서드 추출
    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertThat(result).isEqualTo(expStr);
    }

    @Test
    void 암호검사_모든규칙을_3개_충족() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    void 값이없는경우_0개_충족(){
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 숫자_대문자_2개_충족() {
        assertStrength("ab123AB", PasswordStrength.NORMAL);
    }

    @Test
    void 길이_대문자_2개_충족() {
        assertStrength("abcABCAa", PasswordStrength.NORMAL);
    }

    @Test
    void 길이_숫자_2개_충족(){
        assertStrength("abcd1234", PasswordStrength.NORMAL);
    }

    @Test
    void 길이_1개_충족(){
        assertStrength("abcdabcd", PasswordStrength.WEAK);
    }

    @Test
    void 숫자_1개_충족(){
        assertStrength("abcd12", PasswordStrength.WEAK);
    }

    @Test
    void 대문자_1개_충족(){
        assertStrength("ABCDab", PasswordStrength.WEAK);
    }

    @Test
    void 아무조건_0개_충족(){
        assertStrength("ac",PasswordStrength.WEAK);
    }


}
