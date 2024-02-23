package game;

import java.util.Scanner;

public class GameInputs {
    private static Scanner INSTANCE;

    public static Scanner getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new Scanner(System.in);
        }
        return INSTANCE;
    }
}
