package view.cell;

import model.Dir;
import model.cell.Cell;

public class FreeView extends CellView {
    public FreeView(Cell cell) {
        super(cell);
    }

    @Override
    protected void paintCenter(int bkColor) {
        write(' ', cell.hasColor() ? getColor() : CELL_EMPTY);
    }

    @Override
    protected void paintSide(Dir dir, int bkColor) {
        write(' ', dir == cell.nextDir || dir == cell.prevDir ? getColor() : bkColor);
    }

    @Override
    protected void paintCorner(int bkColor) {
        super.paintCorner(bkColor);
    }
}
