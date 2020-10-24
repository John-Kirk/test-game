package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jdkd.test.ecs.component.Position;

public class PositionUpdater extends IteratingSystem implements LogicSystem {

    private ComponentMapper<Position> positionComponentMapper;

    public PositionUpdater() {
        super(Aspect.all(Position.class));
    }

    @Override
    protected void process(int entityId) {
        Position position = positionComponentMapper.get(entityId);

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            position.setX(position.getX() + 2);
        }
    }
}
