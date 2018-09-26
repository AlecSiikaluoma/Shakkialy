package main.pieces;

import main.game.Board;
import main.game.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class Pawn extends Piece {

    public boolean neverMoved;

    public Pawn(boolean color, int x, int y) {
        super(color, x, y);
        this.neverMoved = true;
        this.value = 1;
    }

    public Pawn(Pawn o) {
        super(o.color, o.x, o.y);
        this.value = o.value;
        this.neverMoved = o.neverMoved;
    }

    public List<Move> generateAllLegalMoves(Board board) {

        List<Move> moves = new ArrayList<>();

        //Player is WHITE.
        if(this.color) {
            // 1 step
            Move move = createMove(board, x - 1, y);
            if(move.isInsideBoard() && board.getPiece(x-1,y).empty) {
                if(move.toX == 0) {
                    moves.addAll(possiblePromotions(board, this.color, x - 1, y));
                } else {
                    moves.add(move);
                }
            }
            move = createMove(board, x - 1, y + 1);
            if(move.isInsideBoard() && !this.hasSameColor(board, move) && !board.getPiece(move).empty) {
                if(move.toX == 0) {
                    moves.addAll(possiblePromotions(board, this.color, x - 1, y + 1));
                } else {
                    moves.add(move);
                }
            }
            move = createMove(board, x - 1, y - 1);
            if(move.isInsideBoard() && !this.hasSameColor(board, move) && !board.getPiece(move).empty) {
                if(move.toX == 0) {
                    moves.addAll(possiblePromotions(board, this.color, x - 1, y - 1));
                } else {
                    moves.add(move);
                }
            }
            // 2 step
            if(this.neverMoved) {
                move = createMove(board, x - 2, y);
                if (move.isInsideBoard()) {
                    // Check it doesnt have a piece in front of it.
                    if (board.getPiece(this.x - 1, this.y).getClass() == Empty.class) {
                        if (board.getPiece(move).empty) {
                            moves.add(move);
                        }
                    }
                }
            }

            // En passant
            if(this.x == 3 && !board.blackMoves.isEmpty()) {
                Move blacksLastMove = board.blackMoves.get(board.blackMoves.size()-1);
                if(blacksLastMove.piece.getClass() == Pawn.class
                        && blacksLastMove.toX - blacksLastMove.fromX == 2
                        && Math.abs(this.y - blacksLastMove.toY) == 1
                        && board.getPiece(blacksLastMove.toX - 1,blacksLastMove.toY).empty) {
                    Move enPassant = null;
                    if(blacksLastMove.toY > y) {
                        enPassant = createMove(board, x - 1, y + 1);
                        enPassant.enPassant = true;
                    } else {
                        enPassant = createMove(board, x - 1, y - 1);
                        enPassant.enPassant = true;
                    }
                    moves.add(enPassant);
                }
            }

        } else {
            // 1 step
            Move move = createMove(board, x + 1, y);
            if(move.isInsideBoard() && board.getPiece(move).empty) {
                if(move.toX == 7) {
                    moves.addAll(possiblePromotions(board, this.color, x + 1, y));
                } else {
                    moves.add(move);
                }
            }
            move = createMove(board, x + 1, y + 1);
            if(move.isInsideBoard() && !this.hasSameColor(board, move) && !board.getPiece(move).empty) {
                if(move.toX == 7) {
                    moves.addAll(possiblePromotions(board, this.color, x + 1, y + 1));
                } else {
                    moves.add(move);
                }
            }
            move = createMove(board, x + 1, y - 1);
            if(move.isInsideBoard() && !this.hasSameColor(board, move) && !board.getPiece(move).empty) {
                if(move.toX == 7) {
                    moves.addAll(possiblePromotions(board, this.color, x + 1, y + - 1));
                } else {
                    moves.add(move);
                }
            }
            // 2 step
            if(this.neverMoved) {
                move = createMove(board, x + 2, y);
                if (move.isInsideBoard()) {
                    // Check it doesnt have a piece in front of it.
                    if (board.getPiece(this.x + 1, this.y).getClass() == Empty.class) {
                        if (board.getPiece(move).empty) {
                            moves.add(move);
                        }
                    }
                }
            }

            // En passant
            if(this.x == 4 && !board.whiteMoves.isEmpty()) {
                Move whitesLastMove = board.whiteMoves.get(board.whiteMoves.size()-1);
                if(whitesLastMove.piece.getClass() == Pawn.class
                        && whitesLastMove.fromX - whitesLastMove.toX == 2
                        && Math.abs(this.y - whitesLastMove.toY) == 1
                        && board.getPiece(whitesLastMove.toX + 1,whitesLastMove.toY).empty) {
                    Move enPassant = null;
                    if(whitesLastMove.toY > y) {
                        enPassant = createMove(board, x + 1, y + 1);
                        enPassant.enPassant = true;
                    } else {
                        enPassant = createMove(board, x + 1, y - 1);
                        enPassant.enPassant = true;
                    }
                    moves.add(enPassant);
                }
            }

        }

        return moves;
    }

    private List<Move> possiblePromotions(Board board, boolean color, int x, int y) {

        List<Move> moves = new ArrayList<>();

        Move prom = createMove(board, x, y);
        prom.promotion = new Knight(color, x, y);
        moves.add(prom);

        prom = createMove(board, x, y);
        prom.promotion = new Bishop(color, x, y);
        moves.add(prom);

        prom = createMove(board, x, y);
        prom.promotion = new Rook(color, x, y);
        moves.add(prom);

        prom = createMove(board, x, y);
        prom.promotion = new Queen(color, x, y);
        moves.add(prom);

        return moves;

    }

    public boolean isEnPassant(Move target, Board board) {

        Move move;

        if (!color) {
            if (this.x == 4 && !board.whiteMoves.isEmpty()) {
                Move whitesLastMove = board.whiteMoves.get(board.whiteMoves.size() - 1);
                if (whitesLastMove.piece.getClass() == Pawn.class
                        && whitesLastMove.fromX - whitesLastMove.toX == 2
                        && Math.abs(this.y - whitesLastMove.toY) == 1
                        && board.getPiece(whitesLastMove.toX + 1, whitesLastMove.toY).empty) {
                    if(whitesLastMove.toY > y) {
                        move = createMove(board, x + 1, y + 1);
                        move.enPassant = true;
                    } else {
                        move = createMove(board, x + 1, y - 1);
                        move.enPassant = true;
                    }
                    if(move.fromX == target.fromX && move.fromY == target.fromY && move.toX == target.toX && move.toY == target.toY) {
                        return true;
                    }
                }
            }
        } else {
            if (this.x == 3 && !board.blackMoves.isEmpty()) {
                Move blacksLastMove = board.blackMoves.get(board.blackMoves.size() - 1);
                if (blacksLastMove.piece.getClass() == Pawn.class
                        && blacksLastMove.toX - blacksLastMove.fromX == 2
                        && Math.abs(this.y - blacksLastMove.toY) == 1) {
                    if(blacksLastMove.toY > y) {
                        move = createMove(board, x - 1, y + 1);
                        move.enPassant = true;
                    } else {
                        move = createMove(board, x - 1, y - 1);
                        move.enPassant = true;
                    }
                    if(move.fromX == target.fromX && move.fromY == target.fromY && move.toX == target.toX && move.toY == target.toY) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


}
