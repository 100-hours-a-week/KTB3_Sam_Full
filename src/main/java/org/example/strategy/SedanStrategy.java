package org.example.strategy;

import org.example.EnemyThread;
import org.example.GameController;
import org.example.GameUI;
import org.example.Sedan;

public class SedanStrategy implements VehicleStrategy{
    private final double ATTACK_SUCCEED_PROBABILITY = 0.5;

    private final GameController controller;
    private final GameUI ui;
    private final Sedan sedan;

    public SedanStrategy(GameController controller, GameUI ui, Sedan sedan) {
        this.controller = controller;
        this.ui = ui;
        this.sedan = sedan;
    }

    @Override
    public boolean executeMenu(int menuNumber, int totalKm, EnemyThread enemy) {
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
            case 4 -> {
                if (sedan.getAttackCount() > 0) {
                    sedan.decreaseAttackCount();
                    if (Math.random() < ATTACK_SUCCEED_PROBABILITY) {
                        enemy.kill();
                        System.out.println("공격 성공! 적이 탈락했습니다. 당신의 승리!");
                        yield false;
                    } else {
                        System.out.println("공격 실패! 남은 공격 횟수: " + sedan.getAttackCount());
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
