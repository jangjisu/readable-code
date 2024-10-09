package cleancode.studycafe.self.io;

import cleancode.studycafe.self.model.StudyCafePass;
import cleancode.studycafe.self.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {
  StudyCafePassType getPassTypeSelectingUserAction();

  StudyCafePass getSelectPass(List<StudyCafePass> passes);

  boolean isHourlyPassType();
}
