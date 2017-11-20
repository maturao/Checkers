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

    public static void fancyPrint(String text) {

        int[] textInt = new int[text.length()];
        for (int i = 0; i < textInt.length; i++) {
            textInt[i] = findChar(text.substring(i, i + 1));
        }

        for (int i = 0; i < FANCYTEXT[0].length; i++) {
            for (int j = 0; j < textInt.length; j++) {
                System.out.print(FANCYTEXT[textInt[j]][i]);
            }
            System.out.println();
        }

    }

    private static int findChar(String character) {
        for (int i = 0; i < ALPHABET.length(); i++) {
            if (character.equalsIgnoreCase(ALPHABET.substring(i, i+1))) {
                return i;
            }
        }
        return -1;
    }

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";

    private static final String[][] FANCYTEXT
            = {
                {
                    "        ",
                    "        ",
                    "   __ _ ",
                    "  / _` |",
                    " | (_| |",
                    "  \\__,_|",
                    "        ",
                    "        "
                },
                {
                    "  _     ",
                    " | |    ",
                    " | |__  ",
                    " | '_ \\ ",
                    " | |_) |",
                    " |_.__/ ",
                    "        ",
                    "        "
                },
                {
                    "       ",
                    "       ",
                    "   ___ ",
                    "  / __|",
                    " | (__ ",
                    "  \\___|",
                    "       ",
                    "       "
                },
                {
                    "      _ ",
                    "     | |",
                    "   __| |",
                    "  / _` |",
                    " | (_| |",
                    "  \\__,_|",
                    "        ",
                    "        "
                },
                {
                    "       ",
                    "       ",
                    "   ___ ",
                    "  / _ \\",
                    " |  __/",
                    "  \\___|",
                    "       ",
                    "       "
                },
                {
                    "   __ ",
                    "  / _|",
                    " | |_ ",
                    " |  _|",
                    " | |  ",
                    " |_|  ",
                    "      ",
                    "      "
                },
                {
                    "        ",
                    "        ",
                    "   __ _ ",
                    "  / _` |",
                    " | (_| |",
                    "  \\__, |",
                    "   __/ |",
                    "  |___/ "
                },
                {
                    "  _     ",
                    " | |    ",
                    " | |__  ",
                    " | '_ \\ ",
                    " | | | |",
                    " |_| |_|",
                    "        ",
                    "        "
                },
                {
                    "  _ ",
                    " (_)",
                    "  _ ",
                    " | |",
                    " | |",
                    " |_|",
                    "    ",
                    "    "
                },
                {
                    "    _ ",
                    "   (_)",
                    "    _ ",
                    "   | |",
                    "   | |",
                    "   | |",
                    "  _/ |",
                    " |__/ "
                },
                {
                    "  _    ",
                    " | |   ",
                    " | | __",
                    " | |/ /",
                    " |   < ",
                    " |_|\\_\\",
                    "       ",
                    "       "
                },
                {
                    "  _ ",
                    " | |",
                    " | |",
                    " | |",
                    " | |",
                    " |_|",
                    "    ",
                    "    "
                },
                {
                    "            ",
                    "            ",
                    "  _ __ ___  ",
                    " | '_ ` _ \\ ",
                    " | | | | | |",
                    " |_| |_| |_|",
                    "            ",
                    "            "
                },
                {
                    "        ",
                    "        ",
                    "  _ __  ",
                    " | '_ \\ ",
                    " | | | |",
                    " |_| |_|",
                    "        ",
                    "        "
                },
                {
                    "        ",
                    "        ",
                    "   ___  ",
                    "  / _ \\ ",
                    " | (_) |",
                    "  \\___/ ",
                    "        ",
                    "        "
                },
                {
                    "        ",
                    "        ",
                    "  _ __  ",
                    " | '_ \\ ",
                    " | |_) |",
                    " | .__/ ",
                    " | |    ",
                    " |_|    "
                },
                {
                    "        ",
                    "        ",
                    "   __ _ ",
                    "  / _` |",
                    " | (_| |",
                    "  \\__, |",
                    "     | |",
                    "     |_|"
                },
                {
                    "       ",
                    "       ",
                    "  _ __ ",
                    " | '__|",
                    " | |   ",
                    " |_|   ",
                    "       ",
                    "       "
                },
                {
                    "      ",
                    "      ",
                    "  ___ ",
                    " / __|",
                    " \\__ \\",
                    " |___/",
                    "      ",
                    "      "
                },
                {
                    "  _   ",
                    " | |  ",
                    " | |_ ",
                    " | __|",
                    " | |_ ",
                    "  \\__|",
                    "      ",
                    "      "
                },
                {
                    "        ",
                    "        ",
                    "  _   _ ",
                    " | | | |",
                    " | |_| |",
                    "  \\__,_|",
                    "        ",
                    "        "
                },
                {
                    "        ",
                    "        ",
                    " __   __",
                    " \\ \\ / /",
                    "  \\ V / ",
                    "   \\_/  ",
                    "        ",
                    "        "
                },
                {
                    "           ",
                    "           ",
                    " __      __",
                    " \\ \\ /\\ / /",
                    "  \\ V  V / ",
                    "   \\_/\\_/  ",
                    "           ",
                    "           "
                },
                {
                    "       ",
                    "       ",
                    " __  __",
                    " \\ \\/ /",
                    "  >  < ",
                    " /_/\\_\\",
                    "       ",
                    "       "
                },
                {
                    "        ",
                    "        ",
                    "  _   _ ",
                    " | | | |",
                    " | |_| |",
                    "  \\__, |",
                    "   __/ |",
                    "  |___/ "
                },
                {
                    "      ",
                    "      ",
                    "  ____",
                    " |_  /",
                    "  / / ",
                    " /___|",
                    "      ",
                    "      "
                },
                {
                    "     ",
                    "     ",
                    "     ",
                    "     ",
                    "     ",
                    "     ",
                    "     ",
                    "      "
                }
            };

}
