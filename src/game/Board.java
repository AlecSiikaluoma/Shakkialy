package game;

import pieces.*;

import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */

public class Board {

    private Piece[][] board;

    public List<Move> whiteMoves;
    public List<Move> blackMoves;

    public Board() {
        board = new Piece[8][8];

        board[0][0] = new Rook(Chess.BLACK, 0, 0);
        board[0][1] = new Horse(Chess.BLACK, 0, 1);
        board[0][2] = new Bishop(Chess.BLACK, 0, 2);
        board[0][3] = new Queen(Chess.BLACK, 0, 3);
        board[0][4] = new King(Chess.BLACK, 0, 4);
        board[0][5] = new Bishop(Chess.BLACK, 0, 5);
        board[0][6] = new Horse(Chess.BLACK, 0, 5);
        board[0][7] = new Rook(Chess.BLACK, 0, 7);

        for (int i = 0; i < board[1].length; i++) {
            board[1][i] = new Pawn(Chess.BLACK, 1, i);
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                board[2+i][j] = new Empty();
            }
        }

        for (int i = 0; i < board[1].length; i++) {
            board[6][i] = new Pawn(Chess.WHITE, 6, i);
        }

        board[7][0] = new Rook(Chess.WHITE, 7, 0);
        board[7][1] = new Horse(Chess.WHITE, 7, 1);
        board[7][2] = new Bishop(Chess.WHITE, 7, 2);
        board[7][3] = new Queen(Chess.WHITE, 7, 3);
        board[7][4] = new King(Chess.WHITE, 7, 4);
        board[7][5] = new Bishop(Chess.WHITE, 7, 5);
        board[7][6] = new Horse(Chess.WHITE, 7, 6);
        board[7][7] = new Rook(Chess.WHITE, 7, 7);


        /*int[][] startingPosition = {{-4, -2, -3, -5, -6, -3, -2, -4},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {4, 2, 3, 5, 6, 3, 2, 4}};

        board = convertNumsToPieces(startingPosition);*/
    }

    /*private Piece[][] convertNumsToPieces(int[][] nums) {

        Piece[][] pieces = new Piece[8][8];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                switch (nums[i][j]) {
                    case 0:
                        pieces[i][j] = new Empty();
                        break;
                    case -1:
                        pieces[i][j] = new Pawn(Chess.BLACK);
                        break;
                    case 1:
                        pieces[i][j] = new Pawn(Chess.WHITE);
                        break;

                }
            }
        }

        return pieces;

    }*/


    public Piece getPiece(int fromX, int fromY) {
        return board[fromX][fromY];
    }

    public void executeMove(Move move) {
        Piece piece = this.getPiece(move.fromX, move.fromY);
        if(this.getPiece(move.toX,move.toY).getColor()) {
           this.whiteMoves.add(move);
        } else if(this.getPiece(move.toX, move.toY).getColor()) {
            this.blackMoves.add(move);
        }
        if(move.enPassant) {
            piece.x = move.toX;
            piece.y = move.toY;
            this.board[move.toX][move.toY] = piece;
            if(move.piece.getColor()) {
                this.board[move.toX-1][move.toY] = new Empty();
            } else {
                this.board[move.toX+1][move.toY] = new Empty();
            }
            this.board[move.fromX][move.fromY] = new Empty();
        } else if(move.castling) {
            // Normal castle
            Piece rook = board[move.toX][move.toY];
            Piece king = move.piece;
            if(move.toY == 0) {
                board[move.toX][6] = king;
                board[move.toX][5] = rook;
                board[move.toX][4] = new Empty();
                board[move.toX][7] = new Empty();
            } else if(move.toY == 7) {
                board[move.toX][2] = king;
                board[move.toX][3] = rook;
                board[move.toX][0] = new Empty();
                board[move.toX][4] = new Empty();
            }
        } else {
            piece.x = move.toX;
            piece.y = move.toY;
            this.board[move.toX][move.toY] = piece;
            this.board[move.fromX][move.fromY] = new Empty();
        }
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public Board clone() {
        Piece[][] p = new Piece[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                p[i][j] = getPiece(i,j);
            }
        }

        Board b = new Board();
        b.board = p;

        return b;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < board.length; i++) {
            str = str.concat(i + 1 + "|");
            for (int j = 0; j < board[i].length; j++) {
                String start = "";
                if(this.getPiece(i, j).empty || this.getPiece(i,j).getColor()) {
                    start += " ";
                }
                if(!this.getPiece(i, j).getColor()) {
                    str = str.concat(" " + start + ((-1)*this.getPiece(i,j).value));
                } else {
                    str = str.concat(" " + start + this.getPiece(i, j).value);
                }
            }
            str = str.concat("\n");
        }
        str = str.concat("-------------------------\n" +
                "    a  b  c  d  e  f  g  h");
        return str;
    }


    public boolean move(Move move) {
        if(move.isLegal()) {
            this.executeMove(move);
            return true;
        }
        return false;
    }

}
