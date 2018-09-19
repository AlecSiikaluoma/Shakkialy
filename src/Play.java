import java.util.List;
import java.util.Scanner;

/**
 * Created by alecsiikaluoma on 14.9.2018.
 * This class implements a simple way to play against a computer.
 */

import game.Board;
import game.Chess;
import game.Move;
import ui.CommandLineInterface;
import ui.Utils;

public class Play {

    public static void main(String[] args) {

        boolean color = true;
        Game game = new Game(color);
        Board board = new Board();

        System.out.print("To choose your color type: 'white' (default) or 'black': ");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        if(s.equals("white")) {
            color = true;
        } else if(s.equals("black")) {
            color = false;
        }

        CommandLineInterface.printChessBoard(game.board);

        System.out.println("You choose your move this way: 'b2 b3', 'e5 e8'.");


        while(true) {

            // User's turn
            while(true) {
                System.out.print("Enter your move: ");
                scan = new Scanner(System.in);
                String str = scan.nextLine();
                if(str.equals("exit") || str.equals("e")) {
                    return ;
                }
                if (Utils.isValidMoveString(str)) {
                    List<Integer> arrayMove = Utils.moveToArray(str);
                    Move move = new Move(board, Chess.WHITE, arrayMove.get(0), arrayMove.get(1), arrayMove.get(2), arrayMove.get(3));
                    boolean successfulMove = board.executeMove(move);
                    if(!successfulMove) {
                        System.out.println("This move is illegal.");
                    } else {
                        CommandLineInterface.printChessBoard(game.board);
                        break;
                    }
                } else {
                    System.out.println("This in not a valid move.");
                }
            }

            if(game.isMate()) {
                break;
            }

            // Computer's turn
            game.computerMove();
            if(game.isMate()) {
                break;
            }


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
