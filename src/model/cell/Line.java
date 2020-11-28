package model.cell;

import model.Dir;

public class Line extends Cell {
    private boolean orient; //True = | ; False = -

    public boolean getOrient() {
        return orient;
    }

    @Override
    public boolean fromString(String word) {
        char type = word.charAt(0);
        if (type != '|' && type != '-') {
            return false;
        }
        orient = (type == '|');
        color = -1;
        return true;
    }

    @Override
    public boolean canLink(Dir d, int color, boolean from) {
        if (super.canLink(d, color, from) || from && (hasColor() || color == getColor() && color != -1)) {
            return orient ? (d == Dir.DOWN || d == Dir.UP) : (d == Dir.LEFT || d == Dir.RIGHT);
        }
        return false;
    }

    @Override
    public void unlink() {
        super.unlink();
    }

    @Override
    public void prevUnlink() {
        super.prevUnlink();
    }

    @Override
    public void nextUnlink() {
        super.nextUnlink();
    }
}
