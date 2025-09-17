package org.example;

public class Car extends Vehicle{
    protected int numberOfTires;
    protected int tireWearnessPerCount;
    protected int tireDurability;

    Car(int wearnessPerCount, int kmPerCount, int safetyLevel) {
        super(wearnessPerCount,kmPerCount,safetyLevel,4);
        this.numberOfTires = 4;
        this.tireWearnessPerCount = 20;
        this.tireDurability = 100;
    }

    public boolean drive(int totalKm) {
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

    public void changeTires() {
        this.canFixCount--;
        this.tireDurability = 100;
    }

}
