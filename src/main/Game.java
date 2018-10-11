package main;
/**
 * Created by alecsiikaluoma on 13.9.2018.
 * Represents the logic behind the game.
 */

import main.game.Board;
import main.pieces.Piece;
import main.game.Move;
import main.data.structures.ArrayList;

public class Game {

    private boolean player;

    /**
     * {@value} Depth of the search in minimax.
     */
    public int depth;

    /**
     * {@value} Current board containing the position of the game.
     */
    public Board board;

    enum State {
        WHITE, BLACK, DRAW, UNFINISHED;
    }
    private State winner;


    /**
     * Creates a new game.
     */
    public Game(boolean white) {
        player = white;
        this.board = new Board();
        this.winner = State.UNFINISHED;
        depth = 4;
    }

    /**
     * Calculates the best possible move.
     * @return Best move according to the algorithm.
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
        Move m = calculateBestMove(depth);
        if(m != null) {
            board.executeMove(m);
        }
    }

    /**
     * Executes best possible move for the opponent when two computers playing
     * @param color For which color the move is to be performed.
     */
    public void computerMove(boolean color) {
        Move m = null;
        if(color == player) {
            this.player = !this.player;
            m = calculateBestMove(depth);
            this.player = !this.player;
        } else {
            m = calculateBestMove(depth);
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
