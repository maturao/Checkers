package checkers;

import java.util.ArrayList;

public class Field {

    private int player;
    private boolean king;

    private final Position position;
    private final Board board;
    private ArrayList<Position> possibleMoves;

    public void moveTo(Field newPosition) {
        if (!this.isEmpty() && newPosition.isEmpty()) {
            newPosition.player = this.player;
            newPosition.king = this.king;
            this.clean();
            newPosition.updatePossibleMoves();
        }
    }

    public void clean() {
        this.player = 0;
        this.king = false;
        this.possibleMoves.clear();
    }

    public void updatePossibleMoves() {
        if (!isEmpty()) {
            int x = position.getX();
            int y = position.getY();
            int BoardWH = this.board.fields.length;
            if (isKing()) {
                addPossibleMove(x - 1, y - 1);
                addPossibleMove(x + 1, y - 1);
                addPossibleMove(x - 1, y + 1);
                addPossibleMove(x + 1, y + 1);
            } else if (player == 1) {
                addPossibleMove(x + 1, y + 1);
                addPossibleMove(x - 1, y + 1);
            } else if (player == 2) {
                addPossibleMove(x + 1, y - 1);
                addPossibleMove(x - 1, y - 1);
            }
        }
    }

    private void addPossibleMove(int x, int y) {
        if ((x >= 0 && x <= board.fields.length) && (y >= 0 && y <= board.fields.length)) {
            possibleMoves.add(new Position(x, y));
        }
    }
    
    public void printPossibleMoves() {
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println("\n" + possibleMoves.get(i).x + " " + possibleMoves.get(i).y);
        }
    }

    public int getPlayer() {
        return player;
    }

    public boolean isEmpty() {
        return player == 0;
    }

    public boolean isKing() {
        return king;
    }

    public Field(int x, int y, int player, Board board) {
        this.position = new Position(x, y);
        this.player = player;
        this.board = board;
        this.possibleMoves = new ArrayList<>();
    }

    class Position {

        private final int x;
        private final int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
