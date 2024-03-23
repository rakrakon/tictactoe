package game;

import game.board.Symbol;
import game.players.PlayerInterface;

public class TurnManager {
    private static TurnManager INSTANCE;
    private final PlayerInterface playerOne;
    private final PlayerInterface playerTwo;
    private PlayerInterface currentPlayer;

    private TurnManager(PlayerInterface playerOne, PlayerInterface playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = playerOne;
    }

    public static synchronized TurnManager getInstance(PlayerInterface playerOne, PlayerInterface playerTwo) {
        if (INSTANCE == null){
            INSTANCE = new TurnManager(playerOne, playerTwo);
        }
        return INSTANCE;
    }

    public void playTurn() {
        printTurn(); // Print whose turn it is
        currentPlayer.playTurn(); // Let the current player play their turn
        switchPlayer(); // Switch to the next player
    }

    public Symbol getCurrentPlayerSymbol(){
        return currentPlayer == playerOne ? Symbol.X : Symbol.O;
    }

    private void switchPlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    private void printTurn(){
        System.out.println("It's " + currentPlayer.toString() + "'s turn.");
    }
}
