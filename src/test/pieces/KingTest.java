package test.pieces;

import main.game.Board;
import main.game.Move;
import main.pieces.Bishop;
import main.pieces.Piece;
import main.ui.Utils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by alecsiikaluoma on 21.9.2018.
 */
public class KingTest {

    Board board = new Board();

    @Test
    public void testKingMovement() {

        // King cannot move
        Piece p = Utils.stringToPiece(board, "d1");
        assertTrue(p.generateAllLegalMoves(board).isEmpty());
    }

    @Test
    public void moveCannotExposeKing() {

        board = new Board();

        Move move = Utils.stringToMove(board, "e2 e4");
        board.move(move);
        move = Utils.stringToMove(board, "e7 e5");
        board.move(move);
        board.move(Utils.stringToMove(board, "d1 e2"));
        board.move(Utils.stringToMove(board, "f8 e7"));
        board.move(Utils.stringToMove(board, "f2 f4"));
        board.move(Utils.stringToMove(board, "e5 f4"));
        board.move(Utils.stringToMove(board, "e4 e5"));
        board.move(Utils.stringToMove(board, "d7 d6"));
        board.move(Utils.stringToMove(board, "e5 d6"));

        // Checks that bishop cannot be moved because it would expose king
        Move m = Utils.stringToMove(board, "e7 f8");
        assertFalse(m.isLegal());


        // If we move queen away
        //board.move(Utils.stringToMove(board, "e2 d3"));
        //assertTrue(Utils.stringToPiece(board, "e7").generateAllLegalMoves(board).size() > 0);

    }

    @Test
    public void castling() {

        board = new Board();

        board.move(Utils.stringToMove(board, "e2 e4"));
        board.move(Utils.stringToMove(board, "f1 e2"));
        board.move(Utils.stringToMove(board, "g1 f3"));

        // Now should be able to castle
        assertTrue(Utils.stringToMove(board, "e1 h1").isLegal());



    }

}
