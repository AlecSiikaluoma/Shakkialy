package test;

import main.Game;
import main.game.Board;
import main.game.Move;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by alecsiikaluoma on 20.9.2018.
 */
public class GameTest {

    Game game = new Game(true);

    @Test
    public void whiteTriesToMoveBlack() {
        assertFalse(game.board.move(new Move(game.board, true, 1, 0, 3, 0)));
    }

    @Test
    public void blackTriesToMoveWhite() {
        game = new Game(false);
        assertFalse(game.board.move(new Move(game.board, false, 6, 0, 5, 0)));
    }

}
