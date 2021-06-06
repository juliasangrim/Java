package ru.nsu.ccfit.trubitsyna.view;

import ru.nsu.ccfit.trubitsyna.model.Board;
import ru.nsu.ccfit.trubitsyna.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class BoardDraw extends JPanel {
    private GameModel model;
    private Board board;
    public static int width;
    public static int height;
    public static final int TILE_SIZE = 20;
    public TileType[] tiles;

    public BoardDraw(GameModel gameModel) {
        this.model = gameModel;
        this.board = model.getBoard();
        height = board.getRowCount();
        width = board.getColumnCount();
        setPreferredSize(new Dimension(width * TILE_SIZE, height * TILE_SIZE));
        setBackground(Color.PINK);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawBoard(graphics);
        graphics.setColor(Color.GRAY);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        String gameMessage = null;
        String infoMessage = null;
        if (model.isEnd()) {
            gameMessage = "You died! :)";
            infoMessage= "Press F to pay respect(reset game)...";
            Font font = new Font("Arial", Font.BOLD, 25);
            graphics.setColor(Color.DARK_GRAY);
            graphics.setFont(font);
            graphics.drawString(gameMessage, centerX - graphics.getFontMetrics().stringWidth(gameMessage) / 2, centerY - 50);
            graphics.drawString(infoMessage, centerX - graphics.getFontMetrics().stringWidth(infoMessage) / 2, centerY + 50);
        }

    }

    private void drawBoard(Graphics graphics) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(board.getTile(x, y) != null) {
                    drawTile(board.getTile(x, y), graphics, x * TILE_SIZE, y * TILE_SIZE);
                }
            }
        }

    }

    private void drawTile(TileType tile, Graphics graphics, int x, int y) {
        switch(tile) {
            case FRUIT:
                graphics.setColor(Color.RED);
                graphics.fillOval(x + 2, y + 2, TILE_SIZE - 4, TILE_SIZE - 4);
                break;

            case SNAKE_BODY:
                graphics.setColor(Color.GREEN);
                graphics.fillOval(x, y, TILE_SIZE, TILE_SIZE);
                break;

            case SNAKE_HEAD:
                //Fill the tile in with green.
                graphics.setColor(Color.YELLOW);
                graphics.fillOval(x, y, TILE_SIZE, TILE_SIZE);
                break;
        }
    }

}
