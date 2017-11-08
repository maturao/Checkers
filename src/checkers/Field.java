package checkers;

import java.util.ArrayList;

public class Field {

    private int player;
    private boolean king;

    private final int x;
    private final int y;
    private final Board board;
    private Moves moves;
    public ArrayList<Jump> jumps;

    public void moveTo(Field newPosition) {
        if (!this.isEmpty() && newPosition.isEmpty()) {
            newPosition.player = this.player;
            newPosition.king = this.king;
            this.clean();
            newPosition.updateMoves();
        }
    }

    public void clean() {
        this.player = 0;
        this.king = false;
        this.moves.clear();
        //this.jumps.clear();
    }

    public void updateMoves() {
        moves.Update();
    }

    public void printMoves() {
        for (int i = 0; i < moves.list.size(); i++) {
            System.out.println(moves.list.get(i).toString());
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlayer() {
        return player;
    }

    public boolean isEmpty() {
        return player == 0;
    }

    public boolean isKing() {
        return king;
    }
    
    public void toKing() {
        king = true;
    }

    public Field(int x, int y, int player, Board board) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.board = board;
        this.moves = new Moves();
        this.jumps = new ArrayList<>();
    }

    class Moves {

        private ArrayList<Position> list;

        public Moves() {
            this.list = new ArrayList<>();
        }

        public void Update() {
            if (!isEmpty()) {
                if (getPlayer() == 1 || isKing()) {
                    addMove(getX() - 1, getY() + 1);
                    addMove(getX() + 1, getY() + 1);
                }
                if (getPlayer() == 2 || isKing()) {
                    addMove(getX() - 1, getY() - 1);
                    addMove(getX() + 1, getY() - 1);
                }
            }
        }

        private void addMove(int x, int y) {
            if ((x >= 0 && x < board.fields.length) && (y >= 0 && y < board.fields.length)) {
                if (board.getField(x, y).getPlayer() == 0) {
                    list.add(new Position(x, y));
                }
            }
        }

        public void clear() {
            list.clear();
        }

    }
    
    public void addJump(int x, int y) {
        jumps.add(new Jump(x, y));
        jumps.get(jumps.size()-1).nextJump();
    }

    class Jump {

        public Position position;
        public Jump parent;
        public ArrayList<Jump> jumps;

        public void nextJump() {

            if (getPlayer() == 1 || isKing()) {
                if (isInBound(position.getX() - 2, position.getY() + 2)) {
                    if (board.getField(position.getX() - 2, position.getY() + 2).isEmpty()) {
                        if (!board.getField(position.getX() - 1, position.getY() + 1).isEmpty()) {
                            if (board.getField(position.getX() - 1, position.getY() + 1).getPlayer() != getPlayer()) {
                                this.jumps.add(new Jump(position.getX() - 2, position.getY() + 2, this));
                                this.jumps.get(this.jumps.size()-1).nextJump();
                            }
                        }
                    }
                }
                if (isInBound(position.getX() + 2, position.getY() + 2)) {
                    if (board.getField(position.getX() + 2, position.getY() + 2).isEmpty()) {
                        if (!board.getField(position.getX() + 1, position.getY() + 1).isEmpty()) {
                            if (board.getField(position.getX() + 1, position.getY() + 1).getPlayer() != getPlayer()) {
                                this.jumps.add(new Jump(position.getX() + 2, position.getY() + 2, this));
                                this.jumps.get(this.jumps.size()-1).nextJump();
                            }
                        }
                    }
                }
            }
            if (getPlayer() == 2 || isKing()) {
                if (isInBound(position.getX() - 2, position.getY() - 2)) {
                    if (board.getField(position.getX() - 2, position.getY() - 2).isEmpty()) {
                        if (!board.getField(position.getX() - 1, position.getY() - 1).isEmpty()) {
                            if (board.getField(position.getX() - 1, position.getY() - 1).getPlayer() != getPlayer()) {
                                this.jumps.add(new Jump(position.getX() - 2, position.getY() - 2, this));
                                this.jumps.get(this.jumps.size()-1).nextJump();
                            }
                        }
                    }
                }
                if (isInBound(position.getX() + 2, position.getY() - 2)) {
                    if (board.getField(position.getX() + 2, position.getY() - 2).isEmpty()) {
                        if (!board.getField(position.getX() + 1, position.getY() - 1).isEmpty()) {
                            if (board.getField(position.getX() + 1, position.getY() - 1).getPlayer() != getPlayer()) {
                                this.jumps.add(new Jump(position.getX() + 2, position.getY() - 2, this));
                                this.jumps.get(this.jumps.size()-1).nextJump();
                            }
                        }
                    }
                }
            }

        }

        public Jump(int x, int y) {
            position = new Position(x, y);
            jumps = new ArrayList<>();
        }

        public Jump(Position pos, Jump parent) {
            position = new Position(pos.getX(), pos.getY());
            jumps = new ArrayList<>();
            this.parent = parent;
        }

        public Jump(int x, int y, Jump parent) {
            position = new Position(x, y);
            jumps = new ArrayList<>();
            this.parent = parent;
        }

    }

    public static boolean isInBound(int x, int y) {
        return (x >= 0 && x < 8) && (y >= 0 && y < 8);
    }

}
