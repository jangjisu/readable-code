package cleancode.studycafe.self.io;

import cleancode.studycafe.self.pass.Pass;
import cleancode.studycafe.self.pass.PassType;

public interface InputHandler {
  PassType getPasstypeFromUser();

  Pass getPassFromUser(PassType passType);

  boolean doesUserChooseLockerPass();
}
