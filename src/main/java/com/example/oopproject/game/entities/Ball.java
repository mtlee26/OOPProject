package com.example.oopproject.game.entities;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import com.example.oopproject.game.graphics.Sprite;

import static com.example.oopproject.game.entities.AnimationEntity.DIRECTION.*;

public class Ball extends AnimationEntity {
    private boolean isHit;
    private boolean isMoving;
    private double initX, initY;

    public Ball(double x, double y, Image image, boolean isHit) {
        super(x, y, image);
        initX = x;
        initY = y;
        this.isHit = isHit;
        sprite = Sprite.ball;
        isMoving = false;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Sprite chooseSprite() {
//        animate();
//        switch (direction) {
//            case CENTER:
//                sprite = Sprite.movingSprite(Sprite.keeper_0, Sprite.keeper_0, animate, 150);
//                break;
//            case LEFT:
//                animate();
//                if (isMoving) {
//                    sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 150);
////                    if (isHit) {
////                        //sprite = Sprite.keeper_hit_left;
////                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
////                    } else {
////                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
////                    }
//                    if (sprite.equals(Sprite.keeper_left[4])) {
//                        isMoving = false;
//                        animate = 0;
//                    }
//                }
//                break;
//            case RIGHT:
//                animate();
//                if (isMoving) {
//                    sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 150);
////                    if (isHit) {
////                        //sprite = Sprite.keeper_hit_left;
////                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
////                    } else {
////                        sprite = Sprite.soccerMovingSprite(Sprite.keeper_left, animate, 100);
////                    }
//                    if (sprite.equals(Sprite.keeper_left[4])) {
//                        isMoving = false;
//                        animate = 0;
//                    }
//                }
//                break;
//            default:
//                break;
            sprite = Sprite.ball;
            //sprite = Sprite.movingSprite(Sprite.keeper, Sprite.keeper_1, animate, 150);            }
        //}
        return sprite;
        //return null;
    }

    @Override
    public void render(GraphicsContext gc) {
        img = chooseSprite().getFxImage();
        gc.drawImage(img, x, y);
    }

    @Override
    public void update() {
        if (isMoving) {
            if (isHit) {
                if (direction == LEFT) {
                    x -= 0.5;
                    y -= 3;
                } else if (direction == RIGHT) {
                    x += 0.5;
                    y -= 3;
                }
                if (y <= 30) {
                    isMoving = false;
                }
                    //System.out.println(x + " " + y);
            } else {
                if (direction == LEFT) {
                    x -= 3;
                    y -= 3;
                } else if (direction == RIGHT) {
                    x += 3;
                    y -= 3;
                }
                if (y <= 55) {
                    isMoving = false;
                }
            }
        }
    }

    public void reset() {
        direction = CENTER;
        isHit = false;
        isMoving = false;
        animate = 0;
        x = initX;
        y = initY;
        //System.out.println(animate);
    }
}
