package com.example.oopproject.game.entities;

import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.image.Image;

public class FootPrint extends Entity {
    public FootPrint(int x, int y, Image image) {
        super(x, y, image, Sprite.DEFAULT_SIZE);
    }
}
