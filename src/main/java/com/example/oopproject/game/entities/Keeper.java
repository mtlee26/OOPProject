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

    public Keeper(double x, double y, Image image, boolean isHit) {
        super(x, y, image);
        initX = x;
        initY = y;
        this.isHit = isHit;
        sprite = Sprite.keeper_0;
        isMoving = true;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Sprite chooseSprite() {
//        animate();
        switch (direction) {
            case CENTER:
                //animate();
                sprite = Sprite.keeper_hit_left;
                //sprite = Sprite.movingSprite(Sprite.keeper, Sprite.keeper_1, animate, 150);
                //sprite = Sprite.movingSprite(Sprite.keeper_left, Sprite.keeper_1, animate, 100);
                //sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
                //System.out.println(sprite);
                break;
            case LEFT:
                //animate = 0;
                animate();
                if (isMoving) {
                    sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
//                    if (isHit) {
//                        //sprite = Sprite.keeper_hit_left;
//                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
//                    } else {
//                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
//                    }
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
//                    if (isHit) {
//                        //sprite = Sprite.keeper_hit_left;
//                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
//                    } else {
//                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
//                    }
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
        if (isHit) {
            if (isMoving) {
                if (direction == LEFT) {
                    x -= 2.5;
                } else if (direction == RIGHT) {
                    x += 2.5;
                }
//                System.out.println(x + " " + y);
            }
        } else {
            if (isMoving) {
                if (direction == LEFT) {
                    x -= 2.5;
                } else if (direction == RIGHT) {
                    x += 2.5;
                }

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
        System.out.println(animate);
    }
}
