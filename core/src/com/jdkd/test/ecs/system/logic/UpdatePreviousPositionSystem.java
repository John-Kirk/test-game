package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jdkd.test.ecs.component.Position;
import com.jdkd.test.ecs.component.PreviousPosition;

public class UpdatePreviousPositionSystem extends IteratingSystem implements LogicSystem {

    private ComponentMapper<Position> positionComponentMapper;
    private ComponentMapper<PreviousPosition> previousPositionComponentMapper;

    public UpdatePreviousPositionSystem() {
        super(Aspect.all(Position.class, PreviousPosition.class));
    }

    @Override
    protected void process(int entityId) {
        Position position = positionComponentMapper.get(entityId);
        PreviousPosition previousPosition = previousPositionComponentMapper.get(entityId);

        previousPosition.setX(position.getX());
        previousPosition.setY(position.getY());
    }
}
