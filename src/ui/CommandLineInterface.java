package ui;

import game.Board;
import pieces.Piece;

/**
 * Created by alecsiikaluoma on 14.9.2018.
 * This provide some useful methods for creating a simple chess interface for the command line.
 *
 */
public final class CommandLineInterface {

    public static void printChessBoard(int[][] position) {
        for(int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + "|");
            for (int i1 : position[i]) {
                String start = "";
                if(i1 >= 0) {
                    start += " ";
                }
                System.out.print(" " + start + i1);
            }
            System.out.print("\n");
        }
        System.out.println("-------------------------\n" +
                "    a  b  c  d  e  f  g  h");
    }

    public static void printChessBoard(Board b) {
        for (int i = 0; i < b.getBoard().length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < b.getBoard()[i].length; j++) {
                String start = "";
                if(b.getPiece(i,j).empty || b.getPiece(i,j).getColor() == Piece.Color.WHITE) {
                    start += " ";
                }
                if(b.getPiece(i,j).getColor() == Piece.Color.BLACK) {
                    System.out.print(" " + start + ((-1)*b.getPiece(i,j).value));
                } else {
                    System.out.print(" " + start + b.getPiece(i, j).value);
                }
            }
            System.out.print("\n");
        }
        System.out.println("-------------------------\n" +
                "    a  b  c  d  e  f  g  h");
    }

}
