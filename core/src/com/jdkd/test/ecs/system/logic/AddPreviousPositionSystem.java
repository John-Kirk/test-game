package com.jdkd.test.ecs.system.logic;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.jdkd.test.ecs.component.Movable;
import com.jdkd.test.ecs.component.PhysicalBody;
import com.jdkd.test.ecs.component.PreviousPosition;

public class AddPreviousPositionSystem extends IteratingSystem implements LogicSystem {

    private ComponentMapper<PhysicalBody> physicalBodyComponentMapper;

    public AddPreviousPositionSystem() {
        super(Aspect.all(PhysicalBody.class, Movable.class).exclude(PreviousPosition.class));
    }

    @Override
    protected void process(int entityId) {
        PhysicalBody physicalBody = physicalBodyComponentMapper.get(entityId);
        Body body = physicalBody.getBody();

        world.edit(entityId).add(new PreviousPosition(body.getPosition().x, body.getPosition().y));
    }
}
