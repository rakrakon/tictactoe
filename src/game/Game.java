package game;

import game.board.Board;
import game.players.PlayerInterface;

import java.io.IOException;

public class Game {
    private final Board board = new Board();
    private final Cursor cursor = new Cursor(3, 3);
    private PlayerInterface playerOne;
    private PlayerInterface playerTwo;

    public void resetGame(){

    }

    private boolean isGameOver(){ //TODO: implement logic
        return false;
    }

    private void printGame(){
        board.printBoard(cursor.getRow(), cursor.getCol()); //TODO: call turn manager printTurn function
    }

    void start() throws IOException {
        System.out.println();
        while (!isGameOver()) {


            cursor.handleInput(); //Read keyboard input
        }
    }
}
