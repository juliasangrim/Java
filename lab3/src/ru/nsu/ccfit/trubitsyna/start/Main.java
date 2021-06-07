package ru.nsu.ccfit.trubitsyna.start;

import ru.nsu.ccfit.trubitsyna.controller.KeyController;
import ru.nsu.ccfit.trubitsyna.controller.MouseController;
import ru.nsu.ccfit.trubitsyna.gameException.GameException;
import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.view.ViewGame;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        ViewGame viewGame = new ViewGame();
        KeyController controller;
        MouseController controllerMouse;
        try {
            controller = new KeyController(gameModel, viewGame);
            controllerMouse = new MouseController(gameModel, viewGame);
            controller.execute();
        } catch (IOException | GameException e) {
            e.printStackTrace();
        }
    }
}
