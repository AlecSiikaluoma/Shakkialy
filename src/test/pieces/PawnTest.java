package test.pieces;

import main.game.Board;
import main.game.Move;
import org.junit.Test;
import main.pieces.Pawn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by alecsiikaluoma on 19.9.2018.
 */
public class PawnTest {

    Board board = new Board();


    /**
     * Test that can move one tile forward and two tiles when it is at the starting position.
     * Test also that pawn can eat other main.pieces at from right positions.
     */
    @Test
    public void pawnMovement() {

        // Move 1 tile
        Move move1 = new Move(board, true, 6, 0, 5, 0);
        assertTrue(move1.isLegal());

        // Try to move now two tils
        move1 = new Move(board, true, 5, 0, 3, 0);
        assertFalse(move1.isLegal());

        // Move 2 tiles
        Move move2 = new Move(board, true, 6, 0, 4, 0);
        assertTrue(move2.isLegal());
        board.move(move2);

        // Move black pawn
        Move move3 = new Move(board, false, 1, 1, 3, 1);
        assertTrue(move3.isLegal());
        board.move(move3);

        // White can eat black pawn
        Move eat = new Move(board, true, 4, 0, 3, 1);
        assertTrue(eat.isLegal());
        board.move(eat);

        // Move is executed
        assertTrue(board.getPiece(3, 1).color);
        assertTrue(board.getPiece(3, 1).getClass() == Pawn.class);
    }

    /**
     * Test that white can perform en passant move.
     */
    @Test
    public void whitePawnEnPassant() {


        Move move = new Move(board, true, 6, 0, 4, 0);
        board.move(move);

        move = new Move(board, false, 1, 6, 2, 6);
        board.move(move);

        move = new Move(board, true, 4, 0, 3, 0);
        board.move(move);

        move = new Move(board, false, 1, 1, 3, 1);
        board.move(move);

        move = new Move(board, true, 3, 0, 2, 1);
        assertTrue(move.isLegal());

        board.move(move);

        // Check that white pawn goes to right square
        assertTrue(board.getPiece(2, 1).color);
        // Check that black pawn is removed
        assertTrue(board.getPiece(3, 1).empty);
    }


    /**
     * Test that black can perform en passant move.
     */
    @Test
    public void blackPawnEnPassant() {


        Move move = new Move(board, true, 6, 7, 5, 7);
        board.move(move);

        move = new Move(board, false, 1, 0, 3, 0);
        board.move(move);

        move = new Move(board, true, 5, 7, 4, 7);
        board.move(move);

        move = new Move(board, false, 3, 0, 4, 0);
        board.move(move);

        move = new Move(board, true, 6, 1, 4, 1);
        board.move(move);

        move = new Move(board, false, 4, 0, 5, 1);
        assertTrue(move.isLegal());

        board.move(move);

        // Check that white pawn goes to right square
        assertFalse(board.getPiece(5, 1).color);
        // Check that black pawn is removed
        assertTrue(board.getPiece(4, 1).empty);
    }


}
