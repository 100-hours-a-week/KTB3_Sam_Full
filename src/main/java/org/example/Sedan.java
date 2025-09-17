package org.example;

public class Sedan extends Car{
    Sedan() {
        super(8,10,5);
        isDriveFastMode = false;
    }
    private boolean isDriveFastMode;

    public void turnOnDriveFastMode() {
        this.isDriveFastMode = true;
        this.safetyLevel -= 2;
        this.kmPerCount *= 2;
    }

    public void turnOffDriveFastMode() {
        this.isDriveFastMode = false;
        this.safetyLevel +=2;
        this.kmPerCount /=2;
    }
}
