package com.example.oopproject.game.soccer;

import com.example.oopproject.game.Direction;
import com.example.oopproject.game.entities.Keeper;
import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Soccer {
    private Keeper keeper;
    //private Ball ball;
    //private GraphicsContext gc;
    //public Canvas canvas;

    public Soccer() {
        keeper = new Keeper(10, 10, Sprite.keeper.getFxImage(), false);
        //ball = new ball;
    }

    public void setHit(boolean hit) {
        keeper.setHit(hit);
    }

    public void setDirection(Direction.DIRECTION direction) {
        keeper.setDirection(direction);
        //ball.setDirection(direction);
    }

    public void render(GraphicsContext gc) {
//        canvas = new Canvas(50, 50);
//        gc = canvas.getGraphicsContext2D();
//        //keeper.update();
        keeper.render(gc);
    }

    public void refresh() {

    }
}
