package cleancode.studycafe.selftobe;

import cleancode.studycafe.selftobe.exception.AppException;
import cleancode.studycafe.selftobe.io.InputHandler;
import cleancode.studycafe.selftobe.io.OutputHandler;
import cleancode.studycafe.selftobe.io.StudyCafeFileHandler;
import cleancode.studycafe.selftobe.model.StudyCafeLockerPass;
import cleancode.studycafe.selftobe.model.StudyCafePass;
import cleancode.studycafe.selftobe.model.StudyCafePassType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudyCafePassMachine {

  private final InputHandler inputHandler = new InputHandler();
  private final OutputHandler outputHandler = new OutputHandler();

  private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

  public void run() {
    try {
      //시작 메시지 출력 부분
      outputHandler.showWelcomeMessage();
      outputHandler.showAnnouncement();

      //이용권 타입 가져오는 부분
      StudyCafePassType selectedPassType = getSelectedPassTypeFromUser();

      //전체 이용권목록 가져오기
      List<StudyCafePass> selectedPassTypePasses = getSelectedPassTypePassesFromUser(selectedPassType);

      //사용자가 사용할 이용권 선택
      StudyCafePass selectedPass = getSelectedPassFromUser(selectedPassTypePasses);

      //사물함 이용가능할 경우, 사물함 이용 여부 선택
      Optional<StudyCafeLockerPass> lockerPass = doesUserWantToUseLocker(selectedPass);

      //Summary 출력
      lockerPass.ifPresentOrElse(
        selectedLockerPass -> outputHandler.showPassOrderSummary(selectedPass, selectedLockerPass),
        () -> outputHandler.showPassOrderSummary(selectedPass, null)
      );

    } catch (AppException e) {
      outputHandler.showSimpleMessage(e.getMessage());
    } catch (Exception e) {
      outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
    }
  }

  private StudyCafePass getSelectedPassFromUser(List<StudyCafePass> selectedPassTypePasses) {
    outputHandler.showPassListForSelection(selectedPassTypePasses);
    return inputHandler.getSelectPass(selectedPassTypePasses);
  }

  private List<StudyCafePass> getSelectedPassTypePassesFromUser(StudyCafePassType selectedPassType) {
    List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();

    return studyCafePasses.stream()
      .filter(studyCafePass -> studyCafePass.getPassType() == selectedPassType)
      .toList();
  }

  private StudyCafePassType getSelectedPassTypeFromUser() {
    outputHandler.askPassTypeSelection();
    return inputHandler.getPassTypeSelectingUserAction();
  }

  private Optional<StudyCafeLockerPass> doesUserWantToUseLocker(StudyCafePass selectedPass) {
    if (selectedPass.isPassCanNotUseLocker()) {
      return Optional.empty();
    }

    List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

    StudyCafeLockerPass lockerPass = lockerPasses.stream()
      .filter(option ->
        option.getPassType() == selectedPass.getPassType()
          && option.getDuration() == selectedPass.getDuration()
      )
      .findFirst()
      .orElse(null);

    outputHandler.askLockerPass(Objects.requireNonNull(lockerPass));
    boolean lockerSelection = inputHandler.getLockerSelection();

    if(lockerSelection) {
      return Optional.of(lockerPass);
    }

    return Optional.of(null);

  }

}
