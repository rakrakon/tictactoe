package game;

import game.board.Board;
import game.board.Symbol;
import game.cursor.Cursor;
import game.players.Ai;
import game.players.HumanPlayer;
import game.players.PlayerInterface;
import game.players.RandomComputer;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private final Board board;
    private Cursor gameCursor;
    private TurnManager turnManager;

    public Game() {
        board = Board.getINSTANCE();
    }

    private PlayerInterface playerStringToInterface(char playerType, Symbol playerSymbol) {
        if (HumanPlayer.getName().charAt(0) == playerType) {
            return new HumanPlayer(playerSymbol);
        } else if (RandomComputer.getName().charAt(0) == playerType) {
            return new RandomComputer(playerSymbol);
        } else if (Ai.getName().charAt(0) == playerType){
            return new Ai(playerSymbol);
        }
        else {
            throw new RuntimeException("Not a valid player type");
        }
    }

    private void printWelcomeScreen() {
        System.out.println("Welcome to TicTacToe game");
        System.out.println("You can move the cursor by typing ASWD followed by enter");
        System.out.println("If you only press enter the menu will select the item\n");
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
        Scanner scanner = new Scanner(System.in);
        printWelcomeScreen();
        init();
        start();
        while (true) {
            System.out.println("Play again?(y/n): ");
            char input = scanner.next().toLowerCase().charAt(0);
            if (input == 'n') {
                System.out.println("Thank you for playing\nGoodbye!");
                break;
            }
            System.out.println("Starting new game...");
            board.resetBoard();
            init();
            start();
        }
        scanner.close();
    }

    private boolean isGameOver() {
        return board.isFull() || board.getWinner() != null;
    }

    private void printGame() {
        System.out.println(board.getBoard(gameCursor.getRow(), gameCursor.getCol()));
    }

    private void start() throws IOException {
        System.out.println();
        while (!isGameOver()) {
            turnManager.playTurn();
        }
        printGame();
        Symbol winner = board.getWinner();
        if (winner == Symbol.EMPTY) {
            System.out.println("No one won it is a tie");
        }
        System.out.println("Congratulations " + winner.getSymbolChar() + " won the game!!");
    }
}
