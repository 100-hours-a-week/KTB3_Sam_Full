package org.example;

public class Airplane extends Vehicle{
    Airplane() {
        super(15,60,3,1);
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
}
