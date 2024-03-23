package game.board;

import java.util.Arrays;

public class Board {
    private static Board INSTANCE;
    private final Symbol[][] board = new Symbol[3][3];

    private Board() {
        resetBoard();
    }

    public static Board getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Board();
        }
        return INSTANCE;
    }

    public void resetBoard() {
        Arrays.stream(board).forEach(row -> Arrays.fill(row, Symbol.EMPTY));
    }

    public Symbol[][] getBoard() {
        return board;
    }

    public void setBoardSymbol(BoardCoordinates coordinates, Symbol symbol) {
        board[coordinates.x()][coordinates.y()] = symbol;
    }

    public boolean isFull() {
        for (Symbol[] row : board) {
            for (Symbol symbol : row) {
                if (symbol == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public Symbol getWinner() {
        for (int i = 0; i < 3; i++) {
            if (isRow(i, Symbol.X))
                return Symbol.X;
            if (isRow(i, Symbol.O))
                return Symbol.O;
            if (isColumn(i, Symbol.X))
                return Symbol.X;
            if (isColumn(i, Symbol.O))
                return Symbol.O;
        }

        if (isDiagonal(Symbol.X))
            return Symbol.X;
        if (isDiagonal(Symbol.O))
            return Symbol.O;
        if (isDraw()){
            return Symbol.EMPTY;
        }
        return null;
    }

    private boolean isRow(int row, Symbol symbol) {
        return board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol;
    }

    private boolean isColumn(int col, Symbol symbol) {
        return board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol;
    }

    private boolean isDiagonal(Symbol symbol) {
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private boolean isDraw(){
        for (Symbol[] row : board) {
            for (Symbol symbol : row) {
                if (symbol == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getBoard(int cursorRow, int cursorCol) {
        StringBuilder boardString = new StringBuilder("----------------\n");
        for (int i = 0; i < board.length; i++) {
            boardString.append('|');
            for (int j = 0; j < board[i].length; j++) {
                if (i == cursorRow && j == cursorCol) {
                    boardString.append(" >");
                } else {
                    boardString.append("  ");
                }
                boardString.append(board[i][j].getSymbolChar()).append(" |");
            }
            boardString.append('\n');
            if (i < board.length - 1) {
                boardString.append("****************\n");
            }
        }
        boardString.append("----------------\n");
        return boardString.toString();
    }

    public int getRowNumber(){
        return board.length;
    }

    public int getColumnNumber(){
        return board[0].length;
    }
}
