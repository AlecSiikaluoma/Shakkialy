package main.pieces;

import main.data.structures.ArrayList;
import main.game.Board;
import main.game.Move;


/**
 * Created by alecsiikaluoma on 18.9.2018.
 * Represents the piece in abstract.
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

    /**
     * Easier way to create a new move.
     * @param b Current board.
     * @param toX Where to move in x-axis.
     * @param toY Where to move in y-axis.
     * @return Returns the new move.
     */
    public Move createMove(Board b, int toX, int toY) {
        return new Move(b, this.color, this.x, this.y, toX, toY);
    }

    public boolean getColor() {
        return this.color;
    }

    /**
     * Checks if this piece has the same color as the target square of the move. Target square can also be empty and its color is represented
     * by false like black color. This methods has to be used in comparing colors to avoid confusion between black piece and empty square.
     * @param b Current board.
     * @param move Move to be performed to get piece to target square.
     * @return True if colors match and false otherwise.
     */
    public boolean hasSameColor(Board b, Move move) {
        if(b.getPiece(move.toX, move.toY).empty) {
            return false;
        }
        if(b.getPiece(move.toX, move.toY).color == this.color) {
            return true;
        }
        return false;
    }

    /**
     * Clones the piece by creating a copy by calling the constructor with the piece.
     * @return Copy of this piece.
     */
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

    /**
     * Checks if the move is for this piece legal.
     * @param b Current position.
     * @param m The move to be performed.
     * @return True if legal and false otherwise.
     */
    public boolean checkIfLegalMove(Board b, Move m) {
        ArrayList<Move> moves = generateAllLegalMoves(b);
        for(int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            if(move.equals(m)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates all legal moves for a given piece. It doesn't check if it creates a check.
     * @return all legal moves according to chess piece movement rules.
     */
    public abstract ArrayList<Move> generateAllLegalMoves(Board board);


}
