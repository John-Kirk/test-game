package com.jdkd.test.ecs.component;

import com.artemis.Component;

public class PreviousPosition extends Component {

    private int x;
    private int y;

    public PreviousPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PreviousPosition() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
