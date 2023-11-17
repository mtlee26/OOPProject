package com.example.oopproject.game.entities;

import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.image.Image;

public class AnimationEntity extends Entity {
    protected DIRECTION direction;
    public static enum DIRECTION {
        LEFT, RIGHT, UP, DOWN, CENTER
    }
    protected int animate;
    protected final int MAX_ANIMATE = 3000;
    protected Sprite sprite;
    //protected boolean isMoving;

    public AnimationEntity(double x, double y, Image image) {
        super(x, y, image);
        direction = DIRECTION.CENTER;
        animate = 0;
    }

    public AnimationEntity(int x, int y, Image image, int size) {
        super(x, y, image, size);
        direction = DIRECTION.CENTER;
        animate = 0;
        //isMoving = false;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

//    public boolean isMoving() {
//        return isMoving;
//    }
//
//    public void setMoving(boolean moving) {
//        isMoving = moving;
//    }

    protected void animate() {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }

    public void update() {
    }

    public Sprite chooseSprite() {
        return null;
    }

}
