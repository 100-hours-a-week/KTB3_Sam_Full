package org.example.strategy;

import org.example.EnemyThread;

public interface VehicleStrategy {
    boolean executeMenu(int menuNumber, int totalKm, EnemyThread enemy);
}
