package org.example;

public class Vehicle {
    protected int durability;
    protected int wearnessPerCount;
    protected int kmPerCount;
    protected int safetyLevel;
    protected int totalRunCount;
    protected int totalRunKm;
    protected int canFixCount;

    Vehicle(int wearnessPerCount, int kmPerCount, int safetyLevel,int canFixCount) {
        this.durability = 100;
        this.wearnessPerCount = wearnessPerCount;
        this.kmPerCount = kmPerCount;
        this.safetyLevel = safetyLevel;
        this.totalRunCount = 0;
        this.totalRunKm = 0;
        this.canFixCount = canFixCount;
    }

    public void showActivityStatus() {
        System.out.println("남은 내구도는 "+durability+"입니다.");
        System.out.println("수리 가능 횟수는 "+canFixCount+"입니다.");
        System.out.println("현재 주행 총 거리는 "+totalRunKm+"입니다.");
        System.out.println("현재 주행 총 횟수는 "+totalRunCount+"입니다.");
    }

    public void showFixedAttributes() {
        System.out.println("해당 운송 수단의 주행당 내구도 감소량은 "+wearnessPerCount+"입니다.");
        System.out.println("해당 운송 수단의 안전 레벨은 "+safetyLevel+"입니다.");
    }

    public void showEndResults() {
        System.out.println("주행 총 거리는 "+totalRunKm+"입니다.");
        System.out.println("주행 총 횟수는 "+totalRunCount+"입니다.");
    }

    public void fix(boolean isAccident) {
        canFixCount--;
        if(!isAccident) {
            durability += 15;
        }
    }
}
