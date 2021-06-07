package ru.nsu.ccfit.trubitsyna.controller;

import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.view.ViewGame;
import ru.nsu.ccfit.trubitsyna.view.ViewState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener {
    private final GameModel model;
    private final ViewGame view;
    public MouseController(GameModel gameModel, ViewGame viewGame) {
            this.model = gameModel;
            this.view = viewGame;
            this.view.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (model.getGameState() == ViewState.MENU) {
            if (x >= 50 * 20 / 2 - 90 && x <= 50 * 20 / 2 + 110) {
                if (y >= 230 && y <= 300) {
                    String name = view.getUserName();
                    if (name == null) {
                        model.setGameState(ViewState.MENU);
                        return;
                    }
                    model.resetGame(name);
                    model.setGameState(ViewState.GAME);
                    model.setNewGame();
                }

                if (y >= 330 && y <= 400) {
                    model.setGameState(ViewState.TABLE);
                }

                if (y >= 430 && y <= 500) {
                    model.setGameState(ViewState.ABOUT);
                }
                if (y >= 530 && y <= 600) {
                    view.dispose();
                }

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
