package checkers;

import java.util.ArrayList;

public class Field {

    private int player;
    private boolean king;

    public final Position position;
    private final Board board;
    private Moves moves;
    public ArrayList<Jump> jumps;

    public void moveTo(Field newPosition) {
        if (!this.isEmpty() && newPosition.isEmpty()) {
            newPosition.player = this.player;
            newPosition.king = this.king;
            this.clean();
            newPosition.checkMoves();
        }
    }

    public void moveTo(Position pos) {
        moveTo(board.getField(pos));
    }

    public void clean() {
        this.player = 0;
        this.king = false;
        this.moves.clear();
        //this.jumps.clear();
    }

    public void checkMoves() {
        if (isNotEmpty()) {
            moves.Update();
        }
    }

    public void checkJumps() {
        jumps.clear();
        Position left1, left2, right1, right2;
        if (movesUp() || isKing()) {
            left1 = position.offset(true, false, 1);
            left2 = position.offset(true, false, 2);
            addJmp(left2, left1);

            right1 = position.offset(true, true, 1);
            right2 = position.offset(true, true, 2);
            addJmp(right2, right1);

        }
        if (movesDown() || isKing()) {
            left1 = position.offset(false, false, 1);
            left2 = position.offset(false, false, 2);
            addJmp(left2, left1);

            right1 = position.offset(false, true, 1);
            right2 = position.offset(false, true, 2);
            addJmp(right2, right1);
        }
    }

    private void addJmp(Position jump, Position jumpOver) {
        if (jump.isInBound() && getField(jump).isEmpty()) {
            if (isOtherPlayer(jumpOver)) {
                jumps.add(new Jump(jump));
                jumps.get(jumps.size() - 1).nextJump();
            }
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

    public boolean isOtherPlayer(Position pos) {
        return board.getField(pos).getPlayer() != this.getPlayer() && this.isNotEmpty() && board.getField(pos).isNotEmpty();
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public int getPlayer() {
        return player;
    }

    public ArrayList<Position> getMoves() {
        return moves.list;
    }

    public Field getField(Position pos) {
        return board.getField(pos);
    }

    public Field offsetField(boolean upward, boolean left, int offset) {
        if (position.offset(upward, left, offset).isInBound()) {
            return board.getField(position.offset(upward, left, offset));
        } else {
            return new Field();
        }
    }

    public void toKing() {
        king = true;
        checkMoves();
    }

    public Field(int x, int y, int player, Board board) {
        this.position = new Position(x, y);
        this.player = player;
        this.board = board;
        this.moves = new Moves();
        this.jumps = new ArrayList<>();
    }

    public Field() {
        this.position = new Position(0, 0);
        this.player = 0;
        this.board = new Board();
    }

    class Moves {

        private ArrayList<Position> list;

        public Moves() {
            this.list = new ArrayList<>();
        }

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

    }

    class Jump {

        public Position pos;
        public Jump parent;
        public ArrayList<Jump> children;

        public void nextJump() {
            Position left1, left2, right1, right2;
            if (movesUp() || isKing()) {
                left1 = pos.offset(true, true, 1);
                left2 = pos.offset(true, true, 2);
                addJump(left2, left1);

                right1 = pos.offset(true, false, 1);
                right2 = pos.offset(true, false, 2);
                addJump(right2, right1);

            }
            if (movesDown() || isKing()) {
                left1 = pos.offset(false, true, 1);
                left2 = pos.offset(false, true, 2);
                addJump(left2, left1);

                right1 = pos.offset(false, false, 1);
                right2 = pos.offset(false, false, 2);
                addJump(right2, right1);
            }
        }

        private void addJump(Position jump, Position jumpOver) {
            if (jump.isInBound() && (getField(jump).isEmpty() || getField(jump).position.equal(position))) {
                if (isOtherPlayer(jumpOver)) {

                    boolean add = true;
                    ArrayList<Position> previousJumps = jumpedOver();
                    for (int i = 0; i < previousJumps.size(); i++) {
                        if (previousJumps.get(i).equal(jumpOver)) {
                            add = false;
                            break;
                        }
                    }
                    
                    if (add) {
                        children.add(new Jump(jump, this));
                        children.get(children.size() - 1).nextJump();
                    }
                }
            }
        }

        public boolean hasChildren() {
            return children.size() > 0;
        }

        public boolean hasParent() {
            return !this.equals(parent);
        }

        public Position jumpsOver() {
            if (hasParent()) {
                return parent.pos.between(pos);
            } else {
                return position.between(pos);
            }
        }

        public ArrayList<Position> jumpedOver() {
            ArrayList<Position> list = new ArrayList<>();
            previousJumpOver(list);
            return list;
        }

        private void previousJumpOver(ArrayList<Position> jumpedOver) {
            jumpedOver.add(jumpsOver());
            if (hasParent()) {
                parent.previousJumpOver(jumpedOver);
            }
        }

        public Jump getChild(int i) {
            if (hasChildren()) {
                return children.get(i);
            } else {
                return this;
            }
        }

        public Jump(Position pos) {
            this.pos = pos;
            this.parent = this;
            children = new ArrayList<>();
        }

        public Jump(Position pos, Jump parent) {
            this.pos = pos;
            this.parent = parent;
            children = new ArrayList<>();
        }

    }

}
