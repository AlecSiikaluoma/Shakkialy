package test;

/**
 * Created by alecsiikaluoma on 26.9.2018.
 */

import main.Game;
import main.game.Board;
import main.game.Chess;
import main.game.Move;
import main.Utils;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComputerTest {


    @Test
    public void computerPreventsMate() {
        Board board = new Board();

        board.move(Utils.stringToMove(board, "e2 e4"));
        board.move(Utils.stringToMove(board, "e7 e5"));
        board.move(Utils.stringToMove(board, "f1 c4"));
        board.move(Utils.stringToMove(board, "b8 c6"));
        board.move(Utils.stringToMove(board, "d1 f3"));
        // BLACK TO move

        // Check that black prevents mate
        Game game = new Game(true);
        game.board = board;
        game.computerMove();
        Move move = Utils.stringToMove(board, "f3 f7");
        board.move(move);
        assertFalse(game.isMate(false));
    }

    @Test
    public void computerChoosesMate() {
        Board board = new Board();

        board.move(Utils.stringToMove(board, "e2 e4"));
        board.move(Utils.stringToMove(board, "e7 e5"));
        board.move(Utils.stringToMove(board, "f1 c4"));
        board.move(Utils.stringToMove(board, "b8 c6"));
        board.move(Utils.stringToMove(board, "d1 f3"));
        board.move(Utils.stringToMove(board, "h7 h6"));
        // WHITE TO move

        // Check that computer with White pieces chooses mate
        Game game = new Game(false);
        game.board = board;
        game.computerMove();
        assertTrue(game.isMate(false));
    }


    @Test
    public void eatsHighValuePiece() {

        Game game = new Game(false);

        game.board.move(Utils.stringToMove(game.board, "e2 e4"));
        game.board.move(Utils.stringToMove(game.board, "e7 e5"));
        game.board.move(Utils.stringToMove(game.board, "f1 c4"));
        game.board.move(Utils.stringToMove(game.board, "b8 c6"));
        game.board.move(Utils.stringToMove(game.board, "d1 f3"));
        game.board.move(Utils.stringToMove(game.board, "d8 f6"));
        game.board.move(Utils.stringToMove(game.board, "f3 f5"));
        game.board.move(Utils.stringToMove(game.board, "f6 f5"));

        game.computerMove();

        // Make sure computer (White) takes queen.
        assertTrue(game.board.getPiece(3, 5).color == Chess.WHITE);

    }


}
