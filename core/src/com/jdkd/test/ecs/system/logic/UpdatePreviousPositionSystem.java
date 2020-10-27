package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.jdkd.test.ecs.component.PhysicalBody;
import com.jdkd.test.ecs.component.PreviousPosition;

public class UpdatePreviousPositionSystem extends IteratingSystem implements LogicSystem {

    private ComponentMapper<PhysicalBody> physicalBodyComponentMapper;
    private ComponentMapper<PreviousPosition> previousPositionComponentMapper;

    public UpdatePreviousPositionSystem() {
        super(Aspect.all(PhysicalBody.class, PreviousPosition.class));
    }

    @Override
    protected void process(int entityId) {
        Body body = physicalBodyComponentMapper.get(entityId).getBody();
        PreviousPosition previousPosition = previousPositionComponentMapper.get(entityId);

        previousPosition.setX(body.getPosition().x);
        previousPosition.setY(body.getPosition().y);
    }
}
