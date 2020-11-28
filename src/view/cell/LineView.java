package view.cell;

import model.Dir;
import model.cell.Cell;
import model.cell.Line;


public class LineView extends CellView {
    public LineView(Cell cell) {
        super(cell);
    }

    @Override
    protected void paintCenter(int bkColor) {
        write(' ', cell.hasColor() ? getColor() : CELL_EMPTY);
    }

    @Override
    protected void paintSide(Dir dir, int bkColor) {
        Line cell1 = (Line) cell;
        if (!cell1.getOrient() && (dir.equals(Dir.LEFT) || dir.equals(Dir.RIGHT))) {
            write(' ', dir == cell.nextDir || dir == cell.prevDir ? getColor() : CELL_EMPTY);
            return;
        }
        if (cell1.getOrient() && (dir.equals(Dir.UP) || dir.equals(Dir.DOWN))) {
            write(' ', dir == cell.nextDir || dir == cell.prevDir ? getColor() : CELL_EMPTY);
            return;
        }
        write(' ', bkColor);
    }

    @Override
    protected void paintCorner(int bkColor) {
        super.paintCorner(bkColor);
    }
}

