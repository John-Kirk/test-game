package com.jdkd.test.ecs;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jdkd.test.ecs.system.logic.AddPreviousPositionSystem;
import com.jdkd.test.ecs.system.logic.InputSystem;
import com.jdkd.test.ecs.system.logic.PositionUpdater;
import com.jdkd.test.ecs.system.logic.UpdatePreviousPositionSystem;
import com.jdkd.test.ecs.system.render.FPSRenderSystem;
import com.jdkd.test.ecs.system.render.InterpolatingTextureRenderSystem;
import com.jdkd.test.ecs.system.render.StaticTextureRenderSystem;
import com.jdkd.test.ecs.system.render.TextureLoadingSystem;
import com.jdkd.test.gfx.TextureLoader;

public class ECSInitialiser {

    private SpriteBatch spriteBatch;
    private TextureLoader textureLoader;
    private World world;

    public ECSInitialiser(SpriteBatch spriteBatch, TextureLoader textureLoader) {
        this.spriteBatch = spriteBatch;
        this.textureLoader = textureLoader;
    }

    public World init() {
        ECSState ecsState = new ECSState();

        WorldConfigurationBuilder worldConfigurationBuilder = new WorldConfigurationBuilder()
                .with(new AddPreviousPositionSystem())
                .with(new UpdatePreviousPositionSystem())
                .with(new TextureLoadingSystem(textureLoader))
                .with(new StaticTextureRenderSystem(spriteBatch))
                .with(new PositionUpdater())
                .with(new InterpolatingTextureRenderSystem(spriteBatch, ecsState))
                .with(new FPSRenderSystem(spriteBatch, new BitmapFont()))
                .with(new InputSystem())
                .register(new FixedTimeStepInvocationStrategy(ecsState));

        return new World(worldConfigurationBuilder.build());
    }
}
