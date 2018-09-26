package test.pieces;

import main.game.Board;
import main.game.Move;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import main.Utils;

/**
 * Created by alecsiikaluoma on 21.9.2018.
 */
public class QueenTest {

    Board board = new Board();

    @Test
    public void testQueenMovement() {
        //Black queen can't move anywhere
        assertTrue(board.getPiece(0, 3).generateAllLegalMoves(board).isEmpty());

        // Move pawn away
        Move move = Utils.stringToMove(board, "e7 e5");
        board.move(move);

        // Move
        move = Utils.stringToMove(board, "d8 g5");
        assertTrue(move.isLegal());
        board.move(move);

        assertEquals(Utils.stringToPiece(board, "g5").generateAllLegalMoves(board).size(), 14);
    }


}
