package com.jdkd.test.ecs.component;

import com.artemis.Component;

public class Sprite extends Component {

    private String textureReference;
    private int width;
    private int height;

    public Sprite(String textureReference, int width, int height) {
        this.textureReference = textureReference;
        this.width = width;
        this.height = height;
    }

    public Sprite() {
    }

    public String getTextureReference() {
        return textureReference;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
