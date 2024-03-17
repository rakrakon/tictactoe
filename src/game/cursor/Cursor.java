package game.cursor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Cursor { //TODO: needs to be refactored into an interface and make this class the game implementation also create a playerSelection implementation
    private static Cursor INSTANCE;
    private int row;
    private int col;
    private final int numRows;
    private final int numCols;
    private String boardString = "";

    private Cursor(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        row = 0;
        col = 0;
    }

    public static Cursor getINSTANCE(int numRows, int numCols) {
        if (INSTANCE == null){
            INSTANCE = new Cursor(numRows, numCols);
        }
        return INSTANCE;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void move(int dx, int dy) {
        row = Math.max(0, Math.min(numRows - 1, row + dy));
        col = Math.max(0, Math.min(numCols - 1, col + dx));
    }

    /**
     * @return The value the the cursor is currently pointing on
     */
    public char getCursorPointedValue(){
        StringBuilder builder = new StringBuilder(boardString);
        return builder.charAt(builder.indexOf(">") + 1);
    }

    public Optional<Character> handleInput(String boardString) throws IOException {
        this.boardString = boardString;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char input = (char) reader.read();
        // up
        // down
        // right
        // left
        return switch (input) {
            case 'w' -> {
                move(0, -1); // up
                yield Optional.empty();
            }
            case 's' -> {
                move(0, 1);  // down
                yield Optional.empty();
            }
            case 'd' -> {
                move(1, 0);  // right
                yield Optional.empty();
            }
            case 'a' -> {
                move(-1, 0); // left
                yield Optional.empty();
            }
            case '\n' -> Optional.of(getCursorPointedValue());
            default -> Optional.empty();
        };
    }
}