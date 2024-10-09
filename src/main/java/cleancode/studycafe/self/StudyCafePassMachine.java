package cleancode.studycafe.self;

import cleancode.studycafe.self.exception.AppException;
import cleancode.studycafe.self.io.*;
import cleancode.studycafe.self.model.StudyCafeLockerPass;
import cleancode.studycafe.self.model.StudyCafePass;
import cleancode.studycafe.self.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    private StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

  public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler) {
    this.inputHandler = inputHandler;
    this.outputHandler = outputHandler;
  }

  public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
            List<StudyCafePass> selectedPasses = getSelectedPassTypePasses(studyCafePasses, studyCafePassType);
            if (studyCafePassType == StudyCafePassType.HOURLY) {
                outputHandler.showPassListForSelection(selectedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(selectedPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                outputHandler.showPassListForSelection(selectedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(selectedPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                outputHandler.showPassListForSelection(selectedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(selectedPasses);

                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.isHourlyPassType();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

  private static List<StudyCafePass> getSelectedPassTypePasses(List<StudyCafePass> studyCafePasses, StudyCafePassType passType) {
    return studyCafePasses.stream()
      .filter(studyCafePass -> studyCafePass.getPassType() == passType)
      .toList();
  }

}
