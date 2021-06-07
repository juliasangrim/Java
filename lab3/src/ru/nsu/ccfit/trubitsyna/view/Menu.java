package ru.nsu.ccfit.trubitsyna.view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private final int width;
    private boolean isFirstOpen;
    private final static int TILE_SIZE = 20;
    private final static Font BIG_FONT = new Font("Arial", Font.BOLD, 60);
    private final static Font SMALL_FONT = new Font("Arial", Font.BOLD, 30);
    public final Rectangle playButton = new Rectangle(50 * 20 / 2 - 100, 200, 200, 70);
    public final Rectangle highScoreButton = new Rectangle(50 * 20 / 2 - 100, 300, 200, 70);
    public final Rectangle aboutButton = new Rectangle(50 * 20 / 2 - 100, 400, 200, 70);
    public final Rectangle exitButton = new Rectangle(50 * 20 / 2 - 100, 500, 200, 70);
    public Menu() {
        int height = 35;
        width = 50;
        isFirstOpen = false;
        setPreferredSize(new Dimension(width * TILE_SIZE, height * TILE_SIZE));
        setBackground(Color.DARK_GRAY);
    }
   @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
       graphics.setFont(BIG_FONT);
       graphics.setColor(Color.PINK);
       graphics.drawString("SNAKE GAME", width * TILE_SIZE / 2 - 200, 120);

       graphics.setFont(SMALL_FONT);
       graphics.drawString("New game", playButton.x + 25, playButton.y + 45);
       g2d.draw(playButton);
       graphics.drawString("High score", highScoreButton.x + 25, highScoreButton.y + 45);
       g2d.draw(highScoreButton);
       graphics.drawString("About", aboutButton.x + 55, aboutButton.y + 45);
       g2d.draw(aboutButton);
       graphics.drawString("Exit", exitButton.x + 70, exitButton.y + 45);
       g2d.draw(exitButton);
    }

    public boolean isFirstOpen() {
        return isFirstOpen;
    }

    public void setIsFirstOpen(boolean open) {
        this.isFirstOpen = open;
    }
}
