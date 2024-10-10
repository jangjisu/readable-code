package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePasses {
  private final List<StudyCafePass> passses;

  public StudyCafePasses(List<StudyCafePass> passses) {
    this.passses = passses;
  }

  public static StudyCafePasses of(List<StudyCafePass> passses) {
    return new StudyCafePasses(passses);
  }

  public List<StudyCafePass> findPassBy(StudyCafePassType studyCafePassType) {
    return passses.stream()
      .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
      .toList();
  }
}
