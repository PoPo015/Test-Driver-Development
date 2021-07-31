package chpa03.Test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpiryDateCalculatorTest {

    /**
     *  테스트 서비스규칙
     *  1. 서비스를 사용하려면 매달 1만원을 선불로납부한다. 납부일기준으로 한달뒤가 서비스 만료일이된다.
     *  2. 2개월이상 요금을 납부할수 있다.
     *  3. 10만원을 납부하면 서비스를 1년 제공한다.
     *
     */


    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expirytDate = cal.calculateExpiryDate(payData);
        assertThat(expirytDate).isEqualTo(expectedExpiryDate);
    }

    @Test
    void 만원_납부하면_한달뒤가_만료일됨() {
//        assertExpiryDate(LocalDate.of(2019,5,1),10000,LocalDate.of(2019,6,1));
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(10000)
                .build(), LocalDate.of(2019, 4, 1));
    }

    @Test
    void 납부일과_한달뒤_일자가_같지않음() {

//        assertExpiryDate(LocalDate.of(2019,1,31),10000,LocalDate.of(2019,2,28));
//        assertExpiryDate(LocalDate.of(2019,5,31),10000,LocalDate.of(2019,6,30));
//        assertExpiryDate(LocalDate.of(2020,1,31),10000,LocalDate.of(2020,2,29));
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .payAmount(10000)
                .build(), LocalDate.of(2019, 2, 28));
    }

    @Test
    void 첫납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .payAmount(10000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 7, 31));

    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20000)
                        .build(),
                LocalDate.of(2019, 5, 1));

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30000)
                        .build(),
                LocalDate.of(2019, 6, 1));

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 1))
                        .payAmount(50000)
                        .build(),
                LocalDate.of(2019, 10, 1));

    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원이상_납부() {

        assertExpiryDate(
                PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2 ,28))
                .payAmount(20000)
                .build(), LocalDate.of(2019,4,30));

        assertExpiryDate(
                PayData.builder()
                .firstBillingDate(LocalDate.of(2019,3,31))
                .billingDate(LocalDate.of(2019,4 ,30))
                .payAmount(30000)
                .build(), LocalDate.of(2019,7,31));

    }

    @Test
    void 십만원을_납부하면_1년제공(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,1 ,28))
                        .payAmount(100000)
                        .build(), LocalDate.of(2020,1,28));
    }



}