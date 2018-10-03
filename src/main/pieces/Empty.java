package main.pieces;

import main.game.Board;
import main.game.Move;
import main.data.structures.ArrayList;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Empty extends Piece {
    public Empty() {
        super();
        this.empty = true;
    }

    public Empty(Empty o) {
        super();
        this.empty = true;
    }

    public ArrayList<Move> generateAllLegalMoves(Board board) {
        return new ArrayList<>();
    }
}
