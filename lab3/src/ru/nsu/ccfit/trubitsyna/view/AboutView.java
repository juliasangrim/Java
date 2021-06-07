package ru.nsu.ccfit.trubitsyna.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AboutView extends JPanel {
    private final int width;
    private boolean isFirstOpen;
    private final String content;
    private final static int TILE_SIZE = 20;
    private final static Font BIG_FONT = new Font("Arial", Font.BOLD, 40);
    private final static Font SMALL_FONT = new Font("Arial", Font.ITALIC, 20);

    public AboutView() throws IOException {
        int height = 35;
        width = 50;
        this.content = Files.readString(Paths.get("src/ru/nsu/ccfit/trubitsyna/view/About.txt"));
        isFirstOpen = true;
        setPreferredSize(new Dimension(width * TILE_SIZE, height * TILE_SIZE));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setFont(BIG_FONT);
        graphics.setColor(Color.PINK);
        graphics.drawString("~THE OLD SNAKE IS HERE!~", width * TILE_SIZE / 2 - 260, 120);
        graphics.setFont(SMALL_FONT);
        int drawY = 200;
        for (String string : content.split("\n")) {
            graphics.drawString(string, width * TILE_SIZE / 2 - 260, drawY += 30);
        }
    }

    public boolean isFirstOpen() {
        return isFirstOpen;
    }

    public void setIsFirstOpen(boolean open) {
        this.isFirstOpen = open;
    }


}
