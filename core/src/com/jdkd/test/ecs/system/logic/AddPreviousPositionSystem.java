package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jdkd.test.ecs.component.Movable;
import com.jdkd.test.ecs.component.Position;
import com.jdkd.test.ecs.component.PreviousPosition;

public class AddPreviousPositionSystem extends IteratingSystem implements LogicSystem {

    private ComponentMapper<Position> positionComponentMapper;

    public AddPreviousPositionSystem() {
        super(Aspect.all(Position.class, Movable.class).exclude(PreviousPosition.class));
    }

    @Override
    protected void process(int entityId) {
        Position position = positionComponentMapper.get(entityId);

        world.edit(entityId).add(new PreviousPosition(position.getX(), position.getY()));
    }
}
