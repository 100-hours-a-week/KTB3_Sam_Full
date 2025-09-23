package org.example.strategy;

import org.example.EnemyThread;

public class StrategyContext {
    VehicleStrategy vehicleStrategy;

    public void setVehicleStrategy(VehicleStrategy vehicleStrategy) {
        this.vehicleStrategy = vehicleStrategy;
    }

    public boolean executeMenu(int menuNumber, int totalKm, EnemyThread enemy) {
        return this.vehicleStrategy.executeMenu(menuNumber,totalKm, enemy);
    }
}
