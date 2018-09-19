package pieces;

import game.Board;
import game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Bishop extends Piece {

    public Bishop(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 3;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        if(this.color || !this.color) {

            // RIGHT AND UP
            Move move = createMove(board, x - 1, y + 1);
            while(move.isInsideBoard()) {

                if(this.hasSameColor(board, move)) {
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() != Empty.class) {
                    moves.add(move);
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() == Empty.class) {
                    moves.add(move);
                }

                move = createMove(board, move.toX - 1, move.toY + 1);
            }

            //RIGHT AND DOWN
            move = createMove(board, x + 1, y + 1);
            while(move.isInsideBoard()) {

                if(this.hasSameColor(board, move)) {
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() != Empty.class) {
                    moves.add(move);
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() == Empty.class) {
                    moves.add(move);
                }

                move = createMove(board, move.toX + 1, move.toY + 1);
            }

            //LEFT AND DOWN
            move = createMove(board, x + 1, y - 1);
            while(move.isInsideBoard()) {

                if(this.hasSameColor(board, move)) {
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() != Empty.class) {
                    moves.add(move);
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() == Empty.class) {
                    moves.add(move);
                }

                move = createMove(board, move.toX + 1, move.toY - 1);
            }

            //LEFT AND UP
            move = createMove(board, x - 1, y - 1);
            while(move.isInsideBoard()) {

                if(this.hasSameColor(board, move)) {
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() != Empty.class) {
                    moves.add(move);
                    break;
                }
                if(board.getPiece(move.toX, move.toY).getClass() == Empty.class) {
                    moves.add(move);
                }

                move = createMove(board, move.toX - 1, move.toY - 1);
            }

        }

        return moves;

    }

}
