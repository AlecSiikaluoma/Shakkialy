package main.pieces;

import main.game.Board;
import main.game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Knight extends Piece {

    public Knight(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 3;
    }

    public Knight(Knight o) {
        super(o.color, o.x, o.y);
        this.value = o.value;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        // Knight works the same way for white and black
        if(this.getColor() || !this.getColor()) {
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
