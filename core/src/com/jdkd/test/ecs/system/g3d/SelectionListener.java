package com.jdkd.test.ecs.system.g3d;

import com.badlogic.gdx.math.collision.Ray;

public interface SelectionListener {

    boolean handleClick(Ray ray, int button);

}
