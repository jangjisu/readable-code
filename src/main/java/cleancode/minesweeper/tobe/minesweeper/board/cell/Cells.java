package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Arrays;
import java.util.List;

//일급 컬렉션으로 만든다 (필드가 Cell 하나만 존재한다)
public class Cells {
    private List<Cell> cells;

    private Cells(List<Cell> cells) {
        this.cells = cells;
    }

    public static Cells of(List<Cell> cells) {
        return new Cells(cells);
    }

    public static Cells from(Cell[][] cells) {
        List<Cell> cellList = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .toList();
        return of(cellList);
    }

    public boolean isAllChecked() {
        return cells.stream()
                .allMatch(Cell::isChecked);
    }

}
