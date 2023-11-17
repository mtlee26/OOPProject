package com.example.oopproject.game.entities;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import com.example.oopproject.game.graphics.Sprite;

import static com.example.oopproject.game.entities.AnimationEntity.DIRECTION.*;

public class Keeper extends AnimationEntity {
    private boolean isHit;
    private boolean isMoving;
    private double initX, initY;
    public static final double MOVE_SPACE = 2.5;

    public Keeper(double x, double y, Image image, boolean isHit) {
        super(x, y, image);
        initX = x;
        initY = y;
        this.isHit = isHit;
        sprite = Sprite.keeper;
        isMoving = true;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Sprite chooseSprite() {
        switch (direction) {
            case CENTER:
                sprite = Sprite.keeper;
                break;
            case LEFT:
                animate();
                if (isMoving) {
                    sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
                    if (sprite.equals(Sprite.keeper_left[Sprite.keeper_left.length - 1])) {
                        isMoving = false;
                        animate = 0;
                    }
                }
                break;
            case RIGHT:
                animate();
                if (isMoving) {
                    sprite = Sprite.soccerMovingSprite(Sprite.keeper_right, animate, 100);
                    if (sprite.equals(Sprite.keeper_right[Sprite.keeper_right.length - 1])) {
                        isMoving = false;
                        animate = 0;
                    }
                }
                break;
            default:
                break;
        }
        return sprite;
    }

    @Override
    public void render(GraphicsContext gc) {
        img = chooseSprite().getFxImage();
        gc.drawImage(img, x, y);
    }

    @Override
    public void update() {
        if (isMoving) {
            if (direction == LEFT) {
                x -= MOVE_SPACE;
            } else if (direction == RIGHT) {
                x += MOVE_SPACE;
            }
        }
        if (x >= 500 || x <= 50) {
            isMoving = false;
        }
    }

    public void reset() {
        direction = CENTER;
        isHit = false;
        isMoving = true;
        animate = 0;
        x = initX;
        y = initY;
    }
}
