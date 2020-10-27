package com.jdkd.test.ecs.system.logic;

import com.artemis.BaseSystem;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsUpdateSystem extends BaseSystem implements LogicSystem {

    private final World box2D;

    public PhysicsUpdateSystem(World box2D) {
        this.box2D = box2D;
    }

    @Override
    protected void processSystem() {
        box2D.step(0.04f, 6,2 );
    }
}
