package game.players;

import game.board.Board;
import game.board.BoardCoordinates;
import game.board.Symbol;
import game.cursor.Cursor;

import java.io.IOException;

public class Player implements PlayerInterface {
    private final Symbol playerType;

    public Player(Symbol playerType) {
        this.playerType = playerType;
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
                    board.setBoardSymbol(new BoardCoordinates(gameCursor.getRow(), gameCursor.getCol()), playerType); //Place correct symbol on the board
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
        return playerType;
    }
}
