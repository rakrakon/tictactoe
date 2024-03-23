package game.players;

import game.board.Symbol;

import java.io.IOException;

public interface PlayerInterface {
    void playTurn() throws IOException;

    static String getName(){
        return "";
    }

    Symbol getPlayerSymbol();
}
