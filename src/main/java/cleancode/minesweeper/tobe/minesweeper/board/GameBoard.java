package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.*;
import cleancode.minesweeper.tobe.minesweeper.board.cell.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.cell.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.board.cell.position.RelativePosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameBoard {
    public final int landMineCount;
    private final Cell[][] board;
    private GameStatus gameStatus;

    public GameBoard(GameLevel gameLevel) {
        int colSize = gameLevel.getColSize();
        int rowSize = gameLevel.getRowSize();
        board = new Cell[rowSize][colSize];

        landMineCount = gameLevel.getLandMineCount();
        initalizeGameStatus();
    }

    public void initalizeGame() {
        initalizeGameStatus();
        CellPositions cellPositions = CellPositions.from(board);
        initalizeEmptyCells(cellPositions);

        List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
        initalizeLandMineCells(landMinePositions);

        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
        initalizeNumberCells(numberPositionCandidates);
    }

    public void openAt(CellPosition cellPosition) {
        if (isLandMineCellAt(cellPosition)) {
            openOneCellAt(cellPosition);
            changeGameStatusToLose();
            return;
        }

        openSurrounedCells2(cellPosition);
        checkIfGameIsOver();
    }

    public void flagAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.flag();

        checkIfGameIsOver();
    }

    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
                || cellPosition.isColIndexMoreThanOrEqual(colSize);
    }

    public boolean isInProgress() {
        return gameStatus == GameStatus.IN_PROGRESS;
    }

    public boolean isWinStatus() {
        return gameStatus == GameStatus.WIN;
    }

    public boolean isLoseStatus() {
        return gameStatus == GameStatus.LOSE;
    }

    public CellSnapshot getSnapshot(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.getSnapshot();
    }

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    private void initalizeGameStatus() {
        gameStatus = GameStatus.IN_PROGRESS;
    }

    private void initalizeEmptyCells(CellPositions cellPositions) {
        List<CellPosition> allPositions = cellPositions.getPosition();
        for (CellPosition position : allPositions) {
            updateCellAt(position, new EmptyCell());
        }
    }

    private void initalizeLandMineCells(List<CellPosition> landminePositions) {
        for (CellPosition position : landminePositions) {
            updateCellAt(position, new LandmineCell());
        }
    }

    private void initalizeNumberCells(List<CellPosition> numberPositionCandidates) {
        for (CellPosition position : numberPositionCandidates) {
            int count = countNearbyLandMines(position);
            if (count != 0) {
                updateCellAt(position, new NumberCell(count));
            }
        }
    }

    private int countNearbyLandMines(CellPosition cellPosition) {
        long count = calculateSurroundedPositions(cellPosition).stream()
                .filter(this::isLandMineCellAt)
                .count();
        return (int) count;
    }

    private List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition) {
        return RelativePosition.SURROUNED_POSITIONS.stream()
                .filter(cellPosition::canCalculatePositionBy)
                .map(cellPosition::calculatePositionBy)
                .filter(position -> position.isRowIndexLessThan(getRowSize()))
                .filter(position -> position.isColIndexLessThan(getColSize()))
                .toList();
    }

    private void updateCellAt(CellPosition position, Cell cell) {
        board[position.getRowIndex()][position.getColIndex()] = cell;
    }

    private void openSurrounedCells(CellPosition cellPosition) {
        if (isOpenedCell(cellPosition)) {
            return;
        }
        if (isLandMineCellAt(cellPosition)) {
            return;
        }

        openOneCellAt(cellPosition);

        if (doesCellHaveLandMineCount(cellPosition)) {
            return;
        }

        List<CellPosition> cellPositions = calculateSurroundedPositions(cellPosition);
        cellPositions.forEach(this::openSurrounedCells2);
    }

    private void openSurrounedCells2(CellPosition cellPosition) {
        Deque<CellPosition> deque = new ArrayDeque<>();
        deque.push(cellPosition);

        while (!deque.isEmpty()) {
            openAndPushCellAt(deque);
        }
    }

    private void openAndPushCellAt(Deque<CellPosition> deque) {
        CellPosition currentCellPosition = deque.pop();
        if (isOpenedCell(currentCellPosition)) {
            return;
        }
        if (isLandMineCellAt(currentCellPosition)) {
            return;
        }

        openOneCellAt(currentCellPosition);

        if (doesCellHaveLandMineCount(currentCellPosition)) {
            return;
        }

        List<CellPosition> surroundedPositions = calculateSurroundedPositions(currentCellPosition);
        for (CellPosition surroundedPosition : surroundedPositions) {
            deque.push(surroundedPosition);
        }
    }

    private void openOneCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        cell.open();
    }

    private boolean isOpenedCell(CellPosition cellPosition) {
        return findCell(cellPosition).isOpened();
    }

    private boolean isLandMineCellAt(CellPosition cellPosition) {
        Cell cell = findCell(cellPosition);
        return cell.isLandMine();
    }

    private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
        return findCell(cellPosition).hasLandMineCount();
    }

    private void checkIfGameIsOver() {
        if (isAllCellChecked()) {
            changeGameStatusToWin();
        }
    }

    private boolean isAllCellChecked() {
        Cells cells = Cells.from(board);

        return cells.isAllChecked();

    }

    private void changeGameStatusToWin() {
        gameStatus = GameStatus.WIN;
    }

    private void changeGameStatusToLose() {
        gameStatus = GameStatus.LOSE;
    }

    private Cell findCell(CellPosition cellPosition) {
        return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
    }
}
