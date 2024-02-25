package game.cursor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public String getCursorPointedValue

    public void handleInput(String boardString) throws IOException {
        this.boardString = boardString;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char input = (char) reader.read();
        switch (input) {
            case 'w':
                move(0, -1); // up
                break;
            case 's':
                move(0, 1);  // down
                break;
            case 'd':
                move(1, 0);  // right
                break;
            case 'a':
                move(-1, 0); // left
                break;
            case '\n':

                break;
        }
    }
}
