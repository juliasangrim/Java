package ru.nsu.ccfit.trubitsyna.model;


import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
    private Deque<Point> body;
    private int fruitEaten;
    public static final int START_LENGTH = 4;
    public Snake() {
        this.body = new ArrayDeque<>();
        this.fruitEaten = 0;
    }

    public Point getNewHead(Directions direction) {
        Point head;
        if (body.size() != 0) {
            head = new Point(body.peekFirst());
        }
        else {
            head = new Point();
        }
        switch (direction) {
            case UP:
               head.y--;
               break;
            case DOWN:
                head.y++;
                break;
            case LEFT:
                head.x--;
                break;
            case RIGHT:
                head.x++;
                break;

        }
        return head;
    }

    public Point deleteTail() {
        return this.body.removeLast();
    }

    public void addNewHead(Point head) {
        this.body.push(head);
    }

    public int getSizeSnake() {
        return this.body.size();
    }

    public Point getHead() {
        return this.body.peekFirst();
    }

    public void snakeEatFruit(){
        this.fruitEaten++;
    }

    public int getFruitEaten() {
        return fruitEaten;
    }
}
