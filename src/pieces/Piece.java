package pieces;

import game.Board;
import game.Move;

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

    public boolean checkIfLegalMove(Move move, Board board) {
        return generateAllLegalMoves(board).contains(move);
    }

    public abstract List<Move> generateAllLegalMoves(Board board);


}
