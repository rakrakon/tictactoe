package game;

import game.board.Board;
import game.board.BoardCoordinates;
import game.board.Symbol;
import game.cursor.GameCursor;
import game.players.PlayerInterface;

import java.io.IOException;

public class Game {
    private final Board board;
    private final GameCursor gameCursor;
    private PlayerManager playerManager;
    private TurnManager turnManager;
    private PlayerInterface playerOne;
    private PlayerInterface playerTwo;

    public Game(){
        board = Board.getINSTANCE();
        gameCursor = GameCursor.getINSTANCE(3, 3);
        playerManager = PlayerManager.getINSTANCE();
    }

    public void gameInit() throws IOException {
        playerManager.selectPlayers();
//        turnManager = TurnManager.getInstance(); //TODO: implement logic
    }

    public void resetGame(){

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
