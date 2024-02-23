package game.board;

import java.util.Arrays;


public class Board {
    private char[][] board = new char[3][3];


    public Board(){
        resetBoard();
    }

    public void resetBoard(){
        Arrays.stream(board).forEach(row -> Arrays.fill(row, Symbol.EMPTY.getSymbolChar()));
    }

    public char[][] getBoard(){
        return board;
    }

    public void setBoardSymbol(BoardCoordinates coordinates, Symbol symbol){
        board[coordinates.x()][coordinates.y()] = symbol.getSymbolChar();
    }

    public void printBoard(int cursorRow, int cursorCol) {
        StringBuilder boardString = new StringBuilder("----------------\n");
        for (int i = 0; i < board.length; i++) {
            boardString.append('|');
            for (int j = 0; j < board[i].length; j++) {
                if (i == cursorRow && j == cursorCol) {
                    boardString.append(" >");
                } else {
                    boardString.append("  ");
                }
                boardString.append(board[i][j]).append(" |");
            }
            boardString.append('\n');
            if (i < board.length - 1) {
                boardString.append("****************\n");
            }
        }
        boardString.append("----------------\n");
        System.out.println(boardString);
    }
}


