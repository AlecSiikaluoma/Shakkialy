package main.pieces;

import main.game.Board;
import main.game.Move;
import main.data.structures.ArrayList;

/**
 * Created by alecsiikaluoma on 18.9.2018.
 */
public class King extends Piece {

    public boolean hasBeenMoved = false;

    public King(boolean color, int x, int y) {
        super(color, x, y);
        this.value = 10000;
    }

    public King(King o) {
        super(o.color, o.x, o.y);
        this.value = o.value;
        this.hasBeenMoved = o.hasBeenMoved;
    }

    public ArrayList<Move> generateAllLegalMoves(Board board) {

        ArrayList<Move> moves = new ArrayList<>();

        // CASTLING
        if(this.color) {
            if(!this.hasBeenMoved) {
                // King side castle
                if(board.getPiece(7,7).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(7,7);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(7,6).empty && board.getPiece(7,5).empty) {
                            if(!kingsPathAttacked(board, true)) {
                                Move move = createMove(board, 7, 7);
                                move.castling = true;
                                moves.add(move);
                            }
                        }
                    }
                }
                //Queen side castle
                if(board.getPiece(7,0).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(7,0);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(7,1).empty && board.getPiece(7,2).empty && board.getPiece(7, 3).empty) {
                            if(!kingsPathAttacked(board, false)) {
                                Move move = createMove(board, 7, 0);
                                move.castling = true;
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        } else {
            if(!this.hasBeenMoved) {
                // King side castle
                if(board.getPiece(0,7).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(0,7);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(0,6).empty && board.getPiece(0,5).empty) {
                            if(!kingsPathAttacked(board, true)) {
                                Move move = createMove(board, 0, 7);
                                move.castling = true;
                                moves.add(move);
                            }
                        }
                    }
                }
                //Queen side castle
                if(board.getPiece(0,0).getClass() == Rook.class) {
                    Rook rook = (Rook) board.getPiece(0,0);
                    if(!rook.hasBeenMoved) {
                        if(board.getPiece(0,1).empty && board.getPiece(0,2).empty && board.getPiece(0, 3).empty) {
                            if(kingsPathAttacked(board, false)) {
                                Move move = createMove(board, 0, 0);
                                move.castling = true;
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }

        // These are the moves one step around King's positions.
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i==0 && j==0) continue;
                Move move = createMove(board, x + i, y + j);
                if(move.isInsideBoard() && !this.hasSameColor(board, move)) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    private boolean kingsPathAttacked(Board board, boolean kingSideCastle) {

        if(kingSideCastle) {
            if(color) {
                Board nb = board.clone();
                nb.setPiece(createMove(board, 7, 5));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 7, 6));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 7, 7));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
            } else {
                Board nb = board.clone();
                nb.setPiece(createMove(board, 0, 5));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 0, 6));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 0, 7));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
            }
        } else {
            if(color) {
                Board nb = board.clone();
                nb.setPiece(createMove(board, 7, 3));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 7, 2));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 7, 1));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 7, 0));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
            } else {
                Board nb = board.clone();
                nb.setPiece(createMove(board, 0, 3));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 0, 2));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 0, 1));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
                nb = board.clone();
                nb.setPiece(createMove(board, 0, 0));
                if(nb.existAttackOnKing(color)) {
                    return true;
                }
            }
        }

        return false;
    }

}
