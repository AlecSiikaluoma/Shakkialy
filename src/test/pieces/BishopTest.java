package test.pieces;

import main.game.Board;
import main.game.Move;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * Created by alecsiikaluoma on 21.9.2018.
 */
public class BishopTest {

    Board board = new Board();

    @Test
    public void testBishopMovement() {

        // Check it cannot move through own pieces.
        assertEquals(board.getPiece(7, 2).generateAllLegalMoves(board).size(), 0);

        Move move = new Move(board, true, 6, 3, 5, 3);
        board.move(move);

        // Check that it can move to right squares.
        assertEquals(board.getPiece(7, 2).generateAllLegalMoves(board).size(), 5);

        move = new Move(board, true, 7, 2, 3, 6);
        board.move(move);

        // Check that it can eat a black pawn
        move = new Move(board, true, 3, 6, 1, 4);
        assertTrue(move.isLegal());


    }

}
