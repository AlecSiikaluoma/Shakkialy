package test.pieces;

/**
 * Created by alecsiikaluoma on 19.9.2018.
 */

import main.game.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PieceTest {


    Board board = new Board();

    @Test
    public void allLegalMovesAtBegining() {
        assertEquals(board.generateAllMoves(true), 20);
        assertEquals(board.generateAllMoves(false), 20);
    }


}
