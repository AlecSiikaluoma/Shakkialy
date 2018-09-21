package main; /**
 * Created by alecsiikaluoma on 13.9.2018.
 */

import main.game.Board;
import main.pieces.Piece;
import main.game.Move;

import java.util.List;

public class Game {

    /**
     *  {@VALUE} value is TRUE when user playing white otherwise FALSE.
     *  */
    private boolean player;

    public Board board;

    // main.Game result
    enum Result {
        WHITE, BLACK, DRAW;
    }

    private Result winner;


    public Game(boolean white) {
        player = white;
        this.board = new Board();
    }

    /**
     * @param depth Value of search depth.
     * @return Move in list format.
     */
    private Move calculateBestMove(Board board, int depth) {

        if(this.player) {

            List<Move> moves = board.generateAllMoves(!player);
            int bestValue = Integer.MAX_VALUE;
            Move bestMove = moves.get(0);

            for (int i = 0; i < moves.size(); i++) {
                Board newPosition = board.clone();
                newPosition.executeMove(moves.get(i));
                int value = minimax(newPosition, depth - 1, player, Integer.MIN_VALUE, Integer.MAX_VALUE);
                //System.out.println(value);
                //System.out.println(value + " " + moves.get(i).piece.getClass() + " " + moves.get(i).toX + " - " + moves.get(i).toY);
                if (value <= bestValue) {
                    bestValue = value;
                    bestMove = moves.get(i);
                }
            }
            return bestMove;
        } else {
            List<Move> moves = board.generateAllMoves(player);
            int bestValue = Integer.MIN_VALUE;
            Move bestMove = moves.get(0);

            for (int i = 0; i < moves.size(); i++) {
                Board newPosition = board.clone();
                newPosition.executeMove(moves.get(i));
                int value = minimax(newPosition, depth - 1, !player, Integer.MIN_VALUE, Integer.MAX_VALUE);
                //System.out.println(value);
                //System.out.println(value + " " + moves.get(i).piece.getClass() + " " + moves.get(i).toX + " - " + moves.get(i).toY);
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
            int maxVal = Integer.MIN_VALUE;
            List<Move> moves = board.generateAllMoves(player);
            for(int i = 0; i < moves.size(); i++) {
                Board newBoard = board.clone();
                newBoard.executeMove(moves.get(i));
                int bestMove = Math.max(maxVal, minimax(newBoard, depth - 1, !player, alpha, beta));
                maxVal = bestMove;
                alpha = Math.max(alpha, bestMove);
                if(beta <= alpha) {
                    break;
                }
            }
            return maxVal;
        } else {
            int minVal = Integer.MAX_VALUE;
            List<Move> moves = board.generateAllMoves(!player);
            for(int i = 0; i < moves.size(); i++) {
                Board newBoard = board.clone();
                newBoard.executeMove(moves.get(i));
                int bestMove = Math.min(minVal, minimax(newBoard, depth - 1, player, alpha, beta));
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
                //} else if(!this.player && !board.getPiece(i,j).color) {
                    //score += board.getPiece(i,j).value;
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
    public void computerMove(Board board) {
        Move m = calculateBestMove(board, 5);

        board.executeMove(m);
    }


    /**
     * Gets the winner.
     * @return Winner in result enum form.
     */
    public Result getWinner() {
        return winner;
    }


    /**
     * Check if the main.game is in a mate position;
     * @param color true for white, false fo black
     * @return Boolean
     */
    public boolean isMate(boolean color) {
         if(this.board.generateAllMoves(color).isEmpty()) {
             if(color) {
                 this.winner = Result.BLACK;
             } else {
                 this.winner = Result.WHITE;
             }
             return true;
         }
        return false;
    }

}
