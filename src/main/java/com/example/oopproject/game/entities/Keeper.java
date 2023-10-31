package com.example.oopproject.game.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import com.example.oopproject.game.graphics.Sprite;

import static com.example.oopproject.game.Direction.DIRECTION.*;

public class Keeper extends AnimationEntity {
    private boolean isHit;

    public Keeper(int x, int y, Image image, boolean isHit) {
        super(x, y, image);
        this.isHit = isHit;
        sprite = Sprite.keeper;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    @Override
    public Sprite chooseSprite() {
        switch (direction) {
            case CENTER -> {
                //sprite = Sprite.keeper;
                sprite = Sprite.movingSprite(Sprite.keeper, Sprite.keeper_1, Sprite.keeper_2, animate, 20);
            }
            case LEFT -> {
                if (isHit) {
                    //sprite = Sprite.keeper_hit_left;
                    sprite = Sprite.movingSprite(Sprite.keeper_hit_left, Sprite.keeper_hit_left_1, Sprite.keeper_hit_letf_2, animate, 20);
                } else {
                    sprite = Sprite.movingSprite(Sprite.keeper_miss_left, Sprite.keeper_miss_left_1, Sprite.keeper_miss_letf_2, animate, 20);
                }
            }
            case RIGHT -> {
                if (isHit) {
                    //sprite = Sprite.keeper_hit_right;
                    sprite = Sprite.movingSprite(Sprite.keeper_hit_right, Sprite.keeper_hit_right_1, Sprite.keeper_hit_right_2, animate, 20);
                } else {
                    sprite = Sprite.movingSprite(Sprite.keeper_miss_right, Sprite.keeper_miss_right_1, Sprite.keeper_miss_right_2, animate, 20);
                }
            }
            default -> {
                //sprite = Sprite.keeper;
                sprite = Sprite.movingSprite(Sprite.keeper, Sprite.keeper_1, Sprite.keeper_2, animate, 20);            }
        }
        return sprite;
    }

    //        public Image chooseSprite() {
//            switch (direction) {
//                case 0:
//                    return Sprite.keeper.getFxImageOrigin();
//                case 1:
//                    return Sprite.player_right[indexOfFlex].getFxImageOrigin();
//                case 2:
//                    return Sprite.player_up[indexOfFlex].getFxImageOrigin();
//                default:
//                    return Sprite.player_down[indexOfFlex].getFxImageOrigin();
//            }
//        }

    @Override
    public void render(GraphicsContext gc) {
        img = chooseSprite().getFxImage();
        gc.drawImage(img, x, y);
    }

    @Override
    public void update() {
        if (isHit) {

        } else {

        }
    }
}
