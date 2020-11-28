package view;

import isel.leic.pg.Console;
import model.Circuit;
import model.Position;
import view.cell.CellView;

import java.awt.event.KeyEvent;

public class Panel {
    private Circuit model;
    private CellView[][] gridCV;
    private long startTime;

    /**
     * Wait the user to press the key 'Y' or 'N'
     *
     * @param msg - String which contains the question we want to do
     * @return True if the user press the key 'Y'
     */
    public boolean question(String msg) {
        int key;
        Console.cursor(model.getHeight() * CellView.CELL_SIDE, 0);
        Console.print(msg + "? (Y/N)");
        do {
            key = Console.getKeyPressed();
            if (key == KeyEvent.VK_N) {
                return false;
            }
        }
        while (key != KeyEvent.VK_Y);
        return true;
    }

    /**
     * Prints the message in the console
     *
     * @param msg - Message we want to print
     */
    public void message(String msg) {
        Console.cursor(model.getWidth() * CellView.CELL_SIDE, 0);
        Console.color(Console.YELLOW, Console.GRAY);
        Console.print(msg);
    }

    public void close() {
        Console.close();
    }

    /**
     * Opens the console, print all we need and fills the array two-dimensional of CellViews
     *
     * @param model - Circuit we want to print
     */
    public void open(Circuit model) {
        gridCV = new CellView[model.getHeight()][model.getWidth()];
        this.model = model;
        Console.open("Circuit", model.getHeight() * CellView.CELL_SIDE + 1, model.getWidth() * CellView.CELL_SIDE);
        Console.exit(true);
        startTime = System.currentTimeMillis();
        repaintTime();
        repaint();
    }

    /**
     * Prints int the console the time
     */
    public void repaintTime() {
        long sec = (System.currentTimeMillis() - startTime) / 1000;
        long min = sec / 60;
        sec %= 60;
        Console.setBackground(Console.GRAY);
        Console.cursor(model.getHeight() * 3, 0);
        Console.print((min > 9 ? "" : "0") + min + ":" + (sec > 9 ? "" : "0") + sec);
    }

    /**
     * @param line - Line of console of the mouse event
     * @param col  - Column of console of the mouse event
     * @return Position int the array gridCV of the mouse event
     */
    public Position getModelPosition(int line, int col) {
        if (line / CellView.CELL_SIDE >= model.getHeight() || col / CellView.CELL_SIDE >= model.getWidth() || line / CellView.CELL_SIDE < 0 || col / CellView.CELL_SIDE < 0) {
            return null;
        }
        return new Position(line / CellView.CELL_SIDE, col / CellView.CELL_SIDE);
    }

    /**
     * Paints the cell in the position p
     *
     * @param pos       - Position of the cell we want to print
     * @param highLight - True if it's highlighted
     */
    public void paint(Position pos, boolean highLight) {
        gridCV[pos.getLine()][pos.getCol()].paint(pos.getLine(), pos.getCol(), highLight);
    }

    /**
     * Paints CellViews in the console and the line of the messages
     */
    public void repaint() {
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                CellView c = CellView.newInstance(model.getCell(i, j));
                gridCV[i][j] = c;
                c.paint(i, j, false);
            }
        }
        Console.setBackground(Console.GRAY);
        for (int i = 0; i < (model.getWidth() * CellView.CELL_SIDE); i++) {
            Console.cursor(model.getHeight() * CellView.CELL_SIDE, i);
            Console.print(' ');
        }

    }

    public void sleep(long time) {
        Console.sleep(time);
    }
}
