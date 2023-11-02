package com.example.oopproject.controller.game;

import com.example.oopproject.game.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;

public class HoneyBear {
    private Map map;

    public HoneyBear() {
        map = new Map();
    }

    public void update(KeyEvent event) {
        map.update(event);
    }

    public void render(GraphicsContext gc) {
        map.render(gc);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
