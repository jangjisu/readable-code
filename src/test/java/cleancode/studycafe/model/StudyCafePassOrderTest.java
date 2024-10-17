package cleancode.studycafe.model;

import cleancode.studycafe.tobe.model.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudyCafePassOrderTest {
  @DisplayName("스터디카페 주문내역의 총합 금액을 구할 수 있다.")
  @Test
  void calculateTotalPrice() {
    // given
    StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);
    StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 100000);
    StudyCafePassOrder studyCafePassOrder = StudyCafePassOrder.of(seatPass, lockerPass);

    int predictedTotalPrice = seatPass.getPrice() - seatPass.getDiscountPrice() + lockerPass.getPrice();

    // when
    int totalPrice = studyCafePassOrder.getTotalPrice();

    // then
    assertThat(totalPrice).isEqualTo(predictedTotalPrice);
  }

  @DisplayName("스터디카페 주문내역의 할인된 금액을 구할 수 있다.")
  @Test
  void calculateDiscountPrice() {
    // given
    StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);

    int predictedDiscountPrice = (int) (10000 * 0.1);
    // when
    int disCountPrice = seatPass.getDiscountPrice();

    // then
    assertThat(disCountPrice).isEqualTo(predictedDiscountPrice);
  }
}
