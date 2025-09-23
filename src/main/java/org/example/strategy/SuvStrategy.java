package org.example.strategy;

import org.example.EnemyThread;
import org.example.GameController;
import org.example.GameUI;
import org.example.Suv;

public class SuvStrategy implements VehicleStrategy{
    private final double ATTACK_SUCCEED_PROBABILITY = 0.5;

    private final GameController controller;
    private final GameUI ui;
    private final Suv suv;

    public SuvStrategy(GameController controller, GameUI ui, Suv suv) {
        this.controller = controller;
        this.ui = ui;
        this.suv = suv;
    }

    @Override
    public boolean executeMenu(int menuNumber, int totalKm, EnemyThread enemy) {
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
            case 4 -> {
                if (suv.getAttackCount() > 0) {
                    suv.decreaseAttackCount();
                    if (Math.random() < ATTACK_SUCCEED_PROBABILITY) {
                        enemy.kill();
                        System.out.println("공격 성공! 적이 탈락했습니다. 당신의 승리!");
                        yield false;
                    } else {
                        System.out.println("공격 실패! 남은 공격 횟수: " + suv.getAttackCount());
                        yield true;
                    }
                } else {
                    System.out.println("공격 기회를 모두 소진했습니다.");
                    yield true;
                }
            }
            default -> true;
        };
    }
}
