package main.pieces;

import main.game.Board;
import main.game.Move;

import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public abstract class Piece {

    public boolean color;
    public int value;
    public boolean empty = false;
    public int x;
    public int y;

    public Piece() {
        color = false;
        this.value = 0;
    }

    public Piece(boolean color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.value = 0;
    }

    public Move createMove(Board b, int toX, int toY) {
        return new Move(b, this.color, this.x, this.y, toX, toY);
    }

    public boolean getColor() {
        return this.color;
    }

    public boolean hasSameColor(Board b, Move move) {
        if(b.getPiece(move.toX, move.toY).empty) {
            return false;
        }
        if(b.getPiece(move.toX, move.toY).color == this.color) {
            return true;
        }
        return false;
    }

    public Piece clone() {
        if(this.getClass() == Empty.class) {
            return new Empty((Empty) this);
        }
        if(this.getClass() == Pawn.class) {
            return new Pawn((Pawn) this);
        }
        if(this.getClass() == Knight.class) {
            return new Knight((Knight) this);
        }
        if(this.getClass() == Bishop.class) {
            return new Bishop((Bishop) this);
        }
        if(this.getClass() == Rook.class) {
            return new Rook((Rook) this);
        }
        if(this.getClass() == Queen.class) {
            return new Queen((Queen) this);
        }
        if(this.getClass() == King.class) {
            return new King((King) this);
        }
        return new Empty();
    }

    public boolean checkIfLegalMove(Board b, Move m) {
        return generateAllLegalMoves(b).contains(m);
    }

    /**
     * Generates all legal moves for a given piece. It doesn't check if it creates a check.
     * @param board
     * @return all legal moves according to chess piece movement rules.
     */
    public abstract List<Move> generateAllLegalMoves(Board board);


}
