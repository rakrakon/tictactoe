import game.Game;
import game.PlayerSelectionMenu;

import java.io.IOException;

public class Main {
    private static Game game;
    public static void main(String[] args) throws IOException { //TODO: create game manager class
        PlayerSelectionMenu playerSelectionMenu = PlayerSelectionMenu.getINSTANCE();
        playerSelectionMenu.selectPlayers();
    }
}