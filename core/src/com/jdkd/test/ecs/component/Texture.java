package com.jdkd.test.ecs.component;

import com.artemis.Component;

public class Texture extends Component {

    private com.badlogic.gdx.graphics.Texture texture;

    public Texture(com.badlogic.gdx.graphics.Texture texture) {
        this.texture = texture;
    }

    public Texture() {
    }

    public com.badlogic.gdx.graphics.Texture getTexture() {
        return texture;
    }
}
