package checkers;

class Position {

    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        String[] row = {"a", "b", "c", "d", "e", "f", "g", "h"};
        return row[x] + (y+1);
    }
    
    public void print() {
        System.out.println(toString());
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

}