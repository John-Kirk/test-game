package com.jdkd.test.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g3d.decals.Decal;

public class Decal3D extends Component {

    private Decal decal;

    public Decal3D(Decal decal) {
        this.decal = decal;
    }

    public Decal3D() {
    }

    public Decal getDecal() {
        return decal;
    }

    public void setDecal(Decal decal) {
        this.decal = decal;
    }
}
