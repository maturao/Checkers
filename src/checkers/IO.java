package checkers;

import java.util.Scanner;
import java.util.ArrayList;

public class IO {

    private static final Scanner SCAN = new Scanner(System.in);

    public static Position getPosition() {
        String pos = SCAN.next().toLowerCase();
        while (!pos.matches("[abcdefgh][12345678]") && !pos.matches("[12345678][abcdefgh]")) {
            System.out.println("Invalid input");
            pos = SCAN.next().toLowerCase();
        }
        if (pos.matches("[12345678][abcdefgh]")) {
            String reverse = "";
            reverse = reverse.concat(pos.substring(1));
            reverse = reverse.concat(pos.substring(0, 1));
            pos = reverse;
        }
        return Position.convertString(pos);
    }

    public static Position getPosition(ArrayList<Position> list) {
        Position inputPos;
        while (true) {
            inputPos = IO.getPosition();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equal(inputPos)) {
                    return inputPos;
                }
            }
            System.out.println("Invalid selection");
        }
    }

    public static String getName() {
        String name;
        while (true) {
            System.out.println("Choose name: ");
            name = SCAN.next();
            if (name.matches("[a-zA-Z]{1,10}")) {
                return name;
            }
            System.out.println("Invalid input");
        }
    }

    public static void printPositionArray(ArrayList<Position> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i));
            if (i < array.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void printFieldArray(ArrayList<Field> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i).position);
            if (i < array.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void printPlayerPieces(Player p) {
        ArrayList<Position> playablePieces = new ArrayList<>();
        if (p.hasToJump()) {
            playablePieces = p.getBoard().getJumpPieces(p.getNumber());
        } else if (p.canMove()) {
            playablePieces = p.getBoard().getMovablePieces(p.getNumber());
        }
        printPositionArray(playablePieces);
    }

}
