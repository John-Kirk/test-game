package com.jdkd.test.ecs.system.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jdkd.test.ecs.component.PhysicalBody;
import com.jdkd.test.ecs.component.PreviousPosition;
import com.jdkd.test.ecs.component.Sprite;
import com.jdkd.test.ecs.component.Texture;
import com.jdkd.test.util.UnitConverter;

public class StaticTextureRenderSystem extends IteratingSystem {

    private ComponentMapper<Texture> textureComponentMapper;
    private ComponentMapper<PhysicalBody> physicalBodyComponentMapper;
    private ComponentMapper<Sprite> bodyComponentMapper;

    private final SpriteBatch spriteBatch;
    private final UnitConverter unitConverter;

    public StaticTextureRenderSystem(SpriteBatch spriteBatch, UnitConverter unitConverter) {
        super(Aspect.all(Texture.class, PhysicalBody.class, Sprite.class).exclude(PreviousPosition.class));
        this.spriteBatch = spriteBatch;
        this.unitConverter = unitConverter;
    }

    @Override
    protected void process(int entityId) {
        Texture texture = textureComponentMapper.get(entityId);
        Vector2 position = physicalBodyComponentMapper.get(entityId).getBody().getPosition();
        Sprite sprite = bodyComponentMapper.get(entityId);

        float width = sprite.getWidth();
        float height = sprite.getHeight();

        spriteBatch.draw(texture.getTexture(), unitConverter.convertMetersToPixels(position.x) - width / 2, unitConverter.convertMetersToPixels(position.y) - height / 2, width, height);
    }
}
