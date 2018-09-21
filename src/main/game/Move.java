package main.game;

import main.pieces.Piece;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Move {

    private Board board;
    private boolean color;
    public Piece piece;
    public int fromX;
    public int fromY;
    public int toX;
    public int toY;

    public boolean enPassant = false;
    public boolean castling = false;


    public Move(Board board, boolean color, int fromX, int fromY, int toX, int toY) {
        this.board = board;
        this.color = color;
        this.fromX = fromX;
        this.fromY =fromY;
        this.toX = toX;
        this.toY = toY;

        piece = board.getPiece(fromX, fromY);
    }

    /**
     * Checks if the move is legal by looking if it is a legal move of a piece and it doesn't create a check.
     * @return
     */
    public boolean isLegal() {
        if(piece.checkIfLegalMove(board, this) && !this.createsAttackOnKing()) {
            return true;
        }

        return false;
    }

    /**
     * Checks if a check is created when this move is executed
     * @return
     */
    public boolean createsAttackOnKing() {
        Board oldBoard = board.clone();

        Board newBoard = board.clone();
        this.board = newBoard;
        newBoard.executeMove(this);
        boolean exists = newBoard.existAttackOnKing(this.piece.color);

        this.board = oldBoard;

        return exists;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() == Move.class) {
            Move other = (Move) o;
            if (other.fromX == this.fromX && other.fromY == this.fromY && other.toX == this.toX && other.toY == this.toY) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks that this move is inside the board.
     * @return
     */
    public boolean isInsideBoard() {
        if(this.toX < 8 && this.toY < 8 && this.toX >= 0 && this.toY >= 0) {
            return true;
        }
        return false;
    }

}
