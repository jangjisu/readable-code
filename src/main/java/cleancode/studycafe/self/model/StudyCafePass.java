package cleancode.studycafe.self.model;

import cleancode.studycafe.self.pass.PassType;

public class StudyCafePass {

    private final PassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(PassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(PassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public PassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String display() {
        if (passType == PassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == PassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == PassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

}
