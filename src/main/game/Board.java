package main.game;

import main.pieces.*;

import main.data.structures.ArrayList;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 * Represents the board. It contains moves and pieces.
 */

public class Board {

    private Piece[][] board;

    public ArrayList<Move> whiteMoves;
    public ArrayList<Move> blackMoves;

    /**
     * Initialize chess starting position for board.
     */
    public Board() {
        whiteMoves = new ArrayList<>();
        blackMoves = new ArrayList<>();

        board = new Piece[8][8];

        board[0][0] = new Rook(false, 0, 0);
        board[0][1] = new Knight(false, 0, 1);
        board[0][2] = new Bishop(false, 0, 2);
        board[0][3] = new Queen(false, 0, 3);
        board[0][4] = new King(false, 0, 4);
        board[0][5] = new Bishop(false, 0, 5);
        board[0][6] = new Knight(false, 0, 6);
        board[0][7] = new Rook(false, 0, 7);

        for (int i = 0; i < board[1].length; i++) {
            board[1][i] = new Pawn(false, 1, i);
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                board[2+i][j] = new Empty();
            }
        }

        for (int i = 0; i < board[6].length; i++) {
            board[6][i] = new Pawn(true, 6, i);
        }

        board[7][0] = new Rook(true, 7, 0);
        board[7][1] = new Knight(true, 7, 1);
        board[7][2] = new Bishop(true, 7, 2);
        board[7][3] = new Queen(true, 7, 3);
        board[7][4] = new King(true, 7, 4);
        board[7][5] = new Bishop(true, 7, 5);
        board[7][6] = new Knight(true, 7, 6);
        board[7][7] = new Rook(true, 7, 7);
    }


    public Piece getPiece(int fromX, int fromY) {
        return board[fromX][fromY];
    }

    public Piece getPiece(Move move) {
        return board[move.toX][move.toY];
    }

    /**
     * Executes the given move. Checks all the necessary things like if the move is en passant or castling.
     * @param move Move to be executed
     */
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
                king.x = move.toX;
                king.y = 2;
                rook.x = move.toX;
                rook.y = 3;
                board[move.toX][0] = new Empty();
                board[move.toX][4] = new Empty();
            } else if(move.toY == 7) {
                board[move.toX][6] = king;
                board[move.toX][5] = rook;
                king.x = move.toX;
                king.y = 6;
                rook.x = move.toX;
                rook.y = 5;
                board[move.toX][7] = new Empty();
                board[move.toX][4] = new Empty();
            }
            rook.hasBeenMoved = true;
            king.hasBeenMoved = true;
            move.castling = false;
        } else {
            if(piece.getClass() == Rook.class) {
                Rook r = (Rook) piece;
                r.hasBeenMoved = true;
            } else if(piece.getClass() == King.class) {
                King k = (King) piece;
                k.hasBeenMoved = true;
            }
            piece.x = move.toX;
            piece.y = move.toY;
            this.board[move.toX][move.toY] = piece;
            this.board[move.fromX][move.fromY] = new Empty();
        }
    }

    /**
     * Set's a piece to a target square given by the move.
     */
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

    /**
     * Clones the board and pieces.
     */
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

    /**
     * Checks if there is an attack on king of the given color.
     */
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

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece p = this.getPiece(i, j);
                if(!p.empty && p.getColor() != color) {
                    ArrayList<Move> moves = p.generateAllLegalMoves(this);
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

    /**
     * Generates all possible moves and checks if these can be really performed so that they don't create an attack on king.
     */

    public ArrayList<Move> generateAllMoves(boolean color) {
        ArrayList<Move> ms = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(color == getPiece(i,j).color && !getPiece(i,j).empty) {

                    ArrayList<Move> moves = getPiece(i,j).generateAllLegalMoves(this);

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

    /**
     * Generates all seemingly legal moves. It doesn't check if move creates a check to save computation power.
     * If computer chooses a move that creates check it gets punished because it looses the king e.g a high value pice so
     * we don't need to check for this, which makes the move generation faster.
     * @return List of moves
     */
    public ArrayList<Move> generateAllPseudoLegalMoves(boolean color) {
        ArrayList<Move> ms = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(color == getPiece(i,j).color && !getPiece(i,j).empty) {

                    ArrayList<Move> moves = getPiece(i,j).generateAllLegalMoves(this);

                    for (Move move : moves) {
                            ms.add(move);
                    }
                }

            }
        }

        return ms;
    }

    /**
     * Moves the piece.
     */
    public boolean move(Move move) {
        if(move.isLegal()) {
            this.executeMove(move);
            return true;
        }
        return false;
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

}
