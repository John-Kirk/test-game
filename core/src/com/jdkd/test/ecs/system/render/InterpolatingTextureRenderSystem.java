package com.jdkd.test.ecs.system.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jdkd.test.ecs.ECSState;
import com.jdkd.test.ecs.component.PhysicalBody;
import com.jdkd.test.ecs.component.PreviousPosition;
import com.jdkd.test.ecs.component.Sprite;
import com.jdkd.test.ecs.component.Texture;
import com.jdkd.test.util.UnitConverter;

import static com.badlogic.gdx.math.MathUtils.floor;
import static com.badlogic.gdx.math.MathUtils.lerp;

public class InterpolatingTextureRenderSystem extends IteratingSystem {

    private ComponentMapper<Texture> textureComponentMapper;
    private ComponentMapper<PhysicalBody> physicalBodyComponentMapper;
    private ComponentMapper<PreviousPosition> previousPositionComponentMapper;
    private ComponentMapper<Sprite> bodyComponentMapper;

    private final SpriteBatch spriteBatch;
    private final ECSState ecsState;
    private final UnitConverter unitConverter;

    public InterpolatingTextureRenderSystem(SpriteBatch spriteBatch, ECSState ecsState, UnitConverter unitConverter) {
        super(Aspect.all(Texture.class, PhysicalBody.class, PreviousPosition.class, Sprite.class));
        this.spriteBatch = spriteBatch;
        this.ecsState = ecsState;
        this.unitConverter = unitConverter;
    }

    @Override
    protected void process(int entityId) {
        Texture texture = textureComponentMapper.get(entityId);
        PhysicalBody physicalBody = physicalBodyComponentMapper.get(entityId);
        PreviousPosition previousPosition = previousPositionComponentMapper.get(entityId);
        Sprite sprite = bodyComponentMapper.get(entityId);

        Vector2 position = physicalBody.getBody().getPosition();
        float width = sprite.getWidth();
        float height = sprite.getHeight();

        int x = floor(lerp(unitConverter.convertMetersToPixels(previousPosition.getX()) - width / 2, unitConverter.convertMetersToPixels(position.x) - width / 2, ecsState.getAlpha()));
        int y = floor(lerp(unitConverter.convertMetersToPixels(previousPosition.getY()) - height / 2, unitConverter.convertMetersToPixels(position.y) - height / 2, ecsState.getAlpha()));

        spriteBatch.draw(texture.getTexture(), x, y, sprite.getWidth(), sprite.getHeight());
    }
}
