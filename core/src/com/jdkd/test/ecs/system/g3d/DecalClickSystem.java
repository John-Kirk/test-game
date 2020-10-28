package com.jdkd.test.ecs.system.g3d;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.jdkd.test.ecs.component.Decal3D;

public class DecalClickSystem extends BaseEntitySystem implements SelectionListener {

    private ComponentMapper<Decal3D> decal3DComponentMapper;
    private Decal lastSelectedDecal;

    public DecalClickSystem() {
        super(Aspect.all(Decal3D.class));
    }

    @Override
    protected void processSystem() {

    }

    @Override
    public boolean handleClick(Ray ray, int button) {

        if (Input.Buttons.LEFT == button) {
            IntBag entityIds = getEntityIds();
            Vector3 intersection = new Vector3();
            Intersector.intersectRayPlane(ray, new Plane(new Vector3(0, 1, 0), 0), intersection);

            System.out.println(intersection);

            for (int i = 0; i < entityIds.size(); i++) {
                int entityId = entityIds.get(i);

                Decal decal = decal3DComponentMapper.get(entityId).getDecal();

                if (decal.getRotation().x > 0) {

                    float[] vertices = decal.getVertices();
                    float minX = Float.MAX_VALUE;
                    float maxX = Float.MIN_VALUE;
                    float minZ = Float.MAX_VALUE;
                    float maxZ = Float.MIN_VALUE;


                    for (int j = 0; j < vertices.length; j++) {
                        if (j == 0 || j == 6 || j == 12 || j == 18) {
                            float tempX = vertices[j];
                            if (tempX < minX) {
                                minX = tempX;
                            }
                            if (tempX > maxX) {
                                maxX = tempX;
                            }
                        }

                        if (j == 2 || j == 8 || j == 14 || j == 20) {
                            float tempZ = vertices[j];
                            if (tempZ < minZ) {
                                minZ = tempZ;
                            }
                            if (tempZ > maxZ) {
                                maxZ = tempZ;
                            }
                        }
                    }

                    BoundingBox boundingBox = new BoundingBox();
                    boundingBox.set(new Vector3(minX, 0, minZ), new Vector3(maxX, 0, maxZ));

                    if (boundingBox.contains(intersection)) {
                        if (lastSelectedDecal != null) {
                            lastSelectedDecal.setColor(1, 1, 1, 1);
                        }
                        decal.setColor(0.5f, 0, 0, 1f);
                        lastSelectedDecal = decal;
                        break;
                    }
                }

            }
        }

        return false;
    }
}
