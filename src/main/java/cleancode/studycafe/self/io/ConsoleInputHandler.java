package cleancode.studycafe.self.io;

import cleancode.studycafe.self.pass.Pass;
import cleancode.studycafe.self.exception.AppException;
import cleancode.studycafe.self.pass.PassType;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public PassType getPasstypeFromUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return PassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return PassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return PassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    @Override
    public Pass getPassFromUser(PassType passType) {
        String userInput = SCANNER.nextLine();
        int inputIndex = Integer.parseInt(userInput) - 1;
        return Pass.getStudyCafePassByIndex(passType, inputIndex);
    }

    @Override
    public boolean doesUserChooseLockerPass() {
        String userInput = SCANNER.nextLine();
        return "1".equals(userInput);
    }

}
