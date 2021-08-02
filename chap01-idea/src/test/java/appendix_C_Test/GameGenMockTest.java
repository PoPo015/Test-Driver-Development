package appendix_C_Test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {


    @Test
    @DisplayName("Mockitor.mock()을 이용한 목키토 객체생성")
    void startMockTest(){
        GameNumGen genMock = mock(GameNumGen.class);
    }

    @Test
    @DisplayName("BDDMockito.given()을 이용한 스텁")
    void mockTest() {
        //모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        //모의 객체의 메서드 호출을 전달하고, willReturn 메서드로 스텁을 정의한 메서드가 리턴할 값을 지정
        //genMock.generate(GameLevel.EASY)가 불리면 "123"을 리턴하라 라는 설정
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertThat(num).isEqualTo("123");

    }

    @Test
    @DisplayName("특정 타입의 익셉션을 발생하도록 스텁설정")
    void mockThrowTest() {
        //모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        //모의 객체의 메서드 호출을 전달하고, willThrow 메서드로 스텁을 정의한 예외발생생
        given(genMock.generate((GameLevel) null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> genMock.generate((GameLevel) null));
    }

    @Test
    @DisplayName("리턴타입이 void인 메소드에 대한 익셉션 발생")
    void voidMethodWillThrowTest(){
        List<String> mockList = mock(List.class);
        // 발생할 익셉션타입이나, 익셉션객체를 인자로받는다.
        // 이어서 given() 메서드는 모의객체를 전달받는다.
        //given 메서드는 인자로 전달받은 모의 객체 자신을 리턴하는데 이떄 익셉션을 발생할 메서드를 호출한다

        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThrows(
                UnsupportedOperationException.class,
                () -> mockList.clear()
        );

    }

    @Test
    @DisplayName("ArgumentMatchers 클래스로 임의의 값에 일치하도록 설정하는 방법")
    void anyMatchTest(){
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate((GameLevel) any())).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertThat(num).isEqualTo("123");

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertThat(num).isEqualTo("123");
    }


    @Test
    @DisplayName("임의의 값과 일치하는 인자와 정확하게 일치하는 인자 사용")
    void maxAnyAndEq(){
        List<String> mockList = mock(List.class);

        given(mockList.set(anyInt(),eq("123"))).willReturn("456");

        String old = mockList.set(5 , "123");
        Assertions.assertThat("456").isEqualTo(old);

    }

    @Test
    @DisplayName("실제로 모의 객체가 불렸는지 검증")
    void init(){
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        then(genMock).should().generate(GameLevel.class);

    }

    private UserReigster userReigster;
    private EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @Test
    @DisplayName("모의 객체를 호출할떄 검증 * 인자캡쳐")
    void whenRegisterThenSendMail(){
        userReigster.register("id" , "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertThat("email@email.com").isEqualTo(realEmail);
    }


}
