package game.board;

public enum Symbol {
    EMPTY('?'),
    X('X'),
    O('○');

    private final char symbolChar;

    Symbol(char symbolChar) {
        this.symbolChar = symbolChar;
    }

    public char getSymbolChar() {
        return symbolChar;
    }
}