package com.example.oopproject.game.bear;

import com.example.oopproject.dictionary.Word;
import com.example.oopproject.game.entities.*;
import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public static List<List<Entity>> map;
    protected int[][] itemList;
    public static final int HEIGHT = 8;
    public static int WIDTH = 8;
    private Bear bear;
    private Honey honey;

    public Map() {
        map = new ArrayList<>();
        bear = new Bear(0, 0, Sprite.bear1.getFxImage());
        honey = new Honey(7, 7, Sprite.honey.getFxImage());
        itemList = new int[HEIGHT][WIDTH];
        try {
            String fileName = "./src/main/resources/map.txt";
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Scanner sc = new Scanner(fileInputStream);
            while (sc.hasNextLine()) {
                for (int i = 0; i < HEIGHT; i++) {
                    String line = sc.nextLine();
                    List<Entity> list = new ArrayList<>();
                    for (int j = 0; j < WIDTH; j++) {
                        switch(line.charAt(j)) {
                            case '+':
                                list.add(new Rock(j, i, Sprite.rock.getFxImage()));
                                break;
                            case '?':
                                list.add(new QuestionMark(j, i, Sprite.question_mark.getFxImage()));
                                break;
                            case '#':
                                list.add(new FootPrint(j, i, Sprite.foot_print.getFxImage()));
                                break;
                            case '.':
                                list.add(new Grass(j, i, Sprite.grass.getFxImage()));
                                break;
                            default:
                                break;
                        }
                    }
                    map.add(list);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Bear getBear() {
        return bear;
    }

    public void updateMap(int x, int y, Entity entity) {
        map.get(y).set(x, entity);
    }

    public void update(KeyEvent event) {
        bear.update(event);
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity entity = map.get(i).get(j);
                entity.render(gc);
            }
        }
        bear.render(gc);
        honey.render(gc);
    }
}


