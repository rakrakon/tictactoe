package game.players;

public interface PlayerInterface {
    void playTurn();
    static String getName(){
        return "";
    }

    Symbol getPlayerSymbol();
}
