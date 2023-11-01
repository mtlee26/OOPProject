package com.example.oopproject.game.entities;

import com.example.oopproject.game.Direction;
import com.example.oopproject.game.Map;
import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.EventHandler;

public class Bear extends AnimationEntity {
    //private EventHandler event;
    private int xUnit;
    private int yUnit;
    private boolean isCanMove;
    public boolean isQuestion;

    public Bear(int xUnit, int yUnit, Image image) {
        super(xUnit, yUnit, image);
        //this.keyListener = keyListener;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        isCanMove = true;
        isQuestion = false;
    }

    @Override
    public Sprite chooseSprite() {
//        if (animate < MAX_ANIMATE) animate++;
//        else animate = 0;
        //System.out.println(animate);
        animate();
        sprite = Sprite.movingSprite(Sprite.bear1, Sprite.bear2, animate, 100);
        return sprite;
    }

    public void update(KeyEvent event) {
        //animate();
        if (event.KEY_PRESSED.equals(KeyCode.UP)) {
            System.out.println("up");
            if (canMove(xUnit, yUnit, Direction.DIRECTION.UP)) {
                yUnit--;
                y = yUnit * Sprite.DEFAULT_SIZE;
            }
        } else if (event.KEY_PRESSED.equals(KeyCode.DOWN)) {
            if (canMove(xUnit, yUnit, Direction.DIRECTION.DOWN)) {
                yUnit++;
                y = yUnit * Sprite.DEFAULT_SIZE;
            }
        } else if (event.KEY_PRESSED.equals(KeyCode.LEFT)) {
            if (canMove(xUnit, yUnit, Direction.DIRECTION.LEFT)) {
                xUnit--;
                x = xUnit * Sprite.DEFAULT_SIZE;
            }
        } else if (event.KEY_PRESSED.equals(KeyCode.RIGHT)) {
            System.out.println("right");
            if (canMove(xUnit, yUnit, Direction.DIRECTION.RIGHT)) {
                xUnit++;
                x = xUnit * Sprite.DEFAULT_SIZE;
            }
        }
    }

    public boolean canMove(int x, int y, Direction.DIRECTION direction) {
        if (direction == Direction.DIRECTION.UP) {
            int yUp = y - 1;
            if (yUp < 0 || yUp > Map.HEIGHT) {
                return false;
            }
            Entity entityUp = Map.map.get(yUp).get(x);
            if (entityUp instanceof Rock) {
                return false;
            } else if (entityUp instanceof QuestionMark) {
                isQuestion = true;
                return true;
            }
            return true;
        } else if (direction == Direction.DIRECTION.DOWN) {
            int yDown = y + 1;
            if (yDown < 0 || yDown > Map.HEIGHT) {
                return false;
            }
            Entity entityDown = Map.map.get(yDown).get(x);
            if (entityDown instanceof Rock) {
                return false;
            } else if (entityDown instanceof QuestionMark) {
                isQuestion = true;
                return true;
            }
            return true;
        } else if (direction == Direction.DIRECTION.LEFT) {
            int xLeft = x - 1;
            if (xLeft < 0 || xLeft > Map.WIDTH) {
                return false;
            }
            Entity entityLeft = Map.map.get(y).get(xLeft);
            if (entityLeft instanceof Rock) {
                return false;
            } else if (entityLeft instanceof QuestionMark) {
                isQuestion = true;
                return true;
            }
            return true;
        } else {
            int xRight = x + 1;
            if (xRight < 0 || xRight > Map.WIDTH) {
                return false;
            }
            Entity entityRight = Map.map.get(y).get(xRight);
            if (entityRight instanceof Rock) {
                return false;
            } else if (entityRight instanceof QuestionMark) {
                isQuestion = true;
                return true;
            }
        }
        return true;
    }

    @Override
    public void render(GraphicsContext gc) {
        img = chooseSprite().getFxImage();
        //update(keyEvent);
        gc.drawImage(img, x, y);
    }
}
