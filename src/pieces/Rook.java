package pieces;

import game.Board;
import game.Move;

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

    public List<Move> generateAllLegalMoves(Board board) {
        return new ArrayList<>();
    }

}
