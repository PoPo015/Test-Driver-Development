import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {


    @Test
    void subString(){
        String str = "abcde";

        Assertions.assertThat(str).isEqualTo("abcde");

    }
}
