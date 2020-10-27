package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jdkd.test.ecs.component.PhysicalBody;

public class PositionUpdater extends IteratingSystem implements LogicSystem {

    private static final int SPEED = 2;
    private ComponentMapper<PhysicalBody> physicalBodyComponentMapper;

    public PositionUpdater() {
        super(Aspect.all(PhysicalBody.class));
    }

    @Override
    protected void process(int entityId) {
        PhysicalBody physicalBody = physicalBodyComponentMapper.get(entityId);

        int xMovement = 0;
        int yMovement = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yMovement += SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yMovement -= SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xMovement -= SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xMovement += SPEED;
        }

        physicalBody.getBody().setLinearVelocity(xMovement, yMovement);
    }
}
