package cleancode.studycafe.model.pass;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudyCafeSeatPassesTest {
  @DisplayName("좌석 이용권들 중, 선택한 이용권 종류에 해당하는 이용권들을 찾을 수 있다.")
  @Test
  void findSpecificPassTypePasses() {
    // given
    StudyCafeSeatPass fixedSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 10000, 0.1);
    StudyCafeSeatPass fixedSeatPass2 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 50000, 0.1);
    StudyCafeSeatPass fixedSeatPass3 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 24, 200000, 0.1);
    StudyCafeSeatPass hourlySeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 10000, 0.1);
    StudyCafeSeatPass hourlySeatPass2 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 50000, 0.1);

    List<StudyCafeSeatPass> passes = List.of(fixedSeatPass, fixedSeatPass2, fixedSeatPass3, hourlySeatPass, hourlySeatPass2);
    StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(passes);

    // when
    List<StudyCafeSeatPass> fixTypePasses = studyCafeSeatPasses.findPassBy(StudyCafePassType.FIXED);

    // then
    assertThat(fixTypePasses).containsExactlyInAnyOrder(fixedSeatPass, fixedSeatPass2, fixedSeatPass3);
  }
}
