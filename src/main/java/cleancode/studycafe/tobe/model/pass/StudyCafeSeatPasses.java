package cleancode.studycafe.tobe.model.pass;

import java.util.List;

public class StudyCafeSeatPasses {
  private final List<StudyCafeSeatPass> passses;

  public StudyCafeSeatPasses(List<StudyCafeSeatPass> passses) {
    this.passses = passses;
  }

  public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passses) {
    return new StudyCafeSeatPasses(passses);
  }

  public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {
    return passses.stream()
      .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
      .toList();
  }
}
