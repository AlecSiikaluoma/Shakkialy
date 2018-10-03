package main.pieces;

import main.game.Board;
import main.game.Move;
import main.data.structures.ArrayList;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Bishop extends Piece {

    public Bishop(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 3;
    }

    public Bishop(Bishop o) {
        super(o.color, o.x, o.y);
        this.value = 3;
    }

    public ArrayList<Move> generateAllLegalMoves(Board board) {

        ArrayList<Move> moves = new ArrayList<>();

        for(int i = -1; i <= 1; i++) {
            if(i == 0) continue;
            for(int j = -1; j <= 1; j++) {
                if(j==0) continue;
                Move move = createMove(board, x + i, y + j);
                while (move.isInsideBoard()) {

                    if (this.hasSameColor(board, move)) {
                        break;
                    }
                    if (board.getPiece(move.toX, move.toY).getClass() != Empty.class) {
                        moves.add(move);
                        break;
                    }
                    if (board.getPiece(move.toX, move.toY).getClass() == Empty.class) {
                        moves.add(move);
                    }

                    move = createMove(board, move.toX + i, move.toY + j);
                }
            }
        }

        return moves;

    }

}
