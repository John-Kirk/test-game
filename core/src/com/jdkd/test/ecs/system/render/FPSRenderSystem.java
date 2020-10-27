package com.jdkd.test.ecs.system.render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FPSRenderSystem extends BaseSystem {

    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private int count;

    public FPSRenderSystem(SpriteBatch spriteBatch, BitmapFont bitmapFont) {
        this.spriteBatch = spriteBatch;
        this.bitmapFont = bitmapFont;
    }

    @Override
    protected void processSystem() {
        count++;
        if (count > 60) {
            count = 0;
        }

        bitmapFont.draw(spriteBatch, Gdx.graphics.getFramesPerSecond() + " FPS", 3, Gdx.graphics.getHeight() - 3);
        bitmapFont.draw(spriteBatch, count + "" , 3, Gdx.graphics.getHeight() - 18);
    }
}
