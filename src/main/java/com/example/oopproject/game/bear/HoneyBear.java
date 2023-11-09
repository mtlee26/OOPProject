package com.example.oopproject.game.bear;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class HoneyBear {
    private Map map;
    private boolean isRunning;
    private Media sound;
    public MediaPlayer soundPlayer;

    public HoneyBear() {
        isRunning = true;
        map = new Map();
        sound = new Media(new File("./src/main/resources/sound/music.wav").toURI().toString());
        soundPlayer = new MediaPlayer(sound);
        soundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void update(KeyEvent event) {
        map.update(event);
    }

    public void render(GraphicsContext gc) {
        if (isRunning) {
            map.render(gc);
            soundPlayer.play();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
