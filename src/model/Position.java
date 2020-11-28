package model;

public class Position {
    private int lin;
    private int col;

    public Position(int line, int col) {
        lin = line;
        this.col = col;
    }

    public int getLine() {
        return lin;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Position p) {
        return lin == p.lin && col == p.col;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Position && equals((Position) obj);
    }

    Dir getDirTo(Position to) {
        if (getLine() == to.getLine())
            return getCol() < to.getCol() ? Dir.RIGHT : Dir.LEFT;
        if (getCol() == to.getCol())
            return getLine() < to.getLine() ? Dir.UP : Dir.DOWN;
        return null;
    }
}
