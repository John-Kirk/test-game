package com.jdkd.test.ecs;

import com.artemis.BaseSystem;
import com.artemis.SystemInvocationStrategy;
import com.artemis.utils.BitVector;
import com.jdkd.test.ecs.system.logic.LogicSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FixedTimeStepInvocationStrategy extends SystemInvocationStrategy {

    private final List<BaseSystem> logicSystems;
    private final List<BaseSystem> renderSystems;
    private final BitVector disabledLogicSystems = new BitVector();
    private final BitVector disabledRenderSystem = new BitVector();
    private final ECSState ecsState;

    private long nanosPerLogicTick;
    private long currentTime = System.nanoTime();
    private long accumulator;
    private boolean systemsSorted = false;

    public FixedTimeStepInvocationStrategy(ECSState ecsState) {
        this(40, ecsState);
    }

    @Override
    protected void initialize() {
        if (!systemsSorted) {
            sortSystems();
        }
    }

    public FixedTimeStepInvocationStrategy(int millisPerLogicTick, ECSState ecsState) {
        nanosPerLogicTick = TimeUnit.MILLISECONDS.toNanos(millisPerLogicTick);
        logicSystems = new ArrayList<>();
        renderSystems = new ArrayList<>();
        this.ecsState = ecsState;
    }

    private void sortSystems() {
        if (!systemsSorted) {
            Object[] systemsData = systems.getData();
            for (int i = 0, s = systems.size(); s > i; i++) {
                BaseSystem system = (BaseSystem) systemsData[i];
                if (system instanceof LogicSystem) {
                    logicSystems.add(system);
                } else {
                    renderSystems.add(system);
                }
            }
            systemsSorted = true;
        }
    }

    @Override
    protected void process() {
        if (!systemsSorted) {
            sortSystems();
        }

        long newTime = System.nanoTime();
        long frameTime = newTime - currentTime;

        if (frameTime > 250000000) {
            frameTime = 250000000;
        }

        currentTime = newTime;
        accumulator += frameTime;
        updateEntityStates();
		world.setDelta(nanosPerLogicTick * 0.000000001f);

        while (accumulator >= nanosPerLogicTick) {
            for (int i = 0; i < logicSystems.size(); i++) {
                if (disabledLogicSystems.get(i)) {
                    continue;
                }
                logicSystems.get(i).process();
                updateEntityStates();
            }

            accumulator -= nanosPerLogicTick;
        }

		world.setDelta(frameTime * 0.000000001f);
		ecsState.setAlpha((float) accumulator / nanosPerLogicTick);

        for (int i = 0; i < renderSystems.size(); i++) {
            if (disabledRenderSystem.get(i)) {
                continue;
            }
            renderSystems.get(i).process();
            updateEntityStates();
        }
    }

    @Override
    public boolean isEnabled(BaseSystem target) {
        List<BaseSystem> systems = (target instanceof LogicSystem) ? logicSystems : renderSystems;
        BitVector disabledSystems = (target instanceof LogicSystem) ? disabledLogicSystems : disabledRenderSystem;
        Class targetClass = target.getClass();
        for (int i = 0; i < systems.size(); i++) {
            if (targetClass == systems.get(i).getClass())
                return !disabledSystems.get(i);
        }
        throw new RuntimeException("System not found in this world");
    }

    @Override
    public void setEnabled(BaseSystem target, boolean value) {
        List<BaseSystem> systems = (target instanceof LogicSystem) ? logicSystems : renderSystems;
        BitVector disabledSystems = (target instanceof LogicSystem) ? disabledLogicSystems : disabledRenderSystem;
        Class targetClass = target.getClass();
        for (int i = 0; i < systems.size(); i++) {
            if (targetClass == systems.get(i).getClass()) {
                disabledSystems.set(i, !value);
                break;
            }
        }
    }
}