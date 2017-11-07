package checkers;

public class Board {

    final Field[][] fields;

    public void print() {
        System.out.println("|-----------------------------------------|");
        System.out.println("|                                         |");
        System.out.println("|       a   b   c   d   e   f   g   h     |");
        System.out.println("|     ---------------------------------   |");
        
        for (int y = 0; y < fields.length; y++) {
            System.out.print("|   ");
            System.out.print((y + 1) + " |");
  
            for (int x = 0; x < fields[y].length; x++) {
                switch (fields[y][x].getPlayer()) {
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" x ");
                        break;
                    case 2:
                        System.out.print(" o ");
                        break;
                }
                
                System.out.print("|");
                
            }
            System.out.print("\n|     |---|---|---|---|---|---|---|---|   |");
            System.out.println();
        }
    }

    public void moveField(int x1, int y1, int x2, int y2) {
        getField(x1, y1).moveTo(getField(x2, y2));
    }

    public Field getField(int x, int y) {
        return fields[y][x];
    }

    public Board() {
        int[][] placement = {
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 2, 0, 2, 0, 2},
            {2, 0, 2, 0, 2, 0, 2, 0},};

        fields = new Field[8][8];
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                fields[y][x] = new Field(x, y, placement[y][x], this);
            }
        }
        
        for (int y = 0; y < fields.length; y++) {
            for (int x = 0; x < fields[y].length; x++) {
                fields[y][x].updatePossibleMoves();
            }
        }

    }
}
