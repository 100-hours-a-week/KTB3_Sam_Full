package org.example;

import org.example.enums.PlayResult;

public class Airplane extends Vehicle{
    Airplane() {
        super(15,60,0.3,1);
        this.numberOfWings = 2;
    }
    private int numberOfWings;

    public Boolean fly() {
        if(this.durability >= this.wearnessPerCount) {
            this.durability -= this.wearnessPerCount;
            this.totalRunKm += this.kmPerCount;
            this.totalRunCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected PlayResult handleMove(int totalKm) {
        if(this.fly()) {
            if(totalKm <= this.totalRunKm) {
                return PlayResult.SUCCESS_ALL_OVER;
            } else {
                return PlayResult.SUCCESS_MOVE;
            }
        } else {
            return PlayResult.FAIL_LACK_VEHICLE_DURABILITY;
        }
    }

    @Override
    public void chooseMenu() {
        System.out.println("비행기 운영팀입니다. 원하시는 메뉴를 선택해주세요");
        System.out.println("0.운행 종료  1.운행");
    }
}
