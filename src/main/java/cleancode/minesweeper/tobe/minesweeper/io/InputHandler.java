package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.cell.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

public interface InputHandler {
    UserAction getUserActionFromUser();

    CellPosition getCellPositionFromUser();
}
