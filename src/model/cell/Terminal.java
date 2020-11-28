package model.cell;

import model.Dir;

public class Terminal extends Cell {
    @Override
    public boolean fromString(String word) {
        char type = word.charAt(0);
        if (type != 'T' && (type < 'A' || type > 'F')) {
            return false;
        }
        color = type == 'T' ? word.charAt(1) - '0' : type - 'A';
        return true;
    }

    @Override
    public boolean canLink(Dir dir, int color, boolean from) {
        return from ? nextDir == null && prevDir == null && super.canLink(dir, color, true) : nextDir == null && prevDir == null && color == getColor();
    }

    @Override
    public void unlink() {
        nextDir = null;
        prevDir = null;
    }

    @Override
    public void prevUnlink() {
        nextDir = null;
        prevDir = null;
    }

    @Override
    public void nextUnlink() {
        prevDir = null;
    }
}
