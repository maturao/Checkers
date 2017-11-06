package checkers;

public class Main {
    
    public static void main(String[] args) {
        
        Board board = new Board();
        
        board.moveField(3, 2, 4, 3);
        board.print();
        
        board.getField(2, 5).printPossibleMoves();
        
    }
    
}
