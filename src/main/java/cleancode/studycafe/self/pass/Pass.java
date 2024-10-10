package cleancode.studycafe.self.pass;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static cleancode.studycafe.self.pass.PassType.*;
import static java.time.Duration.ofDays;
import static java.time.Duration.ofHours;

public enum Pass {
  HOURLY_2("2시간권", HOURLY, ofHours(2), 3_000),
  HOURLY_4("4시간권", HOURLY, ofHours(4), 6_500),
  HOURLY_6("6시간권", HOURLY, ofHours(6), 9_000),
  HOURLY_8("8시간권", HOURLY, ofHours(8), 11_000),
  HOURLY_10("10시간권", WEEKLY, ofHours(10), 12_000),
  HOURLY_12("12시간권", WEEKLY, ofHours(12), 13_000),
  WEEKLY_1("1주일권", WEEKLY, ofDays(7), 60_000),
  WEEKLY_2("2주일권", WEEKLY, ofDays(7L * 2), 100_000),
  WEEKLY_3("3주일권", WEEKLY, ofDays(7L * 3), 130_000),
  WEEKLY_4("4주일권", WEEKLY, ofDays(7L * 4), 150_000),
  WEEKLY_12("12주일권", WEEKLY, ofDays(7L * 12), 400_000),
  FIXED_4("고정석 4주권", FIXED, ofDays(7L * 4), 250_000),
  FIXED_12("고정석 12주권", FIXED, ofDays(7L * 12), 700_000),
  ;

  private final String description;
  private final PassType passType;
  private final Duration duration;
  private final int price;

  Pass(String description, PassType passType, Duration duration, int price) {
    this.description = description;
    this.passType = passType;
    this.duration = duration;
    this.price = price;
  }

  public PassType getPassType() {
    return passType;
  }

  public int getPrice() {
    return price;
  }

  public static List<Pass> getStudyCafePasses(PassType selectedPassType) {
    return Stream.of(values())
      .filter(cafePass -> cafePass.getPassType().equals(selectedPassType))
      .toList();
  }

  public static Pass getStudyCafePassByIndex(PassType passType, int index) {
    return getStudyCafePasses(passType).get(index);
  }

  public Duration getDuration() {
    return duration;
  }

  public String display() {
    return String.format("%s - %d원", description, price);
  }
}
