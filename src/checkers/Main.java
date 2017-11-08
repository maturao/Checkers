package checkers;

public class Main {
    
    public static void main(String[] args) {
        
        Board board = new Board();
        
        board.moveField(3, 2, 4, 3);
        board.print();
        board.getField("d6").toKing();
        board.getField("d6").addJump(5, 3);
        board.getField("d6").jumps.get(0).jumps.get(1).position.print();
        //board.getField("d6").printMoves();
        
    }
    
}
