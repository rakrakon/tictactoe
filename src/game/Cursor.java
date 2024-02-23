package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cursor {
    private int row;
    private int col;
    private final int numRows;
    private final int numCols;

    public Cursor(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        row = 0;
        col = 0;
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

    public void handleInput() throws IOException {
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
        }
    }
}
