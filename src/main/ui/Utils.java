package main.ui;

import main.game.Board;
import main.game.Move;
import main.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 14.9.2018.
 * This class contains some useful methods for interacting with the "chess board".
 *
 */
public final class Utils {

    public static Piece stringToPiece(Board board, String s) {
        s = s.concat(" a4");
        List<Integer> arr = moveToArray(s);
        return board.getPiece(7-arr.get(1), arr.get(0));
    }

    public static Move stringToMove(Board board, String s) {
        List<Integer> arr = moveToArray(s);
        Piece p = board.getPiece(arr.get(1), arr.get(0));
        return new Move(board, p.color, 7-arr.get(1), arr.get(0), 7-arr.get(3), arr.get(2));
    }

    public static boolean isValidMoveString(String s) {
        if(s.length() == 5 && s.substring(0,1).matches("[a-h]") && s.substring(1,2).matches("[1-8]") && s.substring(2,3).matches("\\s")
                && s.substring(3,4).matches("[a-h]") && s.substring(4,5).matches("[1-8]")) {
            return true;
        }
        return false;
    }

    public static List<Integer> moveToArray(String m) {
        List<Integer> arr = new ArrayList<Integer>();
        m = m.replaceAll("\\s+", "");
        for(int i = 0; i < m.length(); i++) {
            if(Character.isDigit(m.charAt(i))) {
                arr.add(Integer.parseInt(m.substring(i,i+1)) - 1);
            } else {
                int out = 0;
                switch (m.charAt(i)) {
                    case 'a':
                        out = 0;
                        break;
                    case 'b':
                        out = 1;
                        break;
                    case 'c':
                        out = 2;
                        break;
                    case 'd':
                        out = 3;
                        break;
                    case 'e':
                        out = 4;
                        break;
                    case 'f':
                        out = 5;
                        break;
                    case 'g':
                        out = 6;
                        break;
                    case 'h':
                        out = 7;
                        break;
                    default:
                        break;
                }
                arr.add(out);
            }
        }
        return arr;
    }

}
