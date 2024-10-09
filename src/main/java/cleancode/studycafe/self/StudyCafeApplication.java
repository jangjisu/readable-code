package cleancode.studycafe.self;

import cleancode.studycafe.self.io.ConsoleInputHandler;
import cleancode.studycafe.self.io.ConsoleOutputHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
      ConsoleInputHandler inputHandler = new ConsoleInputHandler();
      ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();

      StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler);
        studyCafePassMachine.run();
    }

}
