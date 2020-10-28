package com.jdkd.test.ecs.system.g3d;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.jdkd.test.ecs.component.Decal3D;

public class DecalRenderingSystem extends BaseEntitySystem {

    private ComponentMapper<Decal3D> decal3DComponentMapper;

    private DecalBatch decalBatch;

    public DecalRenderingSystem(DecalBatch decalBatch) {
        super(Aspect.all(Decal3D.class));
        this.decalBatch = decalBatch;
    }

    @Override
    protected void processSystem() {
        IntBag entityIds = getEntityIds();

        for (int i = 0; i < entityIds.size(); i++) {
            int entityId = entityIds.get(i);
            decalBatch.add(decal3DComponentMapper.get(entityId).getDecal());
        }

        decalBatch.flush();
    }
}
