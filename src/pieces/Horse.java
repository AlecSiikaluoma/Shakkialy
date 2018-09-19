package pieces;

import game.Board;
import game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Horse extends Piece {

    public Horse(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 3;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        // WHITE
        if(this.getColor() == Color.WHITE || this.getColor() == Color.BLACK) {
            // ONE RIGHT, TWO UP
            Move m = createMove(board, x - 2, y + 1);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
            // TWO RIGHT, ONE UP
            m = createMove(board, x - 1, y + 2);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
            // TWO RIGHT, ONE DOWN
            m = createMove(board, x + 1, y + 2);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
            // ONE RIGHT, TWO DOWN
            m = createMove(board, x + 2, y + 1);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }

            // ONE LEFT, TWO DOWN
            m = createMove(board, x + 2, y - 1);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
            // TWO LEFT, ONE DOWN
            m = createMove(board, x + 1, y - 2);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
            // TWO LEFT, ONE UP
            m = createMove(board, x - 1, y - 2);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
            // ONE LEFT, TWO UP
            m = createMove(board, x - 2, y - 1);
            if(m.isInsideBoard() && !this.hasSameColor(board, m)) {
                    moves.add(m);
            }
        }

        return moves;
    }

}
