package ru.nsu.ccfit.trubitsyna.view;

import ru.nsu.ccfit.trubitsyna.gameException.GameException;
import ru.nsu.ccfit.trubitsyna.model.GameModel;
import ru.nsu.ccfit.trubitsyna.model.IListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ViewGame extends JFrame implements IListener {
    private BoardDraw boardDraw;
    private Panel panel;
    private GameModel model;
    private Menu menu;
    private AboutView about;
    private HighScoresView table;

    @Override
    public void modelChanged(GameModel model) {
        ViewState state = model.getGameState();
        if (state == ViewState.GAME) {
            if (model.isNewGame()) {
                setBackground(Color.DARK_GRAY);
                add(boardDraw, BorderLayout.CENTER);
                add(panel, BorderLayout.EAST);
                validate();
                menu.setIsFirstOpen(true);
            }
            boardDraw.repaint();
            panel.repaint();
        }

        if (state == ViewState.MENU) {
          if (menu.isFirstOpen()) {
              setBackground(Color.DARK_GRAY);
              getContentPane().remove(panel);
              add(menu, BorderLayout.CENTER);
              validate();
              menu.setIsFirstOpen(false);
              about.setIsFirstOpen(true);
           }
            menu.repaint();
        }
       if (state == ViewState.ABOUT) {
           if (about.isFirstOpen()) {
               setBackground(Color.DARK_GRAY);
               add(about, BorderLayout.CENTER);
               validate();
               about.setIsFirstOpen(false);
               menu.setIsFirstOpen(true);
           }
           about.repaint();
       }

        if (state == ViewState.TABLE) {
            if (table.isFirstOpen()) {
                setBackground(Color.DARK_GRAY);
                add(table, BorderLayout.CENTER);
                validate();
                table.setIsFirstOpen(false);
                menu.setIsFirstOpen(true);
            }
            table.repaint();
        }
    }

    public void openBoard() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(menu, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setModel(GameModel gameModel) throws IOException, GameException {
        noListen();
        this.model = gameModel;
        this.boardDraw = new BoardDraw(model);
        this.panel = new Panel(model);
        this.about = new AboutView();
        this.menu = new Menu();
        this.table = new HighScoresView();
        openBoard();
        listen();
    }

    private void listen() throws GameException {
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

    public String getUserName() {
        return JOptionPane.showInputDialog("Tell me, who are you...")   ;
    }

}
