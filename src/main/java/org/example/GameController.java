package org.example;

import org.example.enums.PlayResult;

public class GameController {
    public PlayResult playGame(Vehicle vehicle, int totalKm) {
        double accidentProbability = Math.random();
        return vehicle.play(accidentProbability,totalKm);
    }
}
