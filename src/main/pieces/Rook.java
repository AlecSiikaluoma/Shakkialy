package main.pieces;

import main.game.Board;
import main.game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Rook extends Piece {

    public boolean hasBeenMoved = false;

    public Rook(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 5;
    }

    public Rook(Rook o) {
        super(o.color, o.x, o.y);
        this.hasBeenMoved = o.hasBeenMoved;
        this.value = o.value;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        Move move = createMove(board, x + 1, y);
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

            move = createMove(board, move.toX + 1, move.toY);
        }

        move = createMove(board, x, y + 1);
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

            move = createMove(board, move.toX, move.toY + 1);
        }


        move = createMove(board, x - 1, y);
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

            move = createMove(board, move.toX - 1, move.toY);
        }

        move = createMove(board, x, y - 1);
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

            move = createMove(board, move.toX, move.toY - 1);
        }

        return moves;

    }

}
