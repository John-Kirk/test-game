package com.jdkd.test.ecs.system.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jdkd.test.ecs.ECSState;
import com.jdkd.test.ecs.component.Body;
import com.jdkd.test.ecs.component.Position;
import com.jdkd.test.ecs.component.PreviousPosition;
import com.jdkd.test.ecs.component.Texture;

public class StaticTextureRenderSystem extends IteratingSystem {

    private ComponentMapper<Texture> textureComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<Body> bodyComponentMapper;

    private final SpriteBatch spriteBatch;

    public StaticTextureRenderSystem(SpriteBatch spriteBatch) {
        super(Aspect.all(Texture.class, Position.class, Body.class).exclude(PreviousPosition.class));
        this.spriteBatch = spriteBatch;
    }

    @Override
    protected void process(int entityId) {
        Texture texture = textureComponentMapper.get(entityId);
        Position position = positionComponentMapper.get(entityId);
        Body body = bodyComponentMapper.get(entityId);

        spriteBatch.draw(texture.getTexture(), position.getX(), position.getY(), body.getWidth(), body.getHeight());
    }
}
