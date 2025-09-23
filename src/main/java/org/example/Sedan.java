package org.example;

public class Sedan extends Car{
    private final double SAFE_PROBABILITY_CHANGE_WHEN_DRIVE_MODE_ON = 0.2;

    Sedan() {
        super(8,10,0.5);
        isDriveFastMode = false;
    }
    private boolean isDriveFastMode;

    public void turnOnDriveFastMode() {
        this.isDriveFastMode = true;
        this.safetyProbability -= SAFE_PROBABILITY_CHANGE_WHEN_DRIVE_MODE_ON;
        this.kmPerCount *= 2;
    }

    public void turnOffDriveFastMode() {
        this.isDriveFastMode = false;
        this.safetyProbability += SAFE_PROBABILITY_CHANGE_WHEN_DRIVE_MODE_ON;
        this.kmPerCount /=2;
    }

    public boolean checkDrivateFastMode() {
        return this.isDriveFastMode;
    }

    @Override
    public void chooseMenu() {
        System.out.println("세단 운영팀입니다. 원하시는 메뉴를 선택해주세요");
        System.out.println("0.운행 종료  1.운행  2.드라이브 모드 켜고 운행  3.드라이브 모드 끄고 운행  4.적 공격");
    }
}
