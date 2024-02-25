import game.Game;
import game.PlayerManager;

import java.io.IOException;

public class Main {
    private static Game game;
    public static void main(String[] args) throws IOException { //TODO: create game manager class
        game = new Game();
        game.start();
    }
}