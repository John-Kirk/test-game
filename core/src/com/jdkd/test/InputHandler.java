package com.jdkd.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.jdkd.test.ecs.system.g3d.SelectionListener;

import java.util.ArrayList;
import java.util.List;

public class InputHandler extends CameraInputController {

    private List<SelectionListener> selectionListeners;

    public InputHandler(IsoViewCamera camera) {
        super(camera);
        this.selectionListeners = new ArrayList<>();
    }

    @Override
    public boolean touchUp(float x, float y, int pointer, int button) {
        Ray pickRay = camera.getPickRay(x, y);
        selectionListeners.forEach(selectionListener -> selectionListener.handleClick(pickRay, button));
        return super.touchUp(x, y, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isLongPressed(1.5f)) {
            return super.touchDragged(screenX, screenY, pointer);
        }

        return false;
    }

    @Override
    public boolean touchDragged(float x, float y, int pointer) {
        if (isLongPressed(1.5f)) {
            return super.touchDragged(x, y, pointer);
        }

        return false;
    }

    public void addSelectionListener(SelectionListener selectionListener) {
        this.selectionListeners.add(selectionListener);
    }
}
