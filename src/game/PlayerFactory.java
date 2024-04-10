package game;

import game.board.Symbol;
import game.players.Ai;
import game.players.HumanPlayer;
import game.players.PlayerInterface;
import game.players.RandomComputer;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {
    private final Map<Character, Class<? extends PlayerInterface>> playerTypes;

    public PlayerFactory() {
        playerTypes = new HashMap<>();
        playerTypes.put(HumanPlayer.getName().charAt(0), HumanPlayer.class);
        playerTypes.put(RandomComputer.getName().charAt(0), RandomComputer.class);
        playerTypes.put(Ai.getName().charAt(0), Ai.class);
    }

    public PlayerInterface playerStringToInterface(char playerType, Symbol playerSymbol) {
        Class<? extends PlayerInterface> playerClass = playerTypes.get(playerType);
        if (playerClass != null) {
            try {
                return playerClass.getConstructor(Symbol.class).newInstance(playerSymbol);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create player", e);
            }
        } else {
            throw new IllegalArgumentException("Not a valid player type: " + playerType);
        }
    }
}
