package org.example.strategy;

import org.example.Airplane;
import org.example.EnemyThread;
import org.example.GameController;
import org.example.GameUI;

public class AirplaneStrategy implements VehicleStrategy{
    private final double ATTACK_SUCCEED_PROBABILITY = 0.5;

    private final GameController controller;
    private final GameUI ui;
    private final Airplane airplane;

    public AirplaneStrategy(GameController controller, GameUI ui, Airplane airplane) {
        this.controller = controller;
        this.ui = ui;
        this.airplane = airplane;
    }

    @Override
    public boolean executeMenu(int menuNumber, int totalKm, EnemyThread enemy) {
        return switch(menuNumber) {
            case 0 -> false;
            case 1 -> ui.showPlayResults(airplane,controller.playGame(airplane,totalKm));
            case 2 -> {
                if (airplane.getAttackCount() > 0) {
                    airplane.decreaseAttackCount();
                    if (Math.random() < ATTACK_SUCCEED_PROBABILITY) {
                        enemy.kill();
                        System.out.println("공격 성공! 적이 탈락했습니다. 당신의 승리!");
                        yield false;
                    } else {
                        System.out.println("공격 실패! 남은 공격 횟수: " + airplane.getAttackCount());
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
