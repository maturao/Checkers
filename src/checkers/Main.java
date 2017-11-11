package checkers;

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        
        Board board = new Board();
        
        board.print();
        //board.getField("e2").toKing();
        //board.getField("e2").printMoves();
        board.getField("e2").toKing();
        board.getField("e2").checkJumps();
        
        //System.out.println(
        ArrayList<Position> list = board.getField("e2").jumps.get(0).getChild(0).getChild(0).getChild(1).jumpedOver();
        //);
        for (int i = 0; i < list.size(); i++) {
            System.out.print( list.get(i).toString() + ", " );
        }
        System.out.println();
                    
    }
    
}