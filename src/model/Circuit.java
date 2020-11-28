package model;

import model.cell.Cell;


public class Circuit {
    private final int height, width;
    private Cell[][] grid;
    private boolean linking;

    Circuit(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
    }

    /**
     * Also change colors and dirs of the cells
     *
     * @param from - Position of the origin of movement
     * @param to   - Position of the destination of movement
     * @return True it's possible to drag
     */
    public boolean drag(Position from, Position to) {
        Dir dir = from.getDirTo(to);
        Cell cf = getCell(from.getLine(), from.getCol()), ct = getCell(to.getLine(), to.getCol());

        if (cf.canLink(dir, ct.getColor(), true) && ct.canLink(dir, cf.getColor(), false)) {
            linking = true;
            cf.nextDir = dir;
            if (ct.prevDir != null)
                ct.nextDir = ct.prevDir;
            ct.prevDir = Dir.not(dir);
            ct.changeColor(cf.getColor());
            return true;
        }
        if (cf.hasColor() && cf.nextDir == null && cf.prevDir != null && !cf.isTerminal()) {
            unlink(from);
            return true;
        }
        return false;
    }

    public boolean unlink(Position pos) {
        if (linking) {
            linking = !linking;
            return false;
        }
        Cell c = getCell(pos);
        if (c.canUnlink()) {
            if (c.nextDir != null) {
                Cell next = getCell(pos.getLine() + c.nextDir.deltaLin, pos.getCol() + c.nextDir.deltaCol);
                next.nextUnlink();
            }
            if (c.prevDir != null) {
                Cell prev = getCell(pos.getLine() + c.prevDir.deltaLin, pos.getCol() + c.prevDir.deltaCol);
                prev.prevUnlink();
            }
            c.unlink();
            return true;
        }
        return false;
    }

    /**
     * @return True if the level is completed
     */
    public boolean isOver() {
        return allCellsColored() && allTerminalsLinked();
    }

    /**
     * @return True if all Terminals are linked
     */
    private boolean allTerminalsLinked() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].isTerminal() && grid[i][j].nextDir == null && grid[i][j].prevDir == null)
                    return false;
            }
        }
        return true;
    }

    /**
     * @return True if all cells have a color
     */

    private boolean allCellsColored() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                count = grid[i][j].hasColor() ? ++count : count;
            }
        }
        return count == height * width;
    }

    /**
     * Put the cell in the array if cells in the index lin and col
     *
     * @param lin  - index of the two-dimensional array
     * @param col  - index of the two-dimensional array
     * @param cell - Cell we want to put in the array
     */
    void putCell(int lin, int col, Cell cell) {
        grid[lin][col] = cell;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /**
     * @param i - index of the two-dimensional array
     * @param j - index of the two-dimensional array
     * @return the cell in the array of cells with the coordinates i and j
     */
    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    public Cell getCell(Position pos) {
        return grid[pos.getLine()][pos.getCol()];
    }


}
