package view.cell;

import model.Dir;
import model.cell.Cell;


public class TerminalView extends CellView {
    public TerminalView(Cell cell) {
        super(cell);
    }

    @Override
    protected void paintCenter(int bkColor) {
        write('O', getColor());
    }

    @Override
    protected void paintSide(Dir dir, int bkColor) {
        write(' ', getColor());
    }

    @Override
    protected void paintCorner(int bkColor) {
        write(' ', getColor());
    }
}
