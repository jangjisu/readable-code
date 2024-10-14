package cleancode.studycafe.selftobe.model;

import java.util.Set;

public enum StudyCafePassType {

  HOURLY("시간 단위 이용권"),
  WEEKLY("주 단위 이용권"),
  FIXED("1인 고정석");

  private final String description;

  private static final Set<StudyCafePassType> canUseLockerPassTypes = Set.of(StudyCafePassType.FIXED);

  StudyCafePassType(String description) {
    this.description = description;
  }

  private boolean isPassTypeCanUseLocker() {
    return canUseLockerPassTypes.contains(this);
  }

  public boolean isPassTypeCanNotUseLocker() {
    return !isPassTypeCanUseLocker();
  }

}
