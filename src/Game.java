/**
 * Created by alecsiikaluoma on 13.9.2018.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Game {

    /**
     *  {@VALUE} value is TRUE when user playing white otherwise FALSE.
     *  */
    private boolean player;

    // Game result
    enum Result {
        WHITE, BLACK, DRAW;
    }
    private Result winner;

    // Board 8x8
    int[][] board = new int[8][8];

    // Store white and black moves here.
    List<List> whiteMoves;
    List<List> blackMoves;

    /**
     *
     * @param white True when player is white and false if black.
     */
    public Game(boolean white) {
        player = white;
        whiteMoves = new ArrayList<>();
        blackMoves = new ArrayList<>();

        int[][] startingPosition = {{-4, -2, -3, -5, -6, -3, -2, -4},
                                    {-1, -1, -1, -1, -1, -1, -1, -1},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 2, 3, 5, 6, 3, 2, 4}};
        board = startingPosition;
    }

    /**
     * @param depth Value of search depth.
     * @return Move in list format.
     */
    private List<Integer> calculateBestMove(int depth) {

        List<List<Integer>> moves = generateAllPossibleMoves(this.board, !player);
        int bestValue = Integer.MIN_VALUE;
        List<Integer> bestMove = new ArrayList<>();

        for (int i = 0; i < moves.size(); i++) {
            int[][] newPosition = move(this.board,moves.get(i));
            int value = minimax(this.board, depth - 1, !player, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if(value >= bestValue) {
                bestValue = value;
                bestMove = moves.get(i);
            }

        }

        return bestMove;
    }

    /**
     * Recursive move valuation method.
     * @param position Contains the position for which the value is calculated.
     * @param depth Search tree depth.
     * @param player True if white, false if depth.
     * @param alpha Value used in pruning the tree.
     * @param beta Value used in pruning the tree.
     * @return The value of the move.
     */
    private int minimax(int[][] position, int depth, boolean player, int alpha, int beta) {

        if(depth == 0) {
            return evaluateBoard(position);
        }

        if(player) {
            int maxVal = Integer.MAX_VALUE;
            int maxValIndex = 0;
            List<List<Integer>> moves = generateAllPossibleMoves(position, player);
            for(int i = 0; i < moves.size(); i++) {
                int[][] nextStep = move(position, moves.get(i));
                int bestMove = Math.max(maxVal, minimax(nextStep, depth - 1, !player, alpha, beta));
                maxVal = bestMove;
                maxValIndex = i;
                alpha = Math.max(alpha, bestMove);
                if(beta <= alpha) {
                    break;
                }
            }
            return maxVal;
        } else {
            int minVal = Integer.MIN_VALUE;
            List<List<Integer>> moves = generateAllPossibleMoves(position, !player);
            for(int i = 0; i < moves.size(); i++) {
                int[][] nextStep = move(position, moves.get(i));
                int bestMove = Math.min(minVal, minimax(nextStep, depth - 1, !player, alpha, beta));
                minVal = bestMove;
                beta = Math.min(beta, bestMove);
                if(beta <= alpha) {
                    break;
                }
            }
            return minVal;
        }

    }

    /**
     * Generates all possible moves for a given player and given position.
     * @param position Contains the position for which the value is calculated.
     * @param player True if white, false if depth.
     * @return List of possible moves, which each of them is in a list form.
     */
    private List<List<Integer>> generateAllPossibleMoves(int[][] position, boolean player) {

        List<List<Integer>> moves = new ArrayList<>();

        //TODO
        return moves;
    }



    private int[][] move(int[][] board, List<Integer> move) {
        //TODO
        int[][] newBoard = board;
        return newBoard;
    }


    /**
     * Simple evaluation method for calculating position value.
     * TODO: take in account the relative value between each square and piece.
     */
    private int evaluateBoard(int[][] position) {

        int score = 0;

        for (int i = 0; i < position.length; i++) {
            for(int j = 0; j < position[i].length; j++) {
                switch (position[i][j]) {
                    case 0: break;
                    case -1:
                        score -= 1;
                        break;
                    case 1:
                        score += 1;
                        break;
                    case 2:
                    case 3:
                        score += 3;
                        break;
                    case -2:
                    case -3:
                        score -= 3;
                        break;
                    case 4:
                        score += 5;
                        break;
                    case -4:
                        score -= 5;
                        break;
                    case 5:
                        score += 9;
                        break;
                    case -5:
                        score -= 9;
                        break;
                    case 6:
                        score += 100000;
                        break;
                    case -6:
                        score -= 100000;
                        break;
                    default:
                        break;
                }
            }
        }

        return score;
    }

    private int getPiece(List<Integer> square) {
        return board[square.get(0)][square.get(1)];
    }


    /**
     * Executes best possible move for the opponent.
     */
    public void computerMove() {
    }

    /*private List<> generateMovesForPiece(Square from, Square to) {

        List

        switch (from.piece) {
            case SOLDIER:
                if(true) {

                }
                break;

        }
    }*/


    /**
     * Checks if this position is in checkMate.
     */
    public boolean isMate() {
        //TODO
        return false;
    }

    /**
     * Gets the winner.
     * @return Winner in result enum form.
     */
    public Result getWinner() {
        return winner;
    }


}
