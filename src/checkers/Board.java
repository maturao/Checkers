package checkers;

import java.util.ArrayList;

public class Board {

    public final static int WIDTHHEIGHT = 8;
    public final static int PLAYERPIECES = 12;
    final Field[][] fields;
    private Player player1;
    private Player player2;
    
    private int player1TakenIndex;
    private int player2TakenIndex;

    public void print() {
        System.out.println("\n     a   b   c   d   e   f   g   h     ".toUpperCase());
        System.out.print("   +---+---+---+---+---+---+---+---+    ");
        
        player1TakenIndex = 0;
        player2TakenIndex = 0;
        
        printTakenPieces();
        System.out.println();
        for (int y = 0; y < fields.length; y++) {
            System.out.print(" ");
            System.out.print((y + 1) + " |");

            for (int x = 0; x < fields[y].length; x++) {
                switch (fields[y][x].getPlayer()) {
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        if (fields[y][x].isKing()) {
                            System.out.print(" X ");
                        } else {
                            System.out.print(" x ");
                        }
                        break;
                    case 2:
                        if (fields[y][x].isKing()) {
                            System.out.print(" O ");
                        } else {
                            System.out.print(" o ");
                        }
                        break;
                }
                System.out.print("|");
            }
            System.out.print(" " + (y+1) + "  ");
            
            printTakenPieces();
            
            System.out.print(" \n   +---+---+---+---+---+---+---+---+    ");
            
            printTakenPieces();
            
            System.out.println();
        }
        System.out.println("     a   b   c   d   e   f   g   h     ".toUpperCase());
    }
    
    public void printTakenPieces() {
        if (player1TakenIndex < player1.piecesTaken()) {
            if (player1.isTakenPieceKing(player1TakenIndex)) {
                System.out.print("   X");
            } else {
                System.out.print("   x");
            }
            player1TakenIndex++;
            if (player2TakenIndex < player2.piecesTaken()) {
                if (player2.isTakenPieceKing(player2TakenIndex)) {
                    System.out.print("   O");
                } else {
                    System.out.print("   o");
                }
                player2TakenIndex++;
            }
        } else if (player2TakenIndex < player2.piecesTaken()) {
            if (player2.isTakenPieceKing(player2TakenIndex)) {
                System.out.print("       O");
            } else {
                System.out.print("       o");
            }
            player2TakenIndex++;
        }
    }

    public void moveField(Position pos1, Position pos2) {
        getField(pos1).moveTo(getField(pos2));
    }

    public void updateAll() {
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                if (fields[y][x].isNotEmpty()) {
                    fields[y][x].checkMoves();
                    fields[y][x].checkJumps();
                    fields[y][x].checkKing();
                }
            }
        }
    }

    public Field getField(Position pos) {
        return fields[pos.getY()][pos.getX()];
    }

    public Field getField(String pos) {
        return getField(Position.convertString(pos));
    }

    public ArrayList<Field> getPlayerPieces(int p) {
        ArrayList<Field> playerFields = new ArrayList<>();

        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                if (fields[y][x].getPlayer() == p) {
                    playerFields.add(fields[y][x]);
                }
            }
        }
        return playerFields;
    }

    public ArrayList<Position> getMovablePieces(int p) {
        ArrayList<Field> playerPieces = getPlayerPieces(p);
        ArrayList<Position> movablePieces = new ArrayList<>();
        for (int i = 0; i < playerPieces.size(); i++) {
            if (playerPieces.get(i).canMove()) {
                movablePieces.add(playerPieces.get(i).getPosition().copy());
            }
        }
        return movablePieces;
    }

    public ArrayList<Position> getJumpPieces(int p) {
        ArrayList<Field> playerPieces = getPlayerPieces(p);
        ArrayList<Position> jumpPieces = new ArrayList<>();
        for (int i = 0; i < playerPieces.size(); i++) {
            if (playerPieces.get(i).hasToJump()) {
                jumpPieces.add(playerPieces.get(i).getPosition().copy());
            }
        }
        return jumpPieces;
    }

    public ArrayList<Field> getPlayablePieces(int p) {
        ArrayList<Field> playerPieces = getPlayerPieces(p);
        ArrayList<Field> playablePieces = new ArrayList<>();

        if (hasToJump(p)) {
            for (int i = 0; i < playerPieces.size(); i++) {
                if (playerPieces.get(i).hasToJump()) {
                    playablePieces.add(playerPieces.get(i));
                }
            }
        } else {
            for (int i = 0; i < playerPieces.size(); i++) {
                if (playerPieces.get(i).canMove()) {
                    playablePieces.add(playerPieces.get(i));
                }
            }
        }
        return playablePieces;
    }

    public boolean hasToJump(int p) {
        ArrayList<Field> playerPieces = getPlayerPieces(p);
        for (int i = 0; i < playerPieces.size(); i++) {
            if (playerPieces.get(i).hasToJump()) {
                return true;
            }
        }
        return false;
    }

    public boolean canMove(int p) {
        ArrayList<Field> playerPieces = getPlayerPieces(p);
        for (int i = 0; i < playerPieces.size(); i++) {
            if (playerPieces.get(i).canMove()) {
                return !hasToJump(p);
            }
        }
        return false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    
    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Board() {
        int[][] placement = {
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},};
            
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 2, 0, 2, 0, 2},
            {2, 0, 2, 0, 2, 0, 2, 0},};
        
        fields = new Field[WIDTHHEIGHT][WIDTHHEIGHT];
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                fields[y][x] = new Field(x, y, placement[y][x], this);
            }
        }

        updateAll();

    }
}
