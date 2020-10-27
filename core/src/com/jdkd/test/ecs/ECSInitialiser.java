package com.jdkd.test.ecs;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jdkd.test.ecs.system.logic.*;
import com.jdkd.test.ecs.system.render.FPSRenderSystem;
import com.jdkd.test.ecs.system.render.InterpolatingTextureRenderSystem;
import com.jdkd.test.ecs.system.render.StaticTextureRenderSystem;
import com.jdkd.test.ecs.system.render.TextureLoadingSystem;
import com.jdkd.test.gfx.TextureLoader;
import com.jdkd.test.util.UnitConverter;

public class ECSInitialiser {

    private final SpriteBatch spriteBatch;
    private final TextureLoader textureLoader;
    private final UnitConverter unitConverter;

    public ECSInitialiser(SpriteBatch spriteBatch, TextureLoader textureLoader, UnitConverter unitConverter) {
        this.spriteBatch = spriteBatch;
        this.textureLoader = textureLoader;
        this.unitConverter = unitConverter;
    }

    public World init(com.badlogic.gdx.physics.box2d.World box2D) {
        ECSState ecsState = new ECSState();

        WorldConfigurationBuilder worldConfigurationBuilder = new WorldConfigurationBuilder()
                .with(new AddPreviousPositionSystem())
                .with(new PhysicsUpdateSystem(box2D))
                .with(new UpdatePreviousPositionSystem())
                .with(new TextureLoadingSystem(textureLoader))
                .with(new StaticTextureRenderSystem(spriteBatch, unitConverter))
                .with(new PositionUpdater())
                .with(new InterpolatingTextureRenderSystem(spriteBatch, ecsState, unitConverter))
                .with(new FPSRenderSystem(spriteBatch, new BitmapFont()))
                .with(new InputSystem())
                .register(new FixedTimeStepInvocationStrategy(ecsState));

        return new World(worldConfigurationBuilder.build());
    }
}
