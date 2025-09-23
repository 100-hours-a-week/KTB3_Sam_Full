package org.example.strategy;

import org.example.GameController;
import org.example.GameUI;
import org.example.Suv;

public class SuvStrategy implements VehicleStrategy{
    private final GameController controller;
    private final GameUI ui;
    private final Suv suv;

    public SuvStrategy(GameController controller, GameUI ui, Suv suv) {
        this.controller = controller;
        this.ui = ui;
        this.suv = suv;
    }

    @Override
    public boolean executeMenu(int menuNumber, int totalKm) {
        return switch (menuNumber) {
            case 0 -> false;
            case 1 -> ui.showPlayResults(suv,controller.playGame(suv,totalKm));
            case 2 -> {
                if(suv.checkSafetyMode()) {
                    System.out.println("이미 안전모드가 켜져있습니다. 다시 선택해주세요");
                    yield true;
                } else {
                    suv.turnOnSafetyMode();
                    yield ui.showPlayResults(suv,controller.playGame(suv,totalKm));
                }
            }
            case 3 -> {
                if(!suv.checkSafetyMode()) {
                    System.out.println("이미 안전모드가 꺼져있습니다. 다시 선택해주세요");
                    yield true;
                } else {
                    suv.turnOffSafetyMode();
                    yield ui.showPlayResults(suv,controller.playGame(suv,totalKm));
                }
            }
            default -> true;
        };
    }
}
