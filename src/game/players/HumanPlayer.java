package game.players;

import game.board.Board;
import game.board.BoardCoordinates;
import game.board.Symbol;
import game.cursor.Cursor;

import java.io.IOException;

public class HumanPlayer implements PlayerInterface {
    private final Symbol playerSymbol;

    public HumanPlayer(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    @Override
    public void playTurn() throws IOException {
        Cursor gameCursor = Cursor.getINSTANCE2();
        Board board = Board.getINSTANCE();
        while (true) {
            System.out.println(board.getBoard(gameCursor.getRow(), gameCursor.getCol()));
            var cursorValue = gameCursor.handleInput(board.getBoard(gameCursor.getRow(), gameCursor.getCol())); //Read keyboard input
            if (cursorValue.isPresent()) {
                if (cursorValue.get().equals(Symbol.EMPTY.getSymbolChar())) {
                    board.setSymbol(new BoardCoordinates(gameCursor.getRow(), gameCursor.getCol()), playerSymbol); //Place correct symbol on the board
                    return;
                }
                System.out.println("There is already a symbol there try again:");
            }
        }
    }

    public static String getName() {
        return "Human Player";
    }

    @Override
    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }
}
