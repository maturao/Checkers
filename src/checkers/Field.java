package checkers;

import java.util.ArrayList;

public class Field {

    private int player;
    private boolean king;

    private Moves moves;
    public Jumps jumps;

    public final Position position;
    private final Board board;

    public void moveTo(Field newPosition) {
        if (this.isNotEmpty() && newPosition.isEmpty()) {
            newPosition.player = this.player;
            newPosition.king = this.king;
            this.clean();
            board.updateAll();
        }
    }

    public void jumpTo(Position newPosition) {
        Field takenPiece = board.getField(position.between(newPosition));
        if (takenPiece.player == 1) {
            if (takenPiece.isKing()) {
                board.getPlayer1().takeKing();
            } else {
                board.getPlayer1().takePiece();
            }
        } else {
            if (takenPiece.isKing()) {
                board.getPlayer2().takeKing();
            } else {
                board.getPlayer2().takePiece();
            }
        }
        takenPiece.clean();
        moveTo(newPosition);
    }

    public void moveTo(Position pos) {

        moveTo(board.getField(pos));
    }

    public void clean() {
        this.player = 0;
        this.king = false;
        this.moves.clear();
        this.jumps.clear();
    }

    public void checkMoves() {
        if (isNotEmpty()) {
            moves.Update();
        }
    }

    public void checkJumps() {
        if (isNotEmpty()) {
            jumps.update();
        }
    }

    public void checkKing() {
        if ((movesDown() && getY() == Board.WIDTHHEIGHT - 1) || (movesUp() && getY() == 0)) {
            toKing();
        }
    }

    public void printMoves() {
        for (int i = 0; i < getMoves().size(); i++) {
            System.out.print(getMoves().get(i).toString() + ", ");
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return player == 0;
    }

    public boolean isNotEmpty() {
        return player != 0;
    }

    public boolean isKing() {
        return king;
    }

    public boolean movesDown() {
        return player == 1;
    }

    public boolean movesUp() {
        return player == 2;
    }

    public boolean canMove() {
        return moves.hasMoves() && !hasToJump();
    }

    public boolean hasToJump() {
        return jumps.hasJumps();
    }

    public boolean isOtherPlayer(Position pos) {
        return board.getField(pos).getPlayer() != this.getPlayer() && this.isNotEmpty() && board.getField(pos).isNotEmpty();
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public Position getPosition() {
        return position;
    }

    public int getPlayer() {
        return player;
    }

    public ArrayList<Position> getMoves() {
        return moves.list;
    }

    public ArrayList<Position> getJumps() {
        return jumps.list;
    }

    public Field getField(Position pos) {
        return board.getField(pos);
    }

    public void toKing() {
        king = true;
        checkMoves();
        checkJumps();
    }

    public Field(int x, int y, int player, Board board) {
        this.position = new Position(x, y);
        this.player = player;
        this.board = board;
        this.moves = new Moves();
        this.jumps = new Jumps();
    }

    class Moves {

        private ArrayList<Position> list;

        public void Update() {
            clear();
            if (!isEmpty()) {
                if (movesUp() || isKing()) {
                    addMove(position.offset(true, true, 1));
                    addMove(position.offset(true, false, 1));
                }
                if (movesDown() || isKing()) {
                    addMove(position.offset(false, true, 1));
                    addMove(position.offset(false, false, 1));
                }
            }
        }

        private void addMove(Position pos) {
            if (pos.isInBound()) {
                if (getField(pos).isEmpty()) {
                    list.add(pos);
                }
            }
        }

        public void clear() {
            list.clear();
        }

        public boolean hasMoves() {
            return !list.isEmpty();
        }

        public Moves() {
            this.list = new ArrayList<>();
        }

    }

    public class Jumps {

        public ArrayList<Position> list;

        public void update() {
            list.clear();
            Position left1, left2, right1, right2;
            if (movesUp() || isKing()) {
                left1 = position.offset(true, true, 1);
                left2 = position.offset(true, true, 2);
                addJump(left2, left1);

                right1 = position.offset(true, false, 1);
                right2 = position.offset(true, false, 2);
                addJump(right2, right1);

            }
            if (movesDown() || isKing()) {
                left1 = position.offset(false, true, 1);
                left2 = position.offset(false, true, 2);
                addJump(left2, left1);

                right1 = position.offset(false, false, 1);
                right2 = position.offset(false, false, 2);
                addJump(right2, right1);
            }
        }

        private void addJump(Position jumpTo, Position jumpOver) {
            if (jumpTo.isInBound() && (getField(jumpTo).isEmpty())) {
                if (isOtherPlayer(jumpOver)) {
                    list.add(jumpTo);
                }
            }
        }

        public void clear() {
            list.clear();
        }

        public boolean hasJumps() {
            return !list.isEmpty();
        }

        public Jumps() {
            list = new ArrayList<>();
        }

    }

}
