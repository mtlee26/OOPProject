package com.example.oopproject.game.entities;

import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.image.Image;

public class Grass extends Entity {
    public Grass(int x, int y, Image image) {
        super(x, y, image, Sprite.DEFAULT_SIZE);
    }
}
