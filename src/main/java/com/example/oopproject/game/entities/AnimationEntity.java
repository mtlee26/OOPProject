package com.example.oopproject.game.entities;

import com.example.oopproject.game.Direction;
import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.image.Image;

public class AnimationEntity extends Entity {
    protected Direction.DIRECTION direction;
    protected int animate = 0;
    protected final int MAX_ANIMATE = 3000;
    protected Sprite sprite;
    protected boolean isMoving;

    public AnimationEntity(int x, int y, Image image) {
        super(x, y, image);
        direction = Direction.DIRECTION.CENTER;
        isMoving = false;
    }

    public Direction.DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(Direction.DIRECTION direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

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
