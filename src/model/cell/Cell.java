package model.cell;

import model.Dir;

public class Cell {
    protected int color;
    public Dir nextDir;
    public Dir prevDir;

    public int getColor() {
        return color;
    }

    public boolean isTerminal() {
        return this instanceof Terminal;
    }

    public static Cell newInstance(char type) {
        switch (type) {
            case '.':
                return new Free();
            case '-':
                return new Line();
            case '|':
                return new Line();
            default:
                if (type >= 'A' && type <= 'F' || type == 'T') return new Terminal();
        }
        return null;
    }

    public boolean fromString(String word) {
        char type = word.charAt(0);
        return ((type >= 'A' && type <= 'Z') || type == '.' || type == '-' || type == '|');
    }

    /**
     * @return True if the cell has color
     */
    public boolean hasColor() {
        return color != -1;
    }

    /**
     * Changes the color of the cell to the parameter color
     *
     * @param color - Color we want to change
     */
    public void changeColor(int color) {
        this.color = color;
    }

    /**
     * Verifica se uma cell pode ligar(to=true) ou receber ligações(to=false) da direção 'dir' com a cor 'color'.
     *
     * @param dir   Direção para onde vai(to=true)/vem(to=false).
     * @param color Cor da outra cell.
     * @param from  Se true verifica se a cell pode fazer ligações naquela direção. Se false verifica se a cell pode receber ligações daquela direção.
     * @return True se pode ligar/receber ligações, else False.
     **/
    public boolean canLink(Dir dir, int color, boolean from) {
        return from ? (hasColor() && (color == -1 || this.color == color)) : !hasColor();
    }

    public boolean canUnlink() {
        return hasColor();
    }

    public void unlink() {
        prevDir = null;
        nextDir = null;
        color = -1;
    }

    public void nextUnlink() {
        if (nextDir == null) {
            color = -1;
        }
        prevDir = null;
    }

    public void prevUnlink() {
        if (prevDir == null) {
            color = -1;
        }
        nextDir = null;
    }
}
