package org.example.strategy;

import org.example.GameController;
import org.example.GameUI;
import org.example.Sedan;

public class SedanStrategy implements VehicleStrategy{
    private final GameController controller;
    private final GameUI ui;
    private final Sedan sedan;

    public SedanStrategy(GameController controller, GameUI ui, Sedan sedan) {
        this.controller = controller;
        this.ui = ui;
        this.sedan = sedan;
    }

    @Override
    public boolean executeMenu(int menuNumber, int totalKm) {
        return switch (menuNumber) {
            case 0 -> false;
            case 1 -> ui.showPlayResults(sedan,controller.playGame(sedan,totalKm));
            case 2 -> {
                if(sedan.checkDrivateFastMode()) {
                    System.out.println("이미 드라이브모드가 켜져있습니다. 다시 선택해주세요");
                    yield true;
                } else {
                    sedan.turnOnDriveFastMode();
                    yield ui.showPlayResults(sedan,controller.playGame(sedan,totalKm));
                }
            }
            case 3 -> {
                if(!sedan.checkDrivateFastMode()) {
                    System.out.println("이미 드라이브모드가 꺼져있습니다. 다시 선택해주세요");
                    yield true;
                } else {
                    sedan.turnOffDriveFastMode();
                    yield ui.showPlayResults(sedan,controller.playGame(sedan,totalKm));
                }
            }
            default -> true;
        };
    }
}
