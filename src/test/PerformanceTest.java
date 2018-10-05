package test;

import main.Game;
import org.junit.Test;

/**
 * Created by alecsiikaluoma on 3.10.2018.
 */
public class PerformanceTest {

    @Test
    public void performanceDepth3() {
        Game game = new Game(true);
        game.depth = 3;
        // test move generation performance after 5 moves
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();

        long sum = 0;
        long start = System.currentTimeMillis();
        game.computerMove(true);
        long end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(false);
        end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(true);
        end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(false);
        end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(true);
        end = System.currentTimeMillis();
        sum += end - start;

        System.out.println("Speed of move generation with search depth of 3: " + (sum / 5) + "ms.");
    }

    @Test
    public void performanceDepth4() {
        Game game = new Game(true);
        game.depth = 5;
        // test move generation performance after 5 moves
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();
        game.computerMove(true);
        game.computerMove();

        long sum = 0;
        long start = System.currentTimeMillis();
        game.computerMove(true);
        long end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(false);
        end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(true);
        end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(false);
        end = System.currentTimeMillis();
        sum += end - start;
        start = System.currentTimeMillis();
        game.computerMove(true);
        end = System.currentTimeMillis();
        sum += end - start;

        System.out.println("Speed of move generation with search depth of 4: " + (sum / 5) + "ms.");
    }

}
