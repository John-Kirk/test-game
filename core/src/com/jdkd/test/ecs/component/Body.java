package com.jdkd.test.ecs.component;

import com.artemis.Component;

public class Body extends Component {

    private int width;
    private int height;

    public Body(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Body() {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
