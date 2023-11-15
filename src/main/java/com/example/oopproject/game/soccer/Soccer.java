package com.example.oopproject.game.soccer;

//.Direction;
import com.example.oopproject.game.entities.AnimationEntity;
import com.example.oopproject.game.entities.Ball;
import com.example.oopproject.game.entities.Bear;
import com.example.oopproject.game.entities.Keeper;
import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Soccer {
    private Keeper keeper;
    private Ball ball;
    private boolean isRunning;

    public Soccer() {
        System.out.println("new soccer");
        keeper = new Keeper(220, 20, Sprite.keeper_0.getFxImage(), false);
        ball = new Ball(220, 220, Sprite.ball.getFxImage(), false);
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setHit(boolean hit) {
        keeper.setHit(hit);
        ball.setHit(hit);
        ball.setMoving(true);
    }

    public void setDirection(AnimationEntity.DIRECTION direction) {
        keeper.setDirection(direction);
        ball.setDirection(direction);
    }

    public void update() {
        keeper.update();
        ball.update();
    }

    public void render(GraphicsContext gc) {
        if (isRunning) {
            Canvas canvas = gc.getCanvas();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            keeper.render(gc);
            ball.render(gc);
        }
    }

    public void reset() {
        keeper.reset();
        ball.reset();
        //System.out.println(keeper.isHit());
    }
}
