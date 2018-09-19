package game;

import pieces.King;
import pieces.Piece;

import java.util.List;

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

    public boolean isLegal() {

        if(!this.piece.checkIfLegalMove(this, board)) {
            return false;
        }

        return true;
    }

    public boolean createsAttackOnKing() {
        Board newBoard = board.clone();
        newBoard.executeMove(this);

        int kingX = 0;
        int kingY = 0;

        for (int i = 0; i < newBoard.getBoard().length; i++) {
            for (int j = 0; j < newBoard.getBoard()[i].length; j++) {
                if(newBoard.getPiece(i,j).getColor() == this.piece.getColor() && newBoard.getPiece(i,j).getClass() == King.class) {
                    kingX = i;
                    kingY = j;
                }
            }
        }

        for (int i = 0; i < newBoard.getBoard().length; i++) {
            for (int j = 0; j < newBoard.getBoard()[i].length; j++) {
                Piece p = newBoard.getPiece(i,j);
                if(!p.empty && p.getColor() != this.piece.getColor()) {
                    List<Move> moves = p.generateAllLegalMoves(newBoard);
                    for (Move move1 : moves) {
                        if(move1.toX == kingX && move1.toY == kingY) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean isInsideBoard() {
        if(this.toX < 8 && this.toY < 8 && this.toX >= 0 && this.toY >= 0) {
            return true;
        }
        return false;
    }

}
