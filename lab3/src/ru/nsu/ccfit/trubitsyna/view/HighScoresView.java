package ru.nsu.ccfit.trubitsyna.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HighScoresView extends JPanel {
    private final int width;
    private boolean isFirstOpen;
    private final static int TILE_SIZE = 20;
    private final static Font BIG_FONT = new Font("Arial", Font.BOLD, 40);
    private final static Font SMALL_FONT = new Font("Arial", Font.ITALIC, 20);

    public HighScoresView() {
        int height = 35;
        width = 50;
        isFirstOpen = true;
        setPreferredSize(new Dimension(width * TILE_SIZE, height * TILE_SIZE));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        graphics.setFont(BIG_FONT);
        graphics.setColor(Color.PINK);
        graphics.drawString("~HIGH SCORES!~", width * TILE_SIZE / 2 - 150, 120);
        graphics.setFont(SMALL_FONT);
        int drawY = 160;
        int i = 1;
        String content = "";
        try {
            content = Files.readString(Paths.get("src/ru/nsu/ccfit/trubitsyna/model/table.data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (content.equals("")) {
            return;
        }
        for (String str : content.split("\r\n")) {
            Rectangle frame = new Rectangle(width * 20 / 2 - 300, drawY+=40, 600, 40);
            String name = "";
            String score = "";
            if (i > 3) {
                graphics.setColor(Color.PINK);
            } else {
                graphics.setColor(Color.GREEN);
            }
            if (str.split(";")[0].equals("")) {
                name = "Unknown user";
            } else {
                name = str.split(";")[0];
            }
            score = str.split(";")[1];
            graphics.drawString(Integer.toString(i), frame.x + 20, frame.y + 30);
            graphics.drawString(name, frame.x + 70, frame.y + 30);
            graphics.drawString(score, frame.x + 500, frame.y + 30);
            graphics.setColor(Color.PINK);
            g2d.draw(frame);
            i++;
        }
    }

    public boolean isFirstOpen() {
        return isFirstOpen;
    }

    public void setIsFirstOpen(boolean open) {
        this.isFirstOpen = open;
    }
}
