package checkers;

import java.util.ArrayList;

public class Player {

    private final int number;
    private final String name;
    private final Board board;
    private final ArrayList<Boolean> piecesTaken;
    
    public void takePiece() {
        piecesTaken.add(false);
    }
    
    public void takeKing() {
        piecesTaken.add(true);        
    }
    
    public int getNumber() {
        return number;
    }

    public int pieces() {
        return board.getPlayerPieces(number).size();
    }
    
    public int piecesTaken() {
        return piecesTaken.size();
    }

    public boolean hasToJump() {
        return board.hasToJump(number);
    }

    public boolean canMove() {
        return board.canMove(number);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }
    
    public ArrayList<Boolean> getTakenPieces() {
        return piecesTaken;
    }
    
    public boolean isTakenPieceKing(int index) {
        return piecesTaken.get(index);
    }

    public Player(String name, Board board, int n) {
        this.name = name;
        this.board = board;
        number = n;
        piecesTaken = new ArrayList<>();
    }
}
