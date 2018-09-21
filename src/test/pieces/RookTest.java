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
public class RookTest {

    Board board = new Board();

    @Test
    public void checkRookMovement() {

        assertTrue(board.getPiece(7, 0).generateAllLegalMoves(board).isEmpty());

        // Move pawn away
        Move move = new Move(board, true, 6, 0, 4, 0);
        board.move(move);

        move = new Move(board, true, 7, 0, 5, 0);
        assertTrue(move.isLegal());
        board.move(move);

        assertEquals(board.getPiece(5,0).generateAllLegalMoves(board).size(), 7+2);

        move = new Move(board, true, 5, 0, 5, 1);
        assertTrue(move.isLegal());
        board.move(move);

        // Can eat enemy pawn
        move = new Move(board, true, 5, 1, 1, 1);
        assertTrue(move.isLegal());
    }

}
