package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jdkd.test.ecs.component.Position;

public class PositionUpdater extends IteratingSystem implements LogicSystem {

    public static final int SPEED = 5;
    private ComponentMapper<Position> positionComponentMapper;

    public PositionUpdater() {
        super(Aspect.all(Position.class));
    }

    @Override
    protected void process(int entityId) {
        Position position = positionComponentMapper.get(entityId);

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

        position.setX(position.getX() + xMovement);
        position.setY(position.getY() + yMovement);
    }
}
