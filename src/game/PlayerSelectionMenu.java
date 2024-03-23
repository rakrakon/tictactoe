package game;

import game.cursor.Cursor;
import game.players.Player;
import game.players.RandomComputer;

import java.io.IOException;

public class PlayerSelectionMenu {
    private static PlayerSelectionMenu INSTANCE;
    private final Cursor cursor;
    private final String[][] selectionMenu;


    private PlayerSelectionMenu() {
        selectionMenu = new String[][]{{Player.getName()}, {RandomComputer.getName()}};
        cursor = Cursor.getINSTANCE1(2, 1);
    }

    public static PlayerSelectionMenu getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerSelectionMenu();
        }
        return INSTANCE;
    }

    private String getSelectionMenu(int cursorRow, int cursorCol) {
        StringBuilder selectionMenuString = new StringBuilder("--------------------\n");
        for (int i = 0; i < selectionMenu.length; i++) {
            selectionMenuString.append('|');
            for (int j = 0; j < selectionMenu[i].length; j++) {
                if (i == cursorRow && j == cursorCol) {
                    selectionMenuString.append(" >");
                } else {
                    selectionMenuString.append("  ");
                }
                selectionMenuString.append(selectionMenu[i][j]).append(" |");
            }
            selectionMenuString.append('\n');
            if (i < selectionMenu.length - 1) {
                selectionMenuString.append("********************\n");
            }
        }
        selectionMenuString.append("--------------------\n");
        return selectionMenuString.toString();
    }

    private char selectPlayer() throws IOException {
        while (true) {
            System.out.println(getSelectionMenu(cursor.getRow(), cursor.getCol()));
            var cursorValue = cursor.handleInput(selectionMenu[cursor.getRow()][cursor.getCol()]);
            if (cursorValue.isPresent()) {
                return cursorValue.get();
            }
        }
    }

    public char[] selectPlayers() throws IOException {
        System.out.println("Please select player One: ");
        char playerOne = selectPlayer();
        System.out.println("Please select player Two: ");
        char playerTwo = selectPlayer();
        return new char[]{playerOne, playerTwo};
    }
}
