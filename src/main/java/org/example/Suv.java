package org.example;

public class Suv extends Car{
    private final double SAFE_PROBABILITY_CHANGE_WHEN_SAFETY_MODE_ON = 0.2;
    private final int VEHICLE_WEARNESS_PER_DRIVE = 10;

    Suv() {
        super(12,10,0.7);
        this.isSafetyMode = false;
    }
    private boolean isSafetyMode;

    public void turnOnSafetyMode() {
        this.isSafetyMode = true;
        this.wearnessPerCount += VEHICLE_WEARNESS_PER_DRIVE;
        this.safetyProbability += SAFE_PROBABILITY_CHANGE_WHEN_SAFETY_MODE_ON;
    }

    public void turnOffSafetyMode() {
        this.isSafetyMode = false;
        this.wearnessPerCount -= VEHICLE_WEARNESS_PER_DRIVE;
        this.safetyProbability -= SAFE_PROBABILITY_CHANGE_WHEN_SAFETY_MODE_ON;
    }

    public boolean checkSafetyMode() {
        return this.isSafetyMode;
    }

    @Override
    public void chooseMenu() {
        System.out.println("Suv 운영팀입니다. 원하시는 메뉴를 선택해주세요");
        System.out.println("0.운행 종료  1.운행  2.안전 모드 켜고 운행  3.안전 모드 끄고 운행");
    }
}
