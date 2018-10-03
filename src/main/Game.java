package main; /**
 * Created by alecsiikaluoma on 13.9.2018.
 */

import main.game.Board;
import main.pieces.Piece;
import main.game.Move;
import main.data.structures.ArrayList;

public class Game {

    /**
     *  {@VALUE} value is TRUE when user playing white otherwise FALSE.
     *  */
    private boolean player;

    public Board board;

    // main.Game result
    enum State {
        WHITE, BLACK, DRAW, UNFINISHED;
    }

    private State winner;


    public Game(boolean white) {
        player = white;
        this.board = new Board();
        this.winner = State.UNFINISHED;
    }

    /**
     * @param depth Value of search depth.
     * @return Move in list format.
     */
    private Move calculateBestMove(int depth) {

        if(this.player) {
            ArrayList<Move> moves = board.generateAllPseudoLegalMoves(!player);
            if(moves.isEmpty()) {
                return null;
            }
            int bestValue = 1000000;
            Move bestMove = null;

            for (int i = 0; i < moves.size(); i++) {
                Board newPosition = board.clone();
                newPosition.executeMove(moves.get(i));
                if(newPosition.existAttackOnKing(!player)) {
                    continue;
                }
                int value = minimax(newPosition, depth - 1, player, -1000000, 1000000);
                if (value <= bestValue) {
                    bestValue = value;
                    bestMove = moves.get(i);
                }
            }
            return bestMove;
        } else {
            ArrayList<Move> moves = board.generateAllPseudoLegalMoves(!player);
            if(moves.isEmpty()) {
                return null;
            }
            int bestValue = -1000000;
            Move bestMove = null;

            for (int i = 0; i < moves.size(); i++) {
                Board newPosition = board.clone();
                newPosition.executeMove(moves.get(i));
                if(newPosition.existAttackOnKing(!player)) {
                    continue;
                }
                int value = minimax(newPosition, depth - 1, player, -1000000, 1000000);
                if (value >= bestValue) {
                    bestValue = value;
                    bestMove = moves.get(i);
                }
            }

            return bestMove;
        }
    }

    /**
     * Recursive move valuation method.
     * @param board Contains the position for which the value is calculated.
     * @param depth Search tree depth.
     * @param player True if white, false if depth.
     * @param alpha Value used in pruning the tree.
     * @param beta Value used in pruning the tree.
     * @return The value of the move.
     */
    private int minimax(Board board, int depth, boolean player, int alpha, int beta) {

        if(depth == 0) {
            return evaluateBoard(board);
        }

        if(player) {
            int maxVal = -1000000;
            ArrayList<Move> moves = board.generateAllPseudoLegalMoves(player);
            for(int i = 0; i < moves.size(); i++) {
                Board newBoard = board.clone();
                newBoard.executeMove(moves.get(i));
                if(newBoard.existAttackOnKing(player)) {
                    continue;
                }
                maxVal = Math.max(maxVal, minimax(newBoard, depth - 1, !player, alpha, beta));
                alpha = Math.max(alpha, maxVal);
                if(beta <= alpha) {
                    break;
                }
            }
            return maxVal;
        } else {
            int minVal = 1000000;
            ArrayList<Move> moves = board.generateAllPseudoLegalMoves(player);
            for(int i = 0; i < moves.size(); i++) {
                Board newBoard = board.clone();
                newBoard.executeMove(moves.get(i));
                if(newBoard.existAttackOnKing(player)) {
                    continue;
                }
                minVal = Math.min(minVal, minimax(newBoard, depth - 1, !player, alpha, beta));
                beta = Math.min(beta, minVal);
                if(beta <= alpha) {
                    break;
                }
            }
            return minVal;
        }

    }

    /**
     * Simple evaluation method for calculating position value.
     * TODO: take in account the relative value between each square and piece.
     */
    private int evaluateBoard(Board board) {

        Piece[][] position = board.getBoard();

        int score = 0;

        for (int i = 0; i < position.length; i++) {
            for(int j = 0; j < position[i].length; j++) {
                if(board.getPiece(i,j).empty) {
                    continue;
                }
                if(board.getPiece(i,j).color) {
                    score += board.getPiece(i, j).value;
                } else {
                    score -= board.getPiece(i,j).value;
                }
            }
        }

        return score;
    }


    /**
     * Executes best possible move for the opponent.
     */
    public void computerMove() {
        Move m = calculateBestMove(4);
        if(m != null) {
            board.executeMove(m);
        }
    }

    /**
     * Executes best possible move for the opponent.
     */
    public void computerMove(boolean color) {
        Move m = null;
        if(color == player) {
            this.player = !this.player;
            m = calculateBestMove(4);
            this.player = !this.player;
        } else {
            m = calculateBestMove(4);
        }
        if(m != null) {
            board.executeMove(m);
        }
    }


    /**
     * Gets the winner.
     * @return Winner in result enum form.
     */
    public State getWinner() {
        return winner;
    }


    /**
     * Check if the main.game is in a mate position;
     */
    public boolean isMate(boolean color) {
        if(board.generateAllMoves(color).isEmpty()) {
            if(board.existAttackOnKing(color)) {
                if(color) {
                    this.winner = State.BLACK;
                } else {
                    this.winner = State.WHITE;
                }
                return true;
            } else {
                this.winner = State.DRAW;
                return true;
            }
        }
        return false;
    }

}
