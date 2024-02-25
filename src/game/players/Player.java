package game.players;

import game.GameInputs;
import game.board.Symbol;

public class Player implements PlayerInterface{
    private final Symbol playerType;

    public Player(Symbol playerType){
        if(playerType == Symbol.EMPTY){
            throw new RuntimeException("Player can't be of type: ?");
        }
        this.playerType = playerType;
    }

    @Override
    public void playTurn() {
        char symbol = playerType.getSymbolChar();
        System.out.println("Please enter the coordinates (row and column) to place your mark.");
        System.out.println("Coordinates should be in the range 0-2, 0-2, starting from the top left corner.");
    }
}
