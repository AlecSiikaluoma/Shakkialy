package main;

import java.util.concurrent.TimeUnit;

/**
 * Created by alecsiikaluoma on 29.9.2018.
 * With this class you can watch the computer play against itself.
 */
public class ComputerVsComputer {

    public static void main(String[] args) {

        System.out.println("Game between two computers.");

        Game game = new Game(true);
        System.out.println(game.board);

        while(true) {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println();
            System.out.println("White is moving...");
            System.out.println();
            if(game.isMate(true)) {
                break;
            }
            game.computerMove(true);
            System.out.println(game.board);
            System.out.println();
            System.out.println("Black is moving...");
            System.out.println();
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            if(game.isMate(false)) {
                break;
            }
            game.computerMove(false);
            System.out.println(game.board);
        }

        switch (game.getWinner()) {
            case WHITE:
                System.out.println("White wins.");
                break;
            case BLACK:
                System.out.println("Black wins.");
                break;
            case DRAW:
                System.out.println("It's a draw.");
                break;
        }


    }


}
