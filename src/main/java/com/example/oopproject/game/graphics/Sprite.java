package com.example.oopproject.game.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static final int DEFAULT_SIZE = 16;
    /*
    public static final int KEEPER_SIZE = 50;
    public static final int SOCCER_SIZE = 16;
    */
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    protected int realWidth, realHeight;
    private SpriteSheet sheet;

    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        realWidth = rw;
        realHeight = rh;
        load();
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet._pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    public static Sprite keeper = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 12, 16);
    public static Sprite keeper_1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 12, 16);
    public static Sprite keeper_2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 16);

    public static Sprite keeper_hit_left = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_hit_left_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_hit_letf_2 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 16, 16);

    public static Sprite keeper_hit_right = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_hit_right_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_hit_right_2 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 16, 16);

    public static Sprite keeper_miss_left = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_miss_left_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_miss_letf_2 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 16, 16);

    public static Sprite keeper_miss_right = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_miss_right_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite keeper_miss_right_2 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 16, 16);

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        if (animate % time < time / 3) return normal;
        if (animate % time < time * 2 / 3) return x1;
        return x2;
    }

//    public Image getFxImage() {
//        WritableImage wr = new WritableImage(SIZE, SIZE);
//        PixelWriter pw = wr.getPixelWriter();
//        for (int x = 0; x < SIZE; x++) {
//            for (int y = 0; y < SIZE; y++) {
//                if (pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
//                    pw.setArgb(x, y, 0);
//                } else {
//                    pw.setArgb(x, y, pixels[x + y * SIZE]);
//                }
//            }
//        }
//        Image input = new ImageView(wr).getImage();
//        return input;
//    }

    public Image getFxImage() {
        BufferedImage image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    image.setRGB(x, y, 0);
                } else {
                    image.setRGB(x, y, pixels[x + y * SIZE]);
                }
            }
        }
        return null;

    }
}
