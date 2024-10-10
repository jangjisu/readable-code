package cleancode.studycafe.self.model;

import cleancode.studycafe.self.pass.PassType;

public class StudyCafeLockerPass {

    private final PassType passType;
    private final int duration;
    private final int price;

    private StudyCafeLockerPass(PassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafeLockerPass of(PassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
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
