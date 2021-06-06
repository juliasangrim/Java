package ru.nsu.ccfit.trubitsyna.model;

import ru.nsu.ccfit.trubitsyna.view.ViewState;
import ru.nsu.ccfit.trubitsyna.view.TileType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameModel extends JFrame {
    private CopyOnWriteArrayList<IListener> listeners = new CopyOnWriteArrayList<IListener>();
    private ViewState state;
    private Board board;
    private Random random;
    private Snake snake;
    private Fruit fruit;
    private Deque<Directions> direction;
    private int score;
    private boolean isNewGame;
    private boolean isGameEnd;
    private boolean isPaused;

    public GameModel() {
        this.board = new Board();
        this.snake = new Snake();
        this.random = new Random();
       // spawnFruit();
        this.score = 0;
        this.direction = new ArrayDeque<Directions>();
        this.direction.push(Directions.UP);
        this.isNewGame = true;
        this.isGameEnd = false;
        this.state = ViewState.MENU;
    }

    private void resetGame() {
        this.score = 0;
        this.isGameEnd = false;
        this.isNewGame = false;
        board.clear();
        this.snake = new Snake();

        Point head = new Point(board.getColumnCount() / 2, board.getRowCount() / 2);
        snake.addNewHead(head);
        board.setTile(head, TileType.SNAKE_HEAD);

        direction.clear();
        direction.add(Directions.UP);

        spawnFruit();
    }

    //notify listeners about changes
    protected void notifyListeners() {
        for (IListener listener : listeners) {
            notifyListener(listener);
        }
    }

    private void notifyListener(IListener listener) {
        if (listener == null) {
            throw new NullPointerException("No listeners for our model...");
        }
        listener.modelChanged(this);
    }

    //method for following our model changes
    public void listen(IListener listener) {
        if (listener == null) {
            throw new NullPointerException("Empty param...");
        }
        if (listeners.contains(listener)) {
            throw new IllegalArgumentException("Repeat listeners...");
        }
        listeners.add(listener);
        notifyListener(listener);
    }

    //method for stop following our model changes
    public void noListen(IListener listener) {
        if (listener == null) {
            throw new NullPointerException("Empty param...");
        }
        if (!listeners.contains(listener)) {
            throw new IllegalArgumentException("Model don't have this listener...");
        }
    }

    private TileType updatePlaceSnake(Point head) {
        if(head.x < 0 || head.x >= board.getColumnCount() || head.y < 0 || head.y >= board.getRowCount()) {
            return TileType.SNAKE_BODY; //Pretend we collided with our body.
        }
        TileType tile = board.getTile(head.x, head.y);

        if(tile != TileType.FRUIT && snake.getSizeSnake() > Snake.START_LENGTH) {
            Point tail = snake.deleteTail();
            board.setTile(tail, null);
            tile = board.getTile(head.x, head.y);
        }

        if(tile != TileType.SNAKE_BODY) {
            board.setTile(snake.getHead(), TileType.SNAKE_BODY);
            snake.addNewHead(head);
            board.setTile(head, TileType.SNAKE_HEAD);
            if (direction.size() > 1) {
                direction.poll();
            }
        }

        return tile;
    }

    public void startRound() {
        while (true) {
            if (state == ViewState.GAME) {
                if (isNewGame) {
                    notifyListeners();
                    resetGame();
                }
                if (!isGameEnd) {
                    if (!isPaused) {
                        updateGame();
                    }
                }
            }

            notifyListeners();
            if (state == ViewState.GAME) {
                try {
                    Thread.sleep(70);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateGame() {
        Point head = snake.getNewHead(getDirection());
        TileType collision = updatePlaceSnake(head);

        if (collision == TileType.SNAKE_BODY) {
            isGameEnd = true;
        } else if (collision == TileType.FRUIT){
            snake.snakeEatFruit();
            score += fruit.getFruitScore();
            spawnFruit();
        } else if (fruit.getFruitScore() > 20) {
            fruit.reduceFruitScore();
        }

    }

    private void spawnFruit() {
        this.fruit = new Fruit();
        int place = random.nextInt(board.getColumnCount() * board.getRowCount() - snake.getSizeSnake());
        int freePlace = -1;
        for(int x = 0; x < board.getColumnCount(); x++) {
            for(int y = 0; y <board.getRowCount(); y++) {
                TileType tile = board.getTile(x, y);
                if(tile == null || tile == fruit.getTileType()) {
                    if(++freePlace== place) {
                        board.setTile(x, y, fruit.getTileType());
                        break;
                    }
                }
            }
        }
    }

    public void changeDirection(Directions direction) {
        this.direction.addLast(direction);
    }

    public Directions getDirection() {
        return this.direction.peekLast();
    }

    public int getSizeArrayDirections() {
        return direction.size();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isEnd() {
        return isGameEnd;
    }

    public boolean isPause() {
        return isPaused;
    }

    public boolean isNewGame() {
        return isNewGame;
    }

    public int getScore() {
        return score;
    }

    public int getAmountEatenFruit(){
        return snake.getFruitEaten();
    }

    public int getFruitScore() {
        return fruit.getFruitScore();
    }

    public void setOnPause() {
        this.isPaused = true;
    }
    public void setNewGame() {
        this.isNewGame = true;
    }
    public void offPause() {
        this.isPaused = false;
    }

    public ViewState getGameState() {
        return this.state;
    }

    public void setGameState(ViewState state) {
        this.state = state;
    }
}
