package game;

import game.board.Board;
import game.board.Symbol;
import game.players.PlayerInterface;

import java.io.IOException;

public class Game {
    private final Board board;
    private final Cursor gameCursor;
    private PlayerManager playerManager;
    private PlayerInterface playerOne;
    private PlayerInterface playerTwo;

    public Game(){
        board = Board.getINSTANCE();
        gameCursor = Cursor.getINSTANCE(3, 3);
        playerManager = PlayerManager.getINSTANCE();
    }

    public void gameInit() throws IOException {
        playerManager.selectPlayers();
    }

    public void resetGame(){

    }

    private boolean isGameOver(){
        return board.isFull() || board.getWinner() != Symbol.EMPTY;
    }

    private void printGame(){
        board.printBoard(gameCursor.getRow(), gameCursor.getCol()); //TODO: call turn manager printTurn function
    }

    public void start() throws IOException {
        resetGame();
        System.out.println();
        while (!isGameOver()) { //TODO: refactor the printGame and gameCursor to gameCursor.handleInput and
            printGame(); //TODO: make enter place the symbol

            gameCursor.handleInput(); //Read keyboard input
        }
        System.out.println("Congratulations " + board.getWinner().getSymbolChar() + " won the game!!");
    }
}
