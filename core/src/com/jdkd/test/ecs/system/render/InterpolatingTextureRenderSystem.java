package com.jdkd.test.ecs.system.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.jdkd.test.ecs.ECSState;
import com.jdkd.test.ecs.component.Body;
import com.jdkd.test.ecs.component.Position;
import com.jdkd.test.ecs.component.PreviousPosition;
import com.jdkd.test.ecs.component.Texture;

import static com.badlogic.gdx.math.MathUtils.floor;
import static com.badlogic.gdx.math.MathUtils.lerp;

public class InterpolatingTextureRenderSystem extends IteratingSystem {

    private ComponentMapper<Texture> textureComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<PreviousPosition> previousPositionComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;

    private final SpriteBatch spriteBatch;
    private final ECSState ecsState;

    public InterpolatingTextureRenderSystem(SpriteBatch spriteBatch, ECSState ecsState) {
        super(Aspect.all(Texture.class, Position.class, PreviousPosition.class, Body.class));
        this.spriteBatch = spriteBatch;
        this.ecsState = ecsState;
    }

    @Override
    protected void process(int entityId) {
        Texture texture = textureComponentMapper.get(entityId);
        Position position = positionComponentMapper.get(entityId);
        PreviousPosition previousPosition = previousPositionComponentMapper.get(entityId);
        Body body = bodyComponentMapper.get(entityId);

        int x = floor(lerp(previousPosition.getX(), position.getX(), ecsState.getAlpha()));
        int y = floor(lerp(previousPosition.getY(), position.getY(), ecsState.getAlpha()));

        System.out.println(x + ":" + y);

        spriteBatch.draw(texture.getTexture(), x, y, body.getWidth(), body.getHeight());
    }
}
