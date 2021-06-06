package ru.nsu.ccfit.trubitsyna.model;

import ru.nsu.ccfit.trubitsyna.view.TileType;

import java.awt.*;

public class Board {
    private TileType[] tiles;
    private static final int COLUMN_COUNT = 35;
    private static final int ROW_COUNT = 35;

    public Board() {
        this.tiles = new TileType[COLUMN_COUNT * ROW_COUNT];
    }

    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    public int getRowCount() {
        return ROW_COUNT;
    }

    public TileType[] getTiles() {
        return tiles;
    }

    public TileType getTile(int x, int y) {
        return tiles[y * ROW_COUNT + x];
    }

    public void setTile(Point point, TileType tile) {
        setTile(point.x, point.y, tile);
    }

    public void setTile(int x, int y, TileType tile) {
        tiles[y * ROW_COUNT + x] = tile;
    }

}
