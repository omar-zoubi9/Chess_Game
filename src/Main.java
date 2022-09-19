import GameManaging.GameManager;
import InputManaging.InputManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager inputManager = new InputManager();
        GameManager gameManager = GameManager.getInstance();
        gameManager.initializeNewGame();
        gameManager.setPlayer1Name(inputManager.takePlayer1Name());
        gameManager.setPlayer2Name(inputManager.takePlayer2Name());
        gameManager.startGame();
    }
}