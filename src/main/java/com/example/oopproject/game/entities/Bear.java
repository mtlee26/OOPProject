package com.example.oopproject.game.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.EventHandler;

public class Bear extends AnimationEntity {
    //private EventHandler event;
    private int xUnit;
    private int yUnit;
    private boolean isCanMove;
    public Bear(int x, int y, Image image) {
        super(x, y, image);
        //this.keyListener = keyListener;
        xUnit = x;
        yUnit = y;
        isCanMove = true;
    }

    public void update(KeyEvent event) {
        if (event.KEY_PRESSED.equals(KeyCode.UP)) {
            System.out.println("up");

        }
    }

//    public boolean canMove(int x, int y) {
//        int xLeft = x - 1;
//        int xRight = x + 1;
//        int yUp = y + 1;
//        int yDown = y - 1;
//
//
//    }
}
