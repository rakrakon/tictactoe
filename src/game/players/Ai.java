package game.players;

import game.board.Board;
import game.board.BoardCoordinates;
import game.board.Symbol;
import game.cursor.Cursor;

public class Ai implements PlayerInterface{
    private final Symbol playerSymbol;
    private Board board;

    public Ai(Symbol playerSymbol){
        this.playerSymbol = playerSymbol;
    }

    private int evaluateBoard(){
        if(board.getWinner().equals(Symbol.EMPTY)){
            return 0;
        } else if (board.getWinner().equals(playerSymbol)) {
            return 10;
        }
        return -10;
    }

    @Override
    public void playTurn() { // minimax and alpha-beta pruning algorithm
        Cursor gameCursor = Cursor.getINSTANCE2();
        board = Board.getINSTANCE();
        System.out.println(board.getBoard(gameCursor.getRow(), gameCursor.getCol()));
        int[] bestMove = minimax(board, playerSymbol);
        int row = bestMove[0];
        int col = bestMove[1];
        board.setSymbol(new BoardCoordinates(row, col), playerSymbol);
    }

    private int[] minimax(Board board, Symbol playerSymbol) { // TODO: implement alpha-beta pruning
        int[] bestMove = new int[]{-1, -1}; // init best move with invalid values
        int bestScore = (playerSymbol == this.playerSymbol) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // check if the game is over
        if (board.getWinner() != null) {
            int score = evaluateBoard(); // evaluate the score of the position
            return new int[]{score, -1, -1}; // return the score with no moves
        }

        for (int row = 0; row < board.getRowNumber(); row++) {
            for (int col = 0; col < board.getColumnNumber(); col++) {
                BoardCoordinates coordinates = new BoardCoordinates(row, col);
                if (board.getSymbol(coordinates) == Symbol.EMPTY) { // check if it is a valid placement
                    board.setSymbol(coordinates, playerSymbol); // make the move

                    // recursively call minimax to evaluate other player response
                    int[] result = minimax(board, (playerSymbol == Symbol.X) ? Symbol.O : Symbol.X);
                    int score = result[0];

                    // undo the move
                    board.setSymbol(coordinates, Symbol.EMPTY);

                    // update the best move and best score based on player turn
                    if ((playerSymbol == this.playerSymbol && score > bestScore) ||
                            (playerSymbol != this.playerSymbol && score < bestScore)) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        return (playerSymbol == this.playerSymbol) ? bestMove : new int[]{bestScore, bestMove[0], bestMove[1]};
    }


    @Override
    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public static String getName(){
        return "AI";
    }
}
