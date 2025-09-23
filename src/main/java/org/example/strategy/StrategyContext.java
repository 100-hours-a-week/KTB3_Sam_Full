package org.example.strategy;

public class StrategyContext {
    VehicleStrategy vehicleStrategy;

    public void setVehicleStrategy(VehicleStrategy vehicleStrategy) {
        this.vehicleStrategy = vehicleStrategy;
    }

    public boolean executeMenu(int menuNumber, int totalKm) {
        return this.vehicleStrategy.executeMenu(menuNumber,totalKm);
    }
}
