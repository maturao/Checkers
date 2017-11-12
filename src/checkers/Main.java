package checkers;

import java.util.ArrayList;

public class Main {

    public static Player playing;

    private static Player player1;
    private static Player player2;

    private static boolean gameOver = false;

    private static void gameOver() {
        gameOver = true;
    }

    public static void switchPlayer() {
        if (playing.equals(player1)) {
            playing = player2;
        } else if (playing.equals(player2)) {
            playing = player1;
        }
    }

    public static void main(String[] args) {

        Board board = new Board();

        System.out.println(
                "   ____ _               _                 \n"
                + "  / ___| |__   ___  ___| | _____ _ __ ___ \n"
                + " | |   | '_ \\ / _ \\/ __| |/ / _ \\ '__/ __|\n"
                + " | |___| | | |  __/ (__|   <  __/ |  \\__ \\\n"
                + "  \\____|_| |_|\\___|\\___|_|\\_\\___|_|  |___/\n"
                + "                                          "
        );

        player2 = new Player(IO.getName(), board, 2);
        player1 = new Player(IO.getName(), board, 1);
        playing = player2;

        Position moving;
        Position moveTo;
        board.print();
        while (!gameOver) {
            System.out.println("Player move: " + playing.getName());
            if (playing.hasToJump()) {

                ArrayList<Position> pieces = board.getJumpPieces(playing.getNumber());
                System.out.println("Choose your piece:");
                IO.printPositionArray(pieces);
                moving = IO.getPosition(board.getJumpPieces(playing.getNumber()));
                board.print();

                ArrayList<Position> moves;
                while (board.getField(moving).hasToJump()) {
                    moves = board.getField(moving).getJumps();
                    System.out.println("Chosen piece: " + moving);
                    System.out.println("Choose where to jump");
                    IO.printPositionArray(moves);
                    moveTo = IO.getPosition(moves);
                    board.getField(moving).jumpTo(moveTo);
                    moving = moveTo.copy();
                    board.print();
                }

            } else if (playing.canMove()) {

                ArrayList<Position> pieces = board.getMovablePieces(playing.getNumber());
                System.out.println("Choose your piece:");
                IO.printPositionArray(pieces);
                moving = IO.getPosition(pieces);
                board.print();

                ArrayList<Position> moves = board.getField(moving).getMoves();
                System.out.println("Chosen piece: " + moving);
                System.out.println("Choose where to move:");
                IO.printPositionArray(moves);
                moveTo = IO.getPosition(moves);
                board.getField(moving).moveTo(moveTo);
                board.print();
            }
            switchPlayer();
            if (player1.pieces() == 0) {
                System.out.println("\n\nWinner: " + player2.getName());
                System.out.println();
                gameOver();
            }
            if (player2.pieces() == 0) {
                System.out.println("\n\nWinner: " + player1.getName());
                System.out.println();
                gameOver();
            }
        }

    }

}
