package test;

import main.game.Board;
import main.game.Move;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by alecsiikaluoma on 20.9.2018.
 */
public class GameTest {

    Board board = new Board();

    @Test
    public void whiteTriesToMoveBlack() {

        Move move = new Move(board, true, 1, 0, 3, 0);
        assertFalse(move.isLegal());

    }

    @Test
    public void blackTriesToMoveWhite() {

        Move move = new Move(board, false, 6, 0, 5, 0);
        assertFalse(move.isLegal());

    }

}
