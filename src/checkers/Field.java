package checkers;

import java.util.ArrayList;

public class Field {

    private int player;
    private boolean king;

    private final int x;
    private final int y;
    private ArrayList<String> possibleMoves;

    public void moveTo(Field newPosition) {
        if ( !this.isEmpty() && newPosition.isEmpty()) {
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

    public Field(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.possibleMoves = new ArrayList<>();
    }

}
