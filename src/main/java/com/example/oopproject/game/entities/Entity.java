package com.example.oopproject.game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import com.example.oopproject.game.graphics.Sprite;

public class Entity {
    protected int x;
    protected int y;
    protected Image img;

//    public Entity(int x, int y, Image img) {
//        this.x = x;
//        this.y = y;
//        this.img = img;
//    }

    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
