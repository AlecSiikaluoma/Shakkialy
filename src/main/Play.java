package main;

import java.util.List;
import java.util.Scanner;

/**
 * Created by alecsiikaluoma on 14.9.2018.
 * This class implements a simple way to play against a computer.
 */

import main.game.Board;
import main.game.Chess;
import main.game.Move;
import main.ui.Utils;

public class Play {

    public static void main(String[] args) {

        boolean color = true;
        Game game = new Game(color);
        Board board = new Board();

        System.out.print("To choose your color type: 'white' (default) or 'black': ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        if(s.equals("white")) {
            color = true;
        } else if(s.equals("black")) {
            color = false;
        }

        System.out.println(board);
        System.out.println();

        System.out.println("You choose your move this way: 'b2 b3', 'e5 e8'.");


        while(true) {

            // User's turn
            if(game.isMate(Chess.WHITE)) {
                break;
            }
            while(true) {
                System.out.print("Enter your move: ");
                scan = new Scanner(System.in);
                String str = scan.nextLine();
                if(str.equals("exit") || str.equals("e")) {
                    return ;
                }
                if (Utils.isValidMoveString(str)) {
                    List<Integer> arrayMove = Utils.moveToArray(str);
                    if(color) {
                        arrayMove.set(3, 7-arrayMove.get(3));
                        arrayMove.set(1, 7-arrayMove.get(1));
                    }
                    Move move = new Move(board, Chess.WHITE, arrayMove.get(1), arrayMove.get(0), arrayMove.get(3), arrayMove.get(2));
                    boolean successfulMove = board.move(move);
                    if(!successfulMove) {
                        System.out.println("This move is illegal.");
                    } else {
                        System.out.println();
                        System.out.println(board);
                        System.out.println();
                        break;
                    }
                } else {
                    System.out.println("This in not a valid move.");
                }
            }

            if(game.isMate(Chess.BLACK)) {
                break;
            }

            // Computer's turn
            System.out.println("Computer moving...");
            game.computerMove(board);

            System.out.println("Computer move:");
            System.out.println(board);
            System.out.println();

        }

        switch (game.getWinner()) {
            case BLACK:
                System.out.println("Black wins.");
                break;
            case WHITE:
                System.out.println("White wins.");
                break;
            case DRAW:
                System.out.println("It's a win.");
                break;
        }


    }

}
