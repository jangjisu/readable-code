package cleancode.studycafe.model.pass;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudyCafeSeatPassTest {

  @DisplayName("좌석 이용권이 동일한 종류임을 확인할 수 있다.")
  @Test
  void checkSamePassType() {
    // given
    StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);
    StudyCafePassType passType = StudyCafePassType.FIXED;

    // when
    boolean isSamePassType = seatPass.isSamePassType(passType);

    // then
    assertThat(isSamePassType).isTrue();
  }

  @DisplayName("좌석 이용권이 다른 종류임을 확인할 수 있다.")
  @Test
  void checkDifferentPassType() {
    // given
    StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);
    StudyCafePassType passType = StudyCafePassType.HOURLY;

    // when
    boolean isSamePassType = seatPass.isSamePassType(passType);

    // then
    assertThat(isSamePassType).isFalse();
  }

  @DisplayName("좌석 이용권이 사물함 이용권과 같은 기간과 종류임을 확인할 수 있다.")
  @Test
  void checkLockerPassSameDurationType() {
    // given
    StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);
    StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

    // when
    boolean sameDurationType = seatPass.isSameDurationType(lockerPass);

    // then
    assertThat(sameDurationType).isTrue();
  }

  @DisplayName("좌석 이용권이 사물함 이용권과 다른 기간이나, 종류가 다를경우 다름을 확인할 수 있다.")
  @Test
  void checkLockerPassDifferentDurationType() {
    // given
    StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);
    StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 10000);

    // when
    boolean sameDurationType = seatPass.isSameDurationType(lockerPass);

    // then
    assertThat(sameDurationType).isFalse();
  }


}
