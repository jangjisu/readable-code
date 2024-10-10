package cleancode.studycafe.self.io;

import cleancode.studycafe.self.pass.LockerPass;
import cleancode.studycafe.self.pass.Pass;
import cleancode.studycafe.self.model.StudyCafeLockerPass;
import cleancode.studycafe.self.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {
  void showWelcomeMessage();

  void showAnnouncement();

  void showPassTypeSelection();

  void showPassListForSelection(List<Pass> passes);

  void askLockerPass(LockerPass lockerPass);

  void showPassOrderSummaryWithLocker(Pass selectedPass, LockerPass lockerPass, boolean isDiscountTarget);

  void showPassOrderSummary(Pass selectedPass, boolean isDiscountTarget);

  void showSimpleMessage(String message);

}
