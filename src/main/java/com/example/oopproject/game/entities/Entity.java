package com.example.oopproject.game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import com.example.oopproject.game.graphics.Sprite;

public class Entity {
    protected double x;
    protected double y;
    protected Image img;

    public Entity(double x, double y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public Entity( int xUnit, int yUnit, Image img, int size) {
        this.x = xUnit * size;
        this.y = yUnit * size;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
