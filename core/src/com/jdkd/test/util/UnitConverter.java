package com.jdkd.test.util;

public class UnitConverter {

    private final int pixelsToMeters;

    public UnitConverter(int pixelsToMeters) {
        this.pixelsToMeters = pixelsToMeters;
    }

    public float convertPixelsToMeters(float pixels) {
        return pixels / pixelsToMeters;
    }

    public float convertMetersToPixels(float meters) {
        return meters * pixelsToMeters;
    }
}
