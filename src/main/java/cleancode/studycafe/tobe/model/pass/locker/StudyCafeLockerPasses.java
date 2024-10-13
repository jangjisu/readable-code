package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
  private final List<StudyCafeLockerPass> lockerPasses;

  public StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
    this.lockerPasses = lockerPasses;
  }

  public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> lockerPasses) {
    return new StudyCafeLockerPasses(lockerPasses);
  }

  public List<StudyCafeLockerPass> findPassBy(StudyCafePassType studyCafePassType) {
    return lockerPasses.stream()
      .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
      .toList();
  }

  public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafeSeatPass pass) {
    return lockerPasses.stream()
      .filter(pass::isSameDurationType)
      .findFirst();
  }
}
