package ru.nsu.ccfit.trubitsyna.model;

import ru.nsu.ccfit.trubitsyna.gameException.GameException;
import ru.nsu.ccfit.trubitsyna.view.ViewState;
import ru.nsu.ccfit.trubitsyna.view.TileType;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Integer.parseInt;
import static ru.nsu.ccfit.trubitsyna.view.ViewState.GAME;
import static ru.nsu.ccfit.trubitsyna.view.ViewState.RECORD;

public class GameModel extends JFrame {
    private final CopyOnWriteArrayList<IListener> listeners = new CopyOnWriteArrayList<>();
    private ViewState state;
    private final Board board;
    private final Random random;
    private Snake snake;
    private Fruit fruit;
    private final Deque<Directions> direction;
    private User user;
    private final HashSet <User> highScores;
    private boolean isNewGame;
    private boolean isGameEnd;
    private boolean isPaused;

    public GameModel() {
        this.board = new Board();
        this.snake = new Snake();
        this.random = new Random();
       // spawnFruit();
        this.user = new User();
        this.direction = new ArrayDeque<>();
        this.direction.push(Directions.UP);
        this.isNewGame = true;
        this.isGameEnd = false;
        this.state = ViewState.MENU;
        this.highScores = new HashSet<>();
    }

    public void resetGame(String name) {
        user = new User();
        user.score = 0;
        user.name = name;
        isGameEnd = false;
        isNewGame = false;
        board.clear();
        snake = new Snake();

        Point head = new Point(board.getColumnCount() / 2, board.getRowCount() / 2);
        snake.addNewHead(head);
        board.setTile(head, TileType.SNAKE_HEAD);

        direction.clear();
        direction.add(Directions.UP);

        spawnFruit();
    }

    //notify listeners about changes
    protected void notifyListeners() throws GameException {
        for (IListener listener : listeners) {
            notifyListener(listener);
        }
    }

    private void notifyListener(IListener listener) throws GameException {
        if (listener == null) {
            throw new GameException("No listeners for our model...");
        }
        listener.modelChanged(this);
    }

    //method for following our model changes
    public void listen(IListener listener) throws GameException {
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

    public void startRound() throws IOException, GameException {
       setHighScores();
        while (true) {
            if (state == ViewState.GAME) {
                if (isNewGame) {
                    notifyListeners();
                }
                if (!isGameEnd) {
                    if (!isPaused) {
                        updateGame();
                    }
                }
                if ((state == ViewState.RECORD) && (isGameEnd)) {
                    addRecord(user);
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
            state = RECORD;
        } else if (collision == TileType.FRUIT){
            snake.snakeEatFruit();
            user.score += fruit.getFruitScore();
            spawnFruit();
        } else if (fruit.getFruitScore() > 20) {
            fruit.reduceFruitScore();
        }

    }

    private void spawnFruit() {
        fruit = new Fruit();
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
        return direction.peekLast();
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
        return user.score;
    }

    public int getAmountEatenFruit(){
        return snake.getFruitEaten();
    }

    public int getFruitScore() {
        return fruit.getFruitScore();
    }

    public void setOnPause() {
        isPaused = true;
    }
    public void setNewGame() {
        isNewGame = true;
    }
    public void offPause() {
        isPaused = false;
    }

    public ViewState getGameState() {
        return state;
    }

    public void setGameState(ViewState state) {
        this.state = state;
    }

    public void setHighScores() throws IOException {
        String table = Files.readString(Paths.get("src/ru/nsu/ccfit/trubitsyna/model/table.data"));
        for (String string : table.split("\r\n")) {
            if (!string.equals("")) {
                User userTmp = new User();
                userTmp.name = string.split(";")[0];
                userTmp.score = parseInt(string.split(";")[1]);
                highScores.add(userTmp);

            }
        }
    }

    public Set<User> getHighScores() {
        return highScores;
    }

    public void addRecord(User user) throws IOException {
        state = GAME;
        highScores.add(user);

        NavigableSet<User> helper = new TreeSet<>(Comparator.comparing(u -> u.score));
        helper.addAll(highScores);
        if (helper.size() > 10) {
            helper.remove(helper.first());
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/ru/nsu/ccfit/trubitsyna/model/table.data"))) {
            for (User u : helper.descendingSet()) {
                writer.write(u.name + ";" + u.score + "\r\n");
            }
        }

    }
}
