package com.jdkd.test.ecs.system.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jdkd.test.ecs.component.Texture;
import com.jdkd.test.ecs.component.TextureReference;
import com.jdkd.test.gfx.TextureLoader;

public class TextureLoadingSystem extends IteratingSystem {

    private ComponentMapper<TextureReference> textureReferenceComponentMapper;
    private TextureLoader textureLoader;

    public TextureLoadingSystem(TextureLoader textureLoader) {
        super(Aspect.all(TextureReference.class));
        this.textureLoader = textureLoader;
    }

    @Override
    protected void process(int entityId) {
        TextureReference reference = textureReferenceComponentMapper.get(entityId);
        Texture texture = new Texture(textureLoader.getTexture(reference.getReference()));

        this.getWorld().edit(entityId).add(texture);
    }
}
