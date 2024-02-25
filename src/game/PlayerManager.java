package game;

import game.cursor.GameCursor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private GameCursor selectingCursor; //TODO: create playerSelectingCursor
    private String selectedPlayerType;

    private PlayerManager(){
        selectingCursor = GameCursor.getINSTANCE(2, 1); // Adjusting the cursor dimensions to match the selection menu
        selectedPlayerType = ""; // Initializing selectedPlayerType
    }

    public static PlayerManager getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    public void reset() {
        // Reset the cursor position to the initial position (typically, top-left corner)
        selectingCursor = GameCursor.getINSTANCE(1, 2);

        // Clear the selected player type
        selectedPlayerType = "";
    }

    private void printSelectionMenu(int cursorRow, int cursorCol) {
        String[] playerTypes = {"player", "randomComputer"};

        StringBuilder menuString = new StringBuilder("--------------\n|");
        for (int i = 0; i < playerTypes.length; i++) {
            if (i == cursorCol) {
                menuString.append(" >").append(playerTypes[i]).append(" |");
            } else {
                menuString.append("  ").append(playerTypes[i]).append(" |");
            }
        }
        menuString.append('\n').append("--------------\n");
        System.out.println(menuString);
    }

    public void selectPlayers() throws IOException { //TODO: fix inputs
        // Print the selection menu initially
        printSelectionMenu(selectingCursor.getRow(), selectingCursor.getCol());

        // BufferedReader to read user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Loop until Enter key is pressed
        while (true) {
//            selectingCursor.handleInput(); //TODO: add enter key logic
        }
    }
}
