package com.jdkd.test.ecs.component;

import com.artemis.Component;

public class PreviousPosition extends Component {

    private float x;
    private float y;

    public PreviousPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PreviousPosition() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
