package com.jdkd.test.ecs.system.g3d;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.jdkd.test.ecs.component.*;

public class DecalCreationSystem extends IteratingSystem {

    private ComponentMapper<Position3D> position3DComponentMapper;
    private ComponentMapper<Rotation3D> rotation3DComponentMapper;
    private ComponentMapper<Sprite> spriteComponentMapper;
    private ComponentMapper<Texture> textureComponentMapper;

    public DecalCreationSystem() {
        super(Aspect.all(Position3D.class, Rotation3D.class, Sprite.class, Texture.class).exclude(Decal3D.class));
    }

    @Override
    protected void process(int entityId) {
        Position3D position = position3DComponentMapper.get(entityId);
        Rotation3D rotation = rotation3DComponentMapper.get(entityId);
        Sprite sprite = spriteComponentMapper.get(entityId);
        Texture texture = textureComponentMapper.get(entityId);

        Decal decal = Decal.newDecal(sprite.getWidth(), sprite.getHeight(), new TextureRegion(texture.getTexture()), true);
        decal.setPosition(position.getX(), position.getY(), position.getZ());

        if (rotation.getX() > 0) {
            decal.setRotationX(rotation.getX());
        }

        if (rotation.getY() > 0) {
            decal.setRotationY(rotation.getY());
        }

        if (rotation.getZ() > 0) {
            decal.setRotationZ(rotation.getZ());
        }

        world.edit(entityId).add(new Decal3D(decal));
    }
}
