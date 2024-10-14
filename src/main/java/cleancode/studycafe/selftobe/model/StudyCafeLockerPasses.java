package cleancode.studycafe.selftobe.model;

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

  public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafePass selectedPass) {
    return lockerPasses.stream()
      .filter(studyCafeLockerPass -> studyCafeLockerPass.getPassType() == selectedPass.getPassType())
      .filter(studyCafeLockerPass -> studyCafeLockerPass.getDuration() == selectedPass.getDuration())
      .findFirst();
  }
}
