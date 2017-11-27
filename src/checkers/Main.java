package checkers;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Player playing;

    private static Player player1;
    private static Player player2;

    private static boolean gameOver = false;

    private static void gameOver() {
        gameOver = true;
        System.out.println("   _____                         ____                 \n" +
                           "  / ____|                       / __ \\                \n" +
                           " | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ \n" +
                           " | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|\n" +
                           " | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   \n" +
                           "  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   \n");
        System.out.println();
        System.out.println();
                System.out.println("\n\n__          ___                       \n" +
                                     " \\ \\        / (_)                      \n" +
                                     "  \\ \\  /\\  / / _ _ __  _ __   ___ _ __ \n" +
                                     "   \\ \\/  \\/ / | | '_ \\| '_ \\ / _ \\ '__|\n" +
                                       "    \\  /\\  /  | | | | | | | |  __/ |   \n" +
                                       "     \\/  \\/   |_|_| |_|_| |_|\\___|_|   \n" +
                                       "                                       \n");
                System.out.println();
                System.out.println();
                System.out.println(playing.getName());
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
        
       Scanner sc = new Scanner(System.in);

        for (int x = 1; x <= 1; x++) {
            System.out.println(x + "============================\n" + x
                                 + "|           MENU          |\n"
                                 + "============================\n"
                                 + "| Options:                 |\n"
                                 + "|        1. start          |\n"
                                 + "|        2. rules          |\n"
                                 + "|        0. exit           |\n" 
                                 + "|                          |\n"
                                 + "============================");
        }

        boolean exit = false;

        int menu;

        System.out.print("Choose menu item: ");
        menu = sc.nextInt();
        
        do {
            switch (menu) {
            case 1:
                System.out.println("Option 1 selected");
                break;
            case 2:
                System.out.println("Option 2 selected");
                break;
            case 0:
                System.out.println("Exit selected");
                break;
            default:
                System.out.println("Invalid choice.");
                System.out.println(sc.nextInt(menu));
            }   
        } while(!exit);
        System.out.println(gameOver);
        
        
        player2 = new Player(IO.getName(), board, 2);
        player1 = new Player(IO.getName(), board, 1);
        playing = player1;
        board.setPlayers(player1, player2);
        
        Position moving, moveTo;
        board.print();
        while (!gameOver) {
            
            switchPlayer();
            
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
            
            if (player1.pieces() == 0 || player2.pieces() == 0) {
                gameOver();
            }
        }

    }

}
