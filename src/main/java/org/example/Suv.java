package org.example;

public class Suv extends Car{
    Suv() {
        super(12,10,7);
        this.isSafetyMode = false;
    }
    private boolean isSafetyMode;

    public void turnOnSafetyMode() {
        this.isSafetyMode = true;
        this.wearnessPerCount +=10;
        this.safetyLevel +=2;
    }

    public void turnOffSafetyMode() {
        this.isSafetyMode = false;
        this.wearnessPerCount -=10;
        this.safetyLevel -=2;
    }

}
