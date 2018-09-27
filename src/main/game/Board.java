package main.game;

import main.pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */

public class Board {

    private Piece[][] board;

    public List<Move> whiteMoves;
    public List<Move> blackMoves;

    /**
     * Initialize chess starting position for board.
     */
    public Board() {
        whiteMoves = new ArrayList<>();
        blackMoves = new ArrayList<>();

        board = new Piece[8][8];

        board[0][0] = new Rook(Chess.BLACK, 0, 0);
        board[0][1] = new Knight(Chess.BLACK, 0, 1);
        board[0][2] = new Bishop(Chess.BLACK, 0, 2);
        board[0][3] = new Queen(Chess.BLACK, 0, 3);
        board[0][4] = new King(Chess.BLACK, 0, 4);
        board[0][5] = new Bishop(Chess.BLACK, 0, 5);
        board[0][6] = new Knight(Chess.BLACK, 0, 6);
        board[0][7] = new Rook(Chess.BLACK, 0, 7);

        for (int i = 0; i < board[1].length; i++) {
            board[1][i] = new Pawn(Chess.BLACK, 1, i);
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                board[2+i][j] = new Empty();
            }
        }

        for (int i = 0; i < board[6].length; i++) {
            board[6][i] = new Pawn(Chess.WHITE, 6, i);
        }

        board[7][0] = new Rook(Chess.WHITE, 7, 0);
        board[7][1] = new Knight(Chess.WHITE, 7, 1);
        board[7][2] = new Bishop(Chess.WHITE, 7, 2);
        board[7][3] = new Queen(Chess.WHITE, 7, 3);
        board[7][4] = new King(Chess.WHITE, 7, 4);
        board[7][5] = new Bishop(Chess.WHITE, 7, 5);
        board[7][6] = new Knight(Chess.WHITE, 7, 6);
        board[7][7] = new Rook(Chess.WHITE, 7, 7);
    }


    public Piece getPiece(int fromX, int fromY) {
        return board[fromX][fromY];
    }

    public Piece getPiece(Move move) {
        return board[move.toX][move.toY];
    }

    public void executeMove(Move move) {
        Piece piece = this.getPiece(move.fromX, move.fromY);
        // Save moves
        if(piece.color) {
           this.whiteMoves.add(move);
        } else if(!piece.color) {
            this.blackMoves.add(move);
        }
        // Check for special moves
        if(piece.getClass() == Pawn.class) {
            Pawn p = (Pawn) piece;
            ((Pawn) piece).neverMoved = false;
            if(p.isEnPassant(move, this)) {
                move.enPassant = true;
            }
            if(move.promotion.getClass() != Empty.class) {
                this.board[move.toX][move.toY] = move.promotion;
                this.board[move.fromX][move.fromY] = new Empty();
                return;
            }
        } else if(piece.getClass() == King.class) {
            if(Math.abs(move.fromY - move.toY) > 1) {
                move.castling = true;
            }
        }

        // Execute moves and special moves
        if(move.enPassant) {
            piece.x = move.toX;
            piece.y = move.toY;
            this.board[move.toX][move.toY] = piece;
            if(move.piece.color) {
                this.board[move.toX+1][move.toY] = new Empty();
            } else {
                board[move.toX-1][move.toY] = new Empty();
            }
            board[move.fromX][move.fromY] = new Empty();
        } else if(move.castling) {
            Rook rook = (Rook) board[move.toX][move.toY];
            King king = (King) board[move.fromX][move.fromY];
            if(move.toY == 0) {
                board[move.toX][2] = king;
                board[move.toX][3] = rook;
                board[move.toX][0] = new Empty();
                board[move.toX][4] = new Empty();
            } else if(move.toY == 7) {
                board[move.toX][6] = king;
                board[move.toX][5] = rook;
                board[move.toX][7] = new Empty();
                board[move.toX][4] = new Empty();
            }
            rook.hasBeenMoved = true;
            king.hasBeenMoved = true;
            move.castling = false;
        } else {
            piece.x = move.toX;
            piece.y = move.toY;
            this.board[move.toX][move.toY] = piece;
            this.board[move.fromX][move.fromY] = new Empty();
        }
    }

    public void setPiece(Move move) {
        Piece piece = this.getPiece(move.fromX, move.fromY);
        piece.x = move.toX;
        piece.y = move.toY;
        this.board[move.toX][move.toY] = piece;
        this.board[move.fromX][move.fromY] = new Empty();
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public Board clone() {
        Piece[][] p = new Piece[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece piece = this.getPiece(i,j).clone();
                p[i][j] = piece;
            }
        }

        Board b = new Board();
        b.blackMoves = this.blackMoves;
        b.whiteMoves = this.whiteMoves;
        b.board = p;

        return b;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < board.length; i++) {
            str = str.concat(7-i + 1 + "|");
            for (int j = 0; j < board[i].length; j++) {
                str = str.concat(" ");
                Piece p = this.getPiece(i,j);
                if(p.getClass() == Empty.class) {
                } else if(p.color) {
                    str = str.concat("\u001B[31m ");
                } else {
                    str = str.concat("\u001B[34m ");
                }

                if(p.getClass() == Pawn.class) {
                    str = str.concat("P");
                } else if(p.getClass() == Knight.class) {
                    str = str.concat("N");
                } else if(p.getClass() == Bishop.class) {
                    str = str.concat("B");
                } else if(p.getClass() == Rook.class) {
                    str = str.concat("R");
                } else if(p.getClass() == Queen.class) {
                    str = str.concat("Q");
                } else if(p.getClass() == King.class) {
                    str = str.concat("K");
                } else {
                    str = str.concat(" 0");
                }

                str = str.concat(" \u001B[0m");
            }
            str = str.concat("\n");
        }
        str = str.concat("---------------------------------\n" +
                "    a   b   c   d   e   f   g   h");
        return str;
    }

    public boolean existAttackOnKing(boolean color) {

        int kingX = 0;
        int kingY = 0;


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(this.getPiece(i,j).getColor() == color && this.getPiece(i, j).getClass() == King.class) {
                    kingX = i;
                    kingY = j;
                }
            }
        }

        // Check that no Pawn attack exists
        /*Board nb = this.clone();
        nb.board[kingX][kingY] = new Pawn(color, kingX, kingY);
        List<Move> ms = nb.board[kingX][kingY].generateAllLegalMoves(nb);
        for(Move m : ms) {
            Piece p = nb.getPiece(m.toX, m.toY);
            if(p.getClass() == Pawn.class && p.color != color) {
                return true;
            }
        }

        // Check that no Horse attack exists
        nb = this.clone();
        nb.board[kingX][kingY] = new Knight(color, kingX, kingY);
        ms = nb.board[kingX][kingY].generateAllLegalMoves(nb);
        for(Move m : ms) {
            Piece p = nb.getPiece(m.toX, m.toY);
            if(p.getClass() == Knight.class && p.color != color) {
                return true;
            }
        }

        // Check that no Bishop attack exists
        nb = this.clone();
        nb.board[kingX][kingY] = new Bishop(color, kingX, kingY);
        ms = nb.board[kingX][kingY].generateAllLegalMoves(nb);
        for(Move m : ms) {
            Piece p = nb.getPiece(m.toX, m.toY);
            if(p.getClass() == Bishop.class && p.color != color) {
                return true;
            }
        }

        // Check that no rook attack exists
        nb = this.clone();
        nb.board[kingX][kingY] = new Rook(color, kingX, kingY);
        ms = nb.board[kingX][kingY].generateAllLegalMoves(nb);
        for(Move m : ms) {
            Piece p = nb.getPiece(m.toX, m.toY);
            if(p.getClass() == Rook.class && p.color != color) {
                return true;
            }
        }

        // Check that no Queen attack exists
        nb = this.clone();
        nb.board[kingX][kingY] = new Queen(color, kingX, kingY);
        ms = nb.board[kingX][kingY].generateAllLegalMoves(nb);
        for(Move m : ms) {
            Piece p = nb.getPiece(m.toX, m.toY);
            if(p.getClass() == Queen.class && p.color != color) {
                return true;
            }
        }

        // Check that no King attack exists
        nb = this.clone();
        nb.board[kingX][kingY] = new King(color, kingX, kingY);
        ms = nb.board[kingX][kingY].generateAllLegalMoves(nb);
        for(Move m : ms) {
            Piece p = nb.getPiece(m.toX, m.toY);
            if(p.getClass() == King.class && p.color != color) {
                return true;
            }
        }*/


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece p = this.getPiece(i, j);
                if(!p.empty && p.getColor() != color) {
                    List<Move> moves = p.generateAllLegalMoves(this);
                    for (Move move1 : moves) {
                        if(move1.toX == kingX && move1.toY == kingY) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public List<Move> generateAllMoves(boolean color) {
        List<Move> ms = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(color == getPiece(i,j).color && !getPiece(i,j).empty) {

                    List<Move> moves = getPiece(i,j).generateAllLegalMoves(this);

                    for (Move move : moves) {

                        Board nb = this.clone();
                        nb.executeMove(move);
                        if(!nb.existAttackOnKing(color)) {
                            ms.add(move);
                        }

                    }
                }

            }
        }

        return ms;
    }


    public boolean move(Move move) {
        if(move.isLegal()) {
            this.executeMove(move);
            return true;
        }
        return false;
    }

}
