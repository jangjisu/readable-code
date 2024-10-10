package cleancode.studycafe.self;

import cleancode.studycafe.self.discount.DiscountPolicy;
import cleancode.studycafe.self.exception.AppException;
import cleancode.studycafe.self.io.InputHandler;
import cleancode.studycafe.self.io.OutputHandler;
import cleancode.studycafe.self.pass.LockerPass;
import cleancode.studycafe.self.pass.Pass;
import cleancode.studycafe.self.pass.PassType;

import java.util.List;

public class StudyCafePassMachine {

  private final InputHandler inputHandler;
  private final OutputHandler outputHandler;

  public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler) {
    this.inputHandler = inputHandler;
    this.outputHandler = outputHandler;
  }

  public void run() {
    try {
      outputHandler.showWelcomeMessage();
      outputHandler.showAnnouncement();

      outputHandler.showPassTypeSelection();
      PassType selectedPassType = inputHandler.getPasstypeFromUser();

      List<Pass> selectedPasses = getSelectedPassTypePasses(selectedPassType);
      outputHandler.showPassListForSelection(selectedPasses);

      Pass selectedPass = inputHandler.getPassFromUser(selectedPassType);

      if (LockerPass.canUseLocker(selectedPass)) {
        LockerPass lockerPass = LockerPass.getAvailableLockerPass(selectedPass);
        outputHandler.askLockerPass(lockerPass);
        if (inputHandler.doesUserChooseLockerPass()) {
          outputHandler.showPassOrderSummaryWithLocker(selectedPass, lockerPass, DiscountPolicy.canUseDiscount(selectedPass));
          return;
        }
      }

      outputHandler.showPassOrderSummary(selectedPass, DiscountPolicy.canUseDiscount(selectedPass));

    } catch (AppException e) {
      outputHandler.showSimpleMessage(e.getMessage());
    } catch (Exception e) {
      outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
    }
  }

  private List<Pass> getSelectedPassTypePasses(PassType passType) {
    return Pass.getStudyCafePasses(passType);
  }
}
