package game.players;

import game.board.Board;
import game.board.BoardCoordinates;
import game.board.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomComputer implements PlayerInterface{
    private final Random random = new Random();
    private final Symbol playerType;

    public RandomComputer(Symbol playerType){
        this.playerType = playerType;
    }

    @Override
    public void playTurn() {
        Board board = Board.getINSTANCE();
        Symbol[][] playingBoard = board.getBoard();
        // List to store array locations of empty symbols using Stream API
        List<int[]> emptyLocations = new ArrayList<>();

        // Using IntStream to iterate over the 2D array
        IntStream.range(0, playingBoard.length)
                .forEach(i -> IntStream.range(0, playingBoard[i].length)
                        .filter(j -> playingBoard[i][j] == Symbol.EMPTY) //find the indexes when the symbol is empty
                        .forEach(j -> emptyLocations.add(new int[]{i, j}))); //add the indexes to a list of int arrays

        int[] randCoordinates = emptyLocations.get(random.nextInt(emptyLocations.toArray().length));
        board.setBoardSymbol(new BoardCoordinates(randCoordinates[0], randCoordinates[1]), playerType);
        try {
            Thread.sleep(100);
        } catch (Exception ignored) {

        }
    }

    public static String getName() {
        return "Random Computer";
    }

    @Override
    public Symbol getPlayerSymbol() {
        return playerType;
    }
}
