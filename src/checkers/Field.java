package checkers;

import java.util.ArrayList;

public class Field extends Board{
    
    private boolean empty;
    private int player;
    private boolean king;
    private ArrayList<String> possibleMoves;
    
    public void move(Field oldPosition) {
        this.empty = oldPosition.empty;
        this.player = oldPosition.player;
        this.king = oldPosition.king;
        oldPosition.clean();
        this.updatePossibleMoves();
    } 
    
    public void clean() {
        this.empty = true;
        this.player = 0;
        this.king = false;
        this.possibleMoves.clear();
    }
    
    public void updatePossibleMoves() {
        
    }
}
