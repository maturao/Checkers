package checkers;

public class Position {

    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        String[] row = {"a", "b", "c", "d", "e", "f", "g", "h"};
        return (row[x] + (y + 1)).toUpperCase();
    }

    public boolean equal(Position pos) {
        return x == pos.x && y == pos.y;
    }

    public static Position convertString(String pos) {
        char[] row = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        pos = pos.toLowerCase();
        int x = 0;
        int y = 0;
        if (pos.matches("[abcdefgh][12345678]")) {
            x = 0;
            for (int i = 0; i < row.length; i++) {
                if (pos.charAt(0) == row[i]) {
                    x = i;
                    break;
                }
            }
            y = Integer.parseInt(pos.substring(1)) - 1;
        }
        return new Position(x, y);
    }

    public Position copy() {
        return new Position(x, y);
    }

    public Position between(Position pos) {
        int newX = x;
        int newY = y;
        if (pos.x < x) {
            newX--;
        } else if (pos.x > x) {
            newX++;
        }
        if (pos.y < y) {
            newY--;
        } else if (pos.y > y) {
            newY++;
        }
        return new Position(newX, newY);
    }

    public Position offset(boolean upward, boolean left, int offset) {
        int newX = this.x + (left ? -offset : offset);
        int newY = this.y + (upward ? -offset : offset);
        return new Position(newX, newY);
    }

    public boolean isInBound() {
        return (x >= 0 && x < Board.WIDTHHEIGHT) && (y >= 0 && y < Board.WIDTHHEIGHT);
    }

    public void print() {
        System.out.println(toString());
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
