package pieces;

import game.Board;
import game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class King extends Piece {

    public boolean hasBeenMoved = false;

    public King(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 10000;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        // CASTLING
        if(this.color) {
            if(!this.hasBeenMoved) {
                //
                if(board.getPiece(7,0).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(7,0);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(7,1).empty & board.getPiece(7,2).empty) {
                            Move move = createMove(board, 7, 0);
                            move.castling = true;
                            moves.add(move);
                        }
                    }
                }
                //Queen side castle
                if(board.getPiece(7,7).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(7,7);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(7,6).empty & board.getPiece(7,5).empty && board.getPiece(7, 4).empty) {
                            Move move = createMove(board, 7, 7);
                            move.castling = true;
                            moves.add(move);
                        }
                    }
                }
            }
        } else {
            if(!this.hasBeenMoved) {
                //
                if(board.getPiece(0,0).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(0,0);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(0,1).empty & board.getPiece(0,2).empty) {
                            Move move = createMove(board, 0, 0);
                            move.castling = true;
                            moves.add(move);
                        }
                    }
                }
                //Queen side castle
                if(board.getPiece(0,7).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(0,7);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(0,6).empty & board.getPiece(0,5).empty && board.getPiece(0, 4).empty) {
                            Move move = createMove(board, 0, 7);
                            move.castling = true;
                            moves.add(move);
                        }
                    }
                }
            }
        }

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i==0 && j==0) continue;
                Move move = createMove(board, x + i, y + j);
                if(move.isInsideBoard() && !this.hasSameColor(board, move)) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

}
