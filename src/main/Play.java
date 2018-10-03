package main;

import java.util.Scanner;

/**
 * Created by alecsiikaluoma on 14.9.2018.
 * This class implements a simple way to play against a computer.
 */

import main.game.Chess;
import main.game.Move;
import main.pieces.*;
import main.data.structures.ArrayList;

public class Play {

    public static void main(String[] args) {

        boolean color = true;
        Game game = new Game(color);

        System.out.print("To choose your color type 'white' (default) or 'black': ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        if(s.equals("white")) {
            color = Chess.WHITE;
        } else if(s.equals("black")) {
            color = Chess.BLACK;
        }
        System.out.println("RED represents white pieces");
        System.out.println("BLUE represents black pieces");
        System.out.println();

        System.out.println(game.board);
        System.out.println();

        System.out.println("You choose your move this way: 'b2 b3', 'e5 e8'.");
        System.out.println();

        if(!color) {
            game.computerMove();
        }


        while(true) {

            // User's turn
            if(game.isMate(color)) {
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
                    ArrayList<Integer> arrayMove = Utils.moveToArray(str);
                    if(color) {
                        ArrayList<Integer> arr = new ArrayList<>();
                        arr.add(arrayMove.get(0));
                        arr.add(7-arrayMove.get(1));
                        arr.add(arrayMove.get(2));
                        arr.add(7-arrayMove.get(3));
                        arrayMove = arr;
                    }
                    Move move = new Move(game.board, Chess.WHITE, arrayMove.get(1), arrayMove.get(0), arrayMove.get(3), arrayMove.get(2));
                    if(move.piece.getClass() == Pawn.class && (move.toX == 7 || move.toX == 0)) {
                        System.out.println("Which piece would you like promote pawn to (K/B/R/Q) (Default is Q (Queen): ");
                        scan = new Scanner(System.in);
                        str = scan.nextLine();
                        if(str.equals("K")) {
                            move.promotion = new Knight(color, move.toX, move.toY);
                        } else if(str.equals("B")) {
                            move.promotion = new Bishop(color, move.toX, move.toY);
                        } else if(str.equals("R")) {
                            move.promotion = new Rook(color, move.toX, move.toY);
                        } else {
                            move.promotion = new Queen(color, move.toX, move.toY);
                        }
                    }
                    boolean successfulMove = game.board.move(move);
                    if(!successfulMove) {
                        System.out.println("This move is illegal.");
                    } else {
                        System.out.println();
                        System.out.println(game.board);
                        System.out.println();
                        break;
                    }
                } else {
                    System.out.println("This in not a valid move.");
                }
            }

            // Computer's turn
            System.out.println("Computer moving...");
            System.out.println();
            game.computerMove();
            if(game.isMate(!color)) {
                break;
            }

            System.out.println("Computer move:");
            System.out.println();
            System.out.println(game.board);
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
                System.out.println("It's a draw.");
                break;
        }


    }

}
