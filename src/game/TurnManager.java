package game;

import game.players.PlayerInterface;

import java.io.IOException;

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

    public static TurnManager getInstance(PlayerInterface playerOne, PlayerInterface playerTwo) {
        if (INSTANCE == null) {
            INSTANCE = new TurnManager(playerOne, playerTwo);
        }
        return INSTANCE;
    }

    public void playTurn() throws IOException {
        printTurn(); // Print whose turn it is
        currentPlayer.playTurn(); // Let the current player play their turn
        switchPlayer(); // Switch to the next player
    }

    private void switchPlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    private void printTurn() {
        System.out.println("It's " + currentPlayer.getPlayerSymbol().getSymbolChar() + "'s turn.");
    }
}
