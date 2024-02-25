package game.cursor;

import java.io.IOException;
import java.util.Optional;

public interface CursorInterface {
    void move(int dx, int dy);

    Optional<Character> handleInput(String boardString) throws IOException;
}
