package ru.nsu.ccfit.trubitsyna.view;

import ru.nsu.ccfit.trubitsyna.model.Board;
import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.model.IListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ViewGame extends JFrame implements IListener {
    private BoardDraw boardDraw;
    private Panel panel;
    private GameModel model;
    private Menu menu;

    @Override
    public void modelChanged(GameModel model) {
        if (model.getGameState() == ViewState.GAME) {
            if (model.isNewGame()) {
                add(boardDraw, BorderLayout.CENTER);
                add(panel, BorderLayout.EAST);
                pack();
            }
            boardDraw.repaint();
            panel.repaint();
        }
        else {
            menu.repaint();
        }
    }

    public void openBoard() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
//        add(boardDraw,  BorderLayout.CENTER);
//        add(panel, BorderLayout.EAST);
        this.menu = new Menu();
        add(menu, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setModel(GameModel gameModel) {
        noListen();
        this.model = gameModel;
        this.boardDraw = new BoardDraw(model);
        this.panel = new Panel(model);
       // this.menu = new Menu();
        openBoard();
        listen();
    }

    private void listen() {
        if (model != null) {
            model.listen(this);
        }
    }

    private void noListen() {
        if (model != null) {
            model.noListen(this);
        }
    }

    public void dispose() {
        noListen();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
