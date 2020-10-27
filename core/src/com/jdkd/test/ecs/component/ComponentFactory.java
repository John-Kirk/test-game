package com.jdkd.test.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.physics.box2d.*;
import com.jdkd.test.util.UnitConverter;

public class ComponentFactory {

    private final World box2D;
    private final UnitConverter unitConverter;

    public ComponentFactory(World box2D, UnitConverter unitConverter) {
        this.box2D = box2D;
        this.unitConverter = unitConverter;
    }

    public Component createBody(int x, int y, int width, int height, boolean isStatic) {
        BodyDef.BodyType bodyType = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
        return new PhysicalBody(createBody(bodyType, x, y, width, height));
    }

    public Component createSprite(String textureReference, int width, int height) {
        return new Sprite(textureReference, width, height);
    }

    public Component createMovable() {
        return new Movable();
    }

    private Body createBody(BodyDef.BodyType bodyType, int x, int y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(unitConverter.convertPixelsToMeters(x + width / 2), unitConverter.convertPixelsToMeters(y + height / 2));
        bodyDef.fixedRotation = true;

        Body body = box2D.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(unitConverter.convertPixelsToMeters(width / 2), unitConverter.convertPixelsToMeters(height / 2));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);

        bodyShape.dispose();

        return body;
    }

}
