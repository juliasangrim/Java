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


    @Override
    public void modelChanged(GameModel model) {
        boardDraw.repaint();
        panel.repaint();
    }

    public void openBoard() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.boardDraw = new BoardDraw(model);
        this.panel = new Panel(model);
        add(boardDraw,  BorderLayout.CENTER);
        add(panel, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setModel(GameModel gameModel) {
        noListen();
        this.model = gameModel;
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
