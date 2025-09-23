package org.example;

import org.example.enums.PlayResult;

public abstract class Vehicle {
    private final int DURABILITY_INCREASEMENT_AFTER_FIX_WHEN_NOT_ACCIDENT = 15;

    protected int durability;
    protected int wearnessPerCount;
    protected int kmPerCount;
    protected double safetyProbability;
    protected int totalRunCount;
    protected int totalRunKm;
    protected int canFixCount;

    Vehicle(int wearnessPerCount, int kmPerCount, double safetyProbability,int canFixCount) {
        this.durability = 100;
        this.wearnessPerCount = wearnessPerCount;
        this.kmPerCount = kmPerCount;
        this.safetyProbability = safetyProbability;
        this.totalRunCount = 0;
        this.totalRunKm = 0;
        this.canFixCount = canFixCount;
    }

    public final PlayResult play(double accidentProbability, int totalKm) {
        if(accidentProbability > this.safetyProbability) {
            if(this.canFixCount > 0) {
                this.fix(true);
                return PlayResult.SUCCESS_ACCIDENT_FIX;
            } else {
                return PlayResult.FAIL_ACCIDENT_FIX;
            }
        } else {
            return handleMove(totalKm);
        }
    }

    protected abstract PlayResult handleMove(int totalKm);
    public abstract void chooseMenu();



    public void showActivityStatus() {
        System.out.println("남은 내구도는 "+durability+"입니다.");
        System.out.println("수리 가능 횟수는 "+canFixCount+"입니다.");
        System.out.println("현재 주행 총 거리는 "+totalRunKm+"입니다.");
        System.out.println("현재 주행 총 횟수는 "+totalRunCount+"입니다.");
    }

    public void showFixedAttributes() {
        System.out.println("해당 운송 수단의 주행당 내구도 감소량은 "+wearnessPerCount+"입니다.");
        System.out.println("해당 운송 수단이 안전할 확률은 "+safetyProbability+"입니다.");
    }

    public void showEndResults() {
        System.out.println("주행 총 거리는 "+totalRunKm+"입니다.");
        System.out.println("주행 총 횟수는 "+totalRunCount+"입니다.");
    }

    public void fix(boolean isAccident) {
        canFixCount--;
        if(!isAccident) {
            durability += DURABILITY_INCREASEMENT_AFTER_FIX_WHEN_NOT_ACCIDENT;
        }
    }
}
