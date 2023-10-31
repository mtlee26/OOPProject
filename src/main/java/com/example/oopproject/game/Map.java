package com.example.oopproject.game;

import com.example.oopproject.dictionary.Word;
import com.example.oopproject.game.entities.*;
import com.example.oopproject.game.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public List<List<Entity>> map;
    protected int[][] itemList;
    private final int HEIGHT = 8;
    private final int WIDTH = 8;

    public Map() {
        map = new ArrayList<>();
        itemList = new int[HEIGHT][WIDTH];
        try {
            String fileName = "./src/main/resources/.txt";
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Scanner sc = new Scanner(fileInputStream);
            while (sc.hasNextLine()) {
                for (int i = 0; i < HEIGHT; i++) {
                    String line = sc.nextLine();
                    List<Entity> list = new ArrayList<>();
                    for (int j = 0; j < WIDTH; j++) {
                        switch(line.charAt(j)) {
                            case '#':
                                list.add(new Rock(j, i, Sprite.rock.getFxImage()));
                                break;
                            case '?':
                                list.add(new QuestionMark(j, i, Sprite.question_mark.getFxImage()));
                                break;
                            case '.':
                                list.add(new FootPrint(j, i, Sprite.foot_print.getFxImage()));
                                break;
                            default:
                                list.add(new Grass(j, i, Sprite.grass.getFxImage()));
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

    public void updateMap(int x, int y, Entity entity) {
        map.get(y).set(x, entity);
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity entity = map.get(i).get(j);
                entity.render(gc);
            }
        }
    }


}


