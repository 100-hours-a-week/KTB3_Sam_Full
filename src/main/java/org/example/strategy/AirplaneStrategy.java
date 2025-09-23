package org.example.strategy;

import org.example.Airplane;
import org.example.GameController;
import org.example.GameUI;

public class AirplaneStrategy implements VehicleStrategy{
    private final GameController controller;
    private final GameUI ui;
    private final Airplane airplane;

    public AirplaneStrategy(GameController controller, GameUI ui, Airplane airplane) {
        this.controller = controller;
        this.ui = ui;
        this.airplane = airplane;
    }

    @Override
    public boolean executeMenu(int menuNumber, int totalKm) {
        return switch(menuNumber) {
            case 0 -> false;
            case 1 -> ui.showPlayResults(airplane,controller.playGame(airplane,totalKm));
            default -> true;
        };
    }
}
