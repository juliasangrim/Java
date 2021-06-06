package ru.nsu.ccfit.trubitsyna.view;

import ru.nsu.ccfit.trubitsyna.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private GameModel model;
    private static final Font BIG_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 16);

    public Panel(GameModel gameModel) {
        this.model = gameModel;
        setPreferredSize(new Dimension( 200,  model.getHeight() * BoardDraw.TILE_SIZE));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(BIG_FONT);
        graphics.drawString("Statistics", 30, 100);
        int drawY = 100;
        graphics.setFont(SMALL_FONT);
        graphics.drawString("Total Score: " + model.getScore(), 50, drawY += 30);
        graphics.drawString("Fruit Eaten: " + model.getAmountEatenFruit(), 50, drawY += 30);
        graphics.drawString("Fruit Score: " + model.getFruitScore(), 50, drawY += 30);
        graphics.setFont(BIG_FONT);
        drawY = 600;
        graphics.drawString("Some instructions: ", 30, drawY);
        graphics.drawString("P - pause ", 30, drawY+=30);
        graphics.drawString("F - back to menu ", 30, drawY+=30);
        graphics.drawString("Esc - exit ", 30, drawY+=30);
    }
}
