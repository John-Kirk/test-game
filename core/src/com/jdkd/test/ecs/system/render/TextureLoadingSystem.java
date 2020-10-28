package com.jdkd.test.ecs.system.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jdkd.test.ecs.component.Sprite;
import com.jdkd.test.ecs.component.Texture;
import com.jdkd.test.gfx.TextureLoader;

public class TextureLoadingSystem extends IteratingSystem {

    private ComponentMapper<Sprite> spriteComponentMapper;
    private final TextureLoader textureLoader;

    public TextureLoadingSystem(TextureLoader textureLoader) {
        super(Aspect.all(Sprite.class).exclude(Texture.class));
        this.textureLoader = textureLoader;
    }

    @Override
    protected void process(int entityId) {
        Sprite sprite = spriteComponentMapper.get(entityId);
        Texture texture = new Texture(textureLoader.getTexture(sprite.getTextureReference()));

        this.getWorld().edit(entityId).add(texture);
    }
}
