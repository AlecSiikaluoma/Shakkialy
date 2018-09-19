package pieces;

import game.Board;
import game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Empty extends Piece {
    public Empty() {
        super();
        this.empty = true;
    }

    public List<Move> generateAllLegalMoves(Board board) {
        return new ArrayList<>();
    }
}
