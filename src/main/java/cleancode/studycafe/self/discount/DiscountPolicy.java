package cleancode.studycafe.self.discount;

import cleancode.studycafe.self.pass.Pass;

import java.time.Duration;
import java.util.Arrays;

public enum DiscountPolicy {
  NONE("할인 없음", 0, Duration.ZERO),
  TEN_PERCENT("10% 할인", 10, Duration.ofDays(7 * 2)),
  FIFTEEN_PERCENT("15% 할인", 15, Duration.ofDays(7 * 4));

  private final String description;
  private final int discountRate;
  private final Duration thresholdDuration;

  DiscountPolicy(String description, int discountRate, Duration thresholdDuration) {
    this.description = description;
    this.discountRate = discountRate;
    this.thresholdDuration = thresholdDuration;
  }

  public static boolean canUseDiscount(Pass pass) {
    return getDiscountRate(pass) > 0;
  }

  public static double getDiscountPrice(Pass pass) {
    int discountRate = getDiscountRate(pass);
    int price = pass.getPrice();

    return (double) (price * discountRate) / 100;
  }

  public static double getDiscountedPrice(Pass pass) {
    int discountRate = getDiscountRate(pass);
    int price = pass.getPrice();

    return (double) price * (100 - discountRate) / 100;
  }

  private static int getDiscountRate(Pass pass) {
    Duration passDuration = pass.getDuration();

    return Arrays.stream(DiscountPolicy.values())
      .filter(discount -> discount.getThresholdDuration().compareTo(passDuration) > 0)
      .mapToInt(DiscountPolicy::getDiscountRate)
      .max()
      .orElse(NONE.getDiscountRate());
  }

  private int getDiscountRate() {
    return discountRate;
  }

  private Duration getThresholdDuration() {
    return thresholdDuration;
  }
}
