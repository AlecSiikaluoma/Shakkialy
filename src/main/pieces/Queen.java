package main.pieces;

import main.game.Board;
import main.game.Move;
import main.data.structures.ArrayList;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Queen extends Piece {

    public Queen(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 9;
    }

    public Queen(Queen o) {
        super(o.color, o.x, o.y);
        this.value = o.value;
    }

    public ArrayList<Move> generateAllLegalMoves(Board board) {

        ArrayList<Move> moves = new ArrayList<>();

        // moveslike king to any direction but takes many steps

        //move up if black and down if white
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i==0 && j==0) continue;
                Move move = createMove(board, x + i, y + j);
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

                    move = createMove(board, move.toX + i, move.toY + j);
                }
            }
        }

        return moves;
    }

}
