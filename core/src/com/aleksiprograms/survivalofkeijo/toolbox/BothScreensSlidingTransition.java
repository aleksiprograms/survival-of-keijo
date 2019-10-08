package com.aleksiprograms.survivalofkeijo.toolbox;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;

public class BothScreensSlidingTransition implements ScreenTransition {

    float width1 = 0;
    float height1 = 0;
    float width2 = 0;
    float height2 = 0;
    float x1 = 0;
    float y1 = 0;
    float x2 = 0;
    float y2 = 0;

    public enum Direction {
        LEFT, RIGHT
    }

    private Direction direction;
    private Interpolation interpolation;

    public BothScreensSlidingTransition(Direction direction, Interpolation interpolation) {
        this.direction = direction;
        this.interpolation = interpolation;
    }

    @Override
    public void render (Batch batch, Texture currentScreenTexture, Texture nextScreenTexture, float percent) {
        width1 = currentScreenTexture.getWidth();
        height1 = currentScreenTexture.getHeight();
        width2 = nextScreenTexture.getWidth();
        height2 = nextScreenTexture.getHeight();
        if (interpolation != null) percent = interpolation.apply(percent);

        switch (direction) {
            case LEFT:
                x1 = -width1 * percent;
                x2 = width2 * (1 - percent);
                break;
            case RIGHT:
                x1 = width1 * percent;
                x2 = -width2 * (1 - percent);
                break;
        }

        batch.begin();
        batch.draw(currentScreenTexture, x1, y1, 0, 0, width1, height1, 1, 1, 0, 0, 0, (int)width1, (int)height1, false, true);
        batch.draw(nextScreenTexture, x2, y2, 0, 0, width2, height2, 1, 1, 0, 0, 0, (int)width2, (int)height2, false, true);
        batch.end();
    }
}