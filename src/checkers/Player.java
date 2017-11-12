package checkers;

public class Player {

    private final int number;
    private final String name;
    private final Board board;

    public int getNumber() {
        return number;
    }

    public int pieces() {
        return board.getPlayerPieces(number).size();
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

    public Player(String name, Board board, int n) {
        this.name = name;
        this.board = board;
        number = n;
    }
}
