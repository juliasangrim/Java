package ru.nsu.ccfit.trubitsyna.start;

import ru.nsu.ccfit.trubitsyna.controller.Controller;
import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.view.ViewGame;

public class Main {

    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        ViewGame viewGame = new ViewGame();
        Controller controller = new Controller(gameModel, viewGame);
        controller.execute();
    }
}
