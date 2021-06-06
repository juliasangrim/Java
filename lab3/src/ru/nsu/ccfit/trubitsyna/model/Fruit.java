package ru.nsu.ccfit.trubitsyna.model;

import ru.nsu.ccfit.trubitsyna.view.TileType;


public class Fruit {
    private int fruitScore;
    private TileType tileType ;
    public Fruit() {
        this.fruitScore = 70;
        this.tileType = TileType.FRUIT;
    }

    public int getFruitScore() {
        return fruitScore;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void reduceFruitScore() {
        fruitScore--;
    }
}
