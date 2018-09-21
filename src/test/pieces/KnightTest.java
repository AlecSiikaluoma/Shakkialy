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
public class KnightTest {


    Board board = new Board();

    @Test
    public void knightMovement() {

        assertEquals(board.getPiece(7,1).generateAllLegalMoves(board).size(), 2);

        // White: move horse to C3 square
        Move move = new Move(board, true, 7, 1, 5, 2);
        assertTrue(move.isLegal());

        board.move(move);

        // Black: move pawn so that knight can eat it
        move = new Move(board, false, 1, 3, 3, 3);
        board.move(move);

        // Test that knight can eat the pawn.
        move = new Move(board, true, 5, 2, 3, 3);
        assertTrue(move.isLegal());

        board.move(move);

        // Test that horse can move to all square around it.
        assertEquals(board.getPiece(3, 3).generateAllLegalMoves(board).size(), 8);

        // Move a pawn
        move = new Move(board, true, 6, 4, 5, 4);
        board.move(move);

        // Try to move knight on that same square
        move = new Move(board, true, 3, 3, 5, 4);
        assertFalse(move.isLegal());
    }




}
