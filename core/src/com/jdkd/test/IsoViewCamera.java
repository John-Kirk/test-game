package com.jdkd.test;

import com.badlogic.gdx.graphics.PerspectiveCamera;

public class IsoViewCamera extends PerspectiveCamera {

    public IsoViewCamera(float viewPortWidth, float viewPortHeight) {
        super(30, viewPortWidth, viewPortHeight);
    }
}