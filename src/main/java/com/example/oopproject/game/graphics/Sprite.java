package com.example.oopproject.game.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static final int DEFAULT_SIZE = 35;
    public static final int KEEPER_SIZE = 120;
    private static final int TRANSPARENT_COLOR = 0xffffff;
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet._pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    public static Sprite keeper = new Sprite(KEEPER_SIZE, 0, 0, SpriteSheet.soccer);

    public static Sprite keeper_left_1 = new Sprite(KEEPER_SIZE, 1, 1, SpriteSheet.soccer);
    public static Sprite keeper_letf_2 = new Sprite(KEEPER_SIZE, 2, 1, SpriteSheet.soccer);
    public static Sprite keeper_letf_3 = new Sprite(KEEPER_SIZE, 3, 1, SpriteSheet.soccer);
    public static Sprite keeper_letf_4 = new Sprite(KEEPER_SIZE, 4, 1, SpriteSheet.soccer);


    public static Sprite keeper_right_1 = new Sprite(KEEPER_SIZE, 1, 0, SpriteSheet.soccer);
    public static Sprite keeper_right_2 = new Sprite(KEEPER_SIZE, 2, 0, SpriteSheet.soccer);
    public static Sprite keeper_right_3 = new Sprite(KEEPER_SIZE, 3, 0, SpriteSheet.soccer);
    public static Sprite keeper_right_4 = new Sprite(KEEPER_SIZE, 4, 0, SpriteSheet.soccer);

    public static Sprite[] keeper_left = {keeper_left_1, keeper_letf_2, keeper_letf_3, keeper_letf_4};
    public static Sprite[] keeper_right = {keeper_right_1, keeper_right_2, keeper_right_3, keeper_right_4};

    public static Sprite ball = new Sprite(KEEPER_SIZE, 0, 3, SpriteSheet.soccer);

    public static Sprite grass = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles);
    public static Sprite foot_print = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles);
    public static Sprite question_mark = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles);
    public static Sprite honey = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles);


    public static Sprite bear1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles);
    public static Sprite bear2 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles);

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        if (animate % time < time / 2) return x1;
        else return x2;
    }

    public static Sprite soccerMovingSprite(Sprite[] sprites, int animate, int time) {
        int n = sprites.length;
        for (int i = 0; i < n; i++) {
            if (animate % time < (i + 1) * time / 5) return sprites[i];
        }
        return sprites[n-1];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return input;
    }

}
