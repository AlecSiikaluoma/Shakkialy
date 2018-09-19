package pieces;

import game.Board;
import game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Pawn extends Piece {

    public Pawn(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 1;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        //Player is WHITE.
        if(this.color) {
            // 1 step
            Move move = createMove(board, x - 1, y);
            if(move.isInsideBoard() && !this.hasSameColor(board, move)) {
                    moves.add(move);
            }
            // 2 step
            move = createMove(board, x - 2, y);
            if(move.isInsideBoard()) {
                // Check it doesnt have a piece in front of it.
                if(board.getPiece(this.x-1, this.y).getClass() == Empty.class) {
                    if(!this.hasSameColor(board, move)) {
                        moves.add(move);
                    }
                }
            }

            // En passant
            if(this.x == 5) {
                Move blacksLastMove = board.blackMoves.get(board.blackMoves.size()-1);
                if(blacksLastMove.piece.getClass() == Pawn.class
                        && blacksLastMove.toX - blacksLastMove.fromX == 2
                        && Math.abs(this.y - blacksLastMove.toY) == 1
                        && board.getPiece(blacksLastMove.toX-1,blacksLastMove.toY).empty) {
                    Move enPassant = null;
                    if(blacksLastMove.toY > y) {
                        enPassant = createMove(board, x - 1, y + 1);
                        enPassant.enPassant = true;
                    } else {
                        enPassant = createMove(board, x - 1, y - 1);
                        enPassant.enPassant = true;
                    }
                }
            }

        } else {
            // 1 step
            Move move = createMove(board, x + 1, y);
            if(move.isInsideBoard() && !this.hasSameColor(board, move)) {
                    moves.add(move);
            }
            // 2 step
            move = createMove(board, x + 2, y);
            if(move.isInsideBoard()) {
                // Check it doesnt have a piece in front of it.
                if(board.getPiece(this.x + 1, this.y).getClass() == Empty.class) {
                    if (!this.hasSameColor(board, move)) {
                        moves.add(move);
                    }
                }
            }

            // En passant
            if(this.x == 5) {
                Move whitesLastMove = board.whiteMoves.get(board.whiteMoves.size()-1);
                if(whitesLastMove.piece.getClass() == Pawn.class
                        && whitesLastMove.toX - whitesLastMove.fromX == 2
                        && Math.abs(this.y - whitesLastMove.toY) == 1
                        && board.getPiece(whitesLastMove.toX + 1,whitesLastMove.toY).empty) {
                    Move enPassant = null;
                    if(whitesLastMove.toY > y) {
                        enPassant = createMove(board, x + 1, y + 1);
                        enPassant.enPassant = true;
                    } else {
                        enPassant = createMove(board, x + 1, y - 1);
                        enPassant.enPassant = true;
                    }
                }
            }

        }

        return moves;
    }


}
