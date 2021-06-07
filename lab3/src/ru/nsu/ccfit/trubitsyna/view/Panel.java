package ru.nsu.ccfit.trubitsyna.view;

import ru.nsu.ccfit.trubitsyna.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private final GameModel model;
    private static final Font BIG_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 16);

    public Panel(GameModel gameModel) {
        this.model = gameModel;
        setPreferredSize(new Dimension( 300,  model.getHeight() * BoardDraw.TILE_SIZE));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(BIG_FONT);
        graphics.drawString("Statistics", 50, 100);
        int drawY = 100;
        graphics.setFont(SMALL_FONT);
        graphics.drawString("Total Score: " + model.getScore(), 70, drawY += 30);
        graphics.drawString("Fruit Eaten: " + model.getAmountEatenFruit(), 70, drawY += 30);
        graphics.drawString("Fruit Score: " + model.getFruitScore(), 70, drawY += 30);
        graphics.setFont(SMALL_FONT);
        drawY = 500;
        graphics.drawString("Some instructions: ", 50, drawY);
        graphics.drawString("P - pause ", 70, drawY+=30);
        graphics.drawString("F - reset game ", 70, drawY+=30);
        graphics.drawString("B - back to menu ", 70, drawY+=30);
        graphics.drawString("Esc - exit ", 70, drawY+=30);
    }
}
