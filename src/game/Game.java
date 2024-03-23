package game;

import game.board.Board;
import game.board.Symbol;
import game.cursor.Cursor;
import game.players.Player;
import game.players.PlayerInterface;
import game.players.RandomComputer;

import java.io.IOException;

public class Game {
    private final Board board;
    private Cursor gameCursor;
    private TurnManager turnManager;

    public Game() {
        board = Board.getINSTANCE();
    }

    private PlayerInterface playerStringToInterface(char playerType, Symbol playerSymbol) {
        if (Player.getName().charAt(0) == playerType) {
            return new Player(playerSymbol);
        } else if (RandomComputer.getName().charAt(0) == playerType) {
            return new RandomComputer(playerSymbol);
        } else {
            throw new RuntimeException("Not a valid player type");
        }
    }

    private void init() throws IOException {
        PlayerSelectionMenu playerSelectionMenu = PlayerSelectionMenu.getINSTANCE();
        char[] selectedPlayers = playerSelectionMenu.selectPlayers();
        turnManager = TurnManager.getInstance(
                playerStringToInterface(selectedPlayers[0], Symbol.X),
                playerStringToInterface(selectedPlayers[1], Symbol.O)
        );
        gameCursor = Cursor.getINSTANCE2(3, 3);
    }

    public void newGame() throws IOException {
        init();
        start();
    }

    private boolean isGameOver(){
        return board.isFull() || board.getWinner() != Symbol.EMPTY;
    }

    private void printGame(){
        System.out.println(board.getBoard(gameCursor.getRow(), gameCursor.getCol()));
    }

    public void start() throws IOException {
        resetGame();
        System.out.println();
        while (!isGameOver()) {
            printGame();
            var cursorValue = gameCursor.handleInput(board.getBoard(gameCursor.getRow(), gameCursor.getCol())); //Read keyboard input
            if(cursorValue.isPresent()){ //TODO: maybe this needs to be in the player interface? and here the turnManager.playTurn will be called
                board.setBoardSymbol(new BoardCoordinates(gameCursor.getRow(), gameCursor.getCol()), turnManager.getCurrentPlayerSymbol()); //Place correct symbol on the board
            }
        }
        System.out.println("Congratulations " + board.getWinner().getSymbolChar() + " won the game!!");
    }
}
