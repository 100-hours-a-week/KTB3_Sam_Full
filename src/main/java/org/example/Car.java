package org.example;

import org.example.enums.PlayResult;

public abstract class Car extends Vehicle{
    protected int numberOfTires;
    protected int tireWearnessPerCount;
    protected int tireDurability;

    Car(int wearnessPerCount, int kmPerCount, double safetyProbability) {
        super(wearnessPerCount,kmPerCount,safetyProbability,4);
        this.numberOfTires = 4;
        this.tireWearnessPerCount = 20;
        this.tireDurability = 100;
    }

    public void changeTires() {
        this.canFixCount--;
        this.tireDurability = 100;
    }

    @Override
    protected PlayResult handleMove(int totalKm) {
        if(this.drive(totalKm)) {
            if(totalKm <= this.totalRunKm) {
                return PlayResult.SUCCESS_ALL_OVER;
            } else {
                return PlayResult.SUCCESS_MOVE;
            }
        } else if(this.tireDurability <= this.tireWearnessPerCount) {
            if(this.canFixCount >0) {
                this.changeTires();
                return PlayResult.SUCCESS_TIRE_FIX;
            } else {
                return PlayResult.FAIL_TIRE_FIX;
            }
        }
        else {
            return PlayResult.FAIL_LACK_VEHICLE_DURABILITY;
        }
    }

    private boolean drive(int totalKm) {
        if(this.tireDurability >= this.tireWearnessPerCount && this.durability >= this.wearnessPerCount) {
            this.durability -= this.wearnessPerCount;
            this.totalRunKm += this.kmPerCount;
            this.totalRunCount++;
            this.tireDurability -= this.tireWearnessPerCount;

            if(totalRunKm > totalKm) totalRunKm = totalKm;
            return true;
        } else {
            return false;
        }
    }
}
