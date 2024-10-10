package cleancode.studycafe.self.pass;

import java.util.List;
import java.util.stream.Stream;

import static cleancode.studycafe.self.pass.Pass.FIXED_12;
import static cleancode.studycafe.self.pass.Pass.FIXED_4;

public enum LockerPass {
  WEEK_4("4주권", List.of(FIXED_4), 10000),
  WEEK_12("12주권", List.of(FIXED_12), 30000),
  ;

  private final String description;
  private final List<Pass> availablePasses;
  private final int price;

  LockerPass(String description, List<Pass> availablePasses, int price) {
    this.description = description;
    this.availablePasses = availablePasses;
    this.price = price;
  }

  public static List<LockerPass> getAvailableLockerPasses(Pass pass) {
    return Stream.of(values())
      .filter(lockerPass -> lockerPass.getAvailablePassTypes().contains(pass))
      .toList();
  }

  public static LockerPass getAvailableLockerPass(Pass pass) {
    return Stream.of(values())
      .filter(locker -> locker.getAvailablePassTypes().contains(pass))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다"));
  }

  public static boolean canUseLocker(Pass pass) {
    return !getAvailableLockerPasses(pass).isEmpty();
  }

  private List<Pass> getAvailablePassTypes() {
    return availablePasses;
  }

  public int getPrice() {
    return price;
  }

  public String display() {
    return String.format("%s - %d원", description, price);
  }
}
