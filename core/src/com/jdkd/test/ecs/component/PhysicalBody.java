package com.jdkd.test.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicalBody extends Component {

    private Body body;

    public PhysicalBody(Body body) {
        this.body = body;
    }

    public PhysicalBody() {
    }

    public Body getBody() {
        return body;
    }
}
