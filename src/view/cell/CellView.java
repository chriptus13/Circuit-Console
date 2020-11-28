package view.cell;

import isel.leic.pg.Console;
import model.Dir;
import model.cell.Cell;
import model.cell.Free;
import model.cell.Line;
import model.cell.Terminal;


/**
 * Base class hierarchy of cell viewers
 */
public class CellView {
    public static final int CELL_SIDE = 3;  // Each side of the cell occupies 3 characters
    protected static final int CELL_EMPTY = Console.GRAY;
    // Matches between the color number in the model and the color displayed on the console
    private static final int[] COLORS = {
            Console.RED, Console.GREEN, Console.YELLOW,
            Console.BLUE, Console.MAGENTA, Console.ORANGE
    };

    protected final Cell cell; // Reference to the model cell

    CellView(Cell cell) {
        this.cell = cell;
    }

    /**
     * The color used to show the cell
     *
     * @return The console color to use in that cell
     */
    public int getColor() {
        return COLORS[cell.getColor()];
    }

    /**
     * Creates the appropriate view of the cell type of the model
     *
     * @param cell The model cell
     * @return The view for the cell
     */
    public static CellView newInstance(Cell cell) {
        if (cell instanceof Terminal) return new TerminalView(cell);
        if (cell instanceof Line) return new LineView(cell);
        if (cell instanceof Free) return new FreeView(cell);
        return new CellView(cell);
/*      try { // NOTE: Alternative implementation using reflexion
            Class vc = Class.forName("view.cell."+cell.getClass().getSimpleName()+"View");
            return (CellView) vc.getConstructor(Cell.class).newInstance(cell);
        } catch (Exception e) { return new CellView(cell); }
*/
    }

    /**
     * Helper method to write a char at the current position of the console with a background color
     *
     * @param v     Char to write
     * @param color Background color to use
     */
    protected static void write(char v, int color) {
        Console.setBackground(color);
        Console.print(v);
    }

    /**
     * Paint the cell of a coordinate (of the model) with or without highlight.
     *
     * @param l         Line in model
     * @param c         Column in model
     * @param highLight Is to present highlighted
     */
    public void paint(int l, int c, boolean highLight) {
        l *= CELL_SIDE;
        l++;
        c *= CELL_SIDE;
        c++;
        int bkColor = highLight ? Console.LIGHT_GRAY : Console.BLACK;
        Console.setBackground(bkColor);
        Console.cursor(l - 1, c - 1);
        paintCorner(bkColor);
        Console.cursor(l - 1, c + 1);
        paintCorner(bkColor);
        Console.cursor(l + 1, c - 1);
        paintCorner(bkColor);
        Console.cursor(l + 1, c + 1);
        paintCorner(bkColor);
        Console.cursor(l, c);
        paintCenter(bkColor);

        for (Dir d : Dir.values()) {
            Console.cursor(l + d.deltaLin, c + d.deltaCol);
            paintSide(d, bkColor);
        }
    }

    /**
     * Paint a corner char</br>
     * Can be specialized for each type of cell
     *
     * @param bkColor Background color that can be used
     */
    protected void paintCorner(int bkColor) {
        write(' ', bkColor);
    }

    /**
     * Paint a side char</br>
     * Can be specialized for each type of cell
     *
     * @param dir     Direction of the side
     * @param bkColor Background color that can be used
     */
    protected void paintSide(Dir dir, int bkColor) {
        write(' ', bkColor);
    }

    /**
     * Paint the center char</br>
     * Can be specialized for each type of cell
     *
     * @param bkColor Background color that can be used
     */
    protected void paintCenter(int bkColor) {
        write(' ', bkColor);
    }
}

