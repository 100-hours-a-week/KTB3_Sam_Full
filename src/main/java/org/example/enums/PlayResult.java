package org.example.enums;

public enum PlayResult {
    SUCCESS_MOVE(true),
    SUCCESS_ACCIDENT_FIX(true),
    SUCCESS_ALL_OVER(false),
    SUCCESS_TIRE_FIX(true),
    FAIL_LACK_VEHICLE_DURABILITY(false),
    FAIL_TIRE_FIX(false),
    FAIL_ACCIDENT_FIX(false);

    private final boolean isGameKeepGoing;

    PlayResult(boolean isGameKeepGoing) {
        this.isGameKeepGoing = isGameKeepGoing;
    }

    public boolean getValue() {
        return this.isGameKeepGoing;
    }
}
