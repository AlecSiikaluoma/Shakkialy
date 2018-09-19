package test.pieces;

/**
 * Created by alecsiikaluoma on 19.9.2018.
 */

import game.Board;
import game.Chess;
import game.Move;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import pieces.*;
import ui.CommandLineInterface;
import ui.CommandLineInterface.*;

public class TestPiece {


    Board board = new Board();
    Move move = new Move(board, Chess.WHITE, 6, 4, 4, 4);

    // Test Pawn movement
    @Test
    public void testPawn() {
        Piece p = board.getPiece(1, 0);
        assertEquals(p.getClass(), Pawn.class);

        assertEquals(p.generateAllLegalMoves(board).size(), 2);

        board.executeMove(move);

        System.out.println(board.getPiece(7,5).generateAllLegalMoves(board).size());
    }







}
