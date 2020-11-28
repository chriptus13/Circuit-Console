package model.cell;

import model.Dir;

public class Free extends Cell {
    @Override
    public boolean fromString(String word) {
        char type = word.charAt(0);
        if (type != '.') {
            return false;
        }
        color = -1;
        return true;
    }

    @Override
    public boolean canLink(Dir d, int color, boolean from) {
        return from ? super.canLink(d, color, true) && nextDir == null : nextDir == null;
    }

    @Override
    public void unlink() {
        super.unlink();
    }

    /**
     * Dà unlink da cell anterior
     */
    @Override
    public void prevUnlink() {
        super.prevUnlink();
    }

    /**
     * Dá unlink da cell seguinte
     */

    @Override
    public void nextUnlink() {
        super.nextUnlink();
    }
}
