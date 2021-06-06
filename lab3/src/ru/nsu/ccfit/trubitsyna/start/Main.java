package ru.nsu.ccfit.trubitsyna.start;

import ru.nsu.ccfit.trubitsyna.controller.KeyController;
import ru.nsu.ccfit.trubitsyna.controller.MouseController;
import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.view.ViewGame;

public class Main {

    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        ViewGame viewGame = new ViewGame();
        KeyController controller = new KeyController(gameModel, viewGame);
        MouseController controller1 = new MouseController(gameModel, viewGame);
        controller.execute();
    }
}
