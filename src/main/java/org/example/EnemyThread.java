package org.example;

public class EnemyThread extends Thread{
    private final int totalKm;
    private int currentKm = 0;
    private final int kmPerCount = 10;
    private final double safetyProbability = 0.4;
    private final double attackProbability = 0.2;
    public volatile boolean isAlive = true;
    public volatile boolean isAttackSucceed = false;

    public EnemyThread(int totalKm) {
        this.totalKm = totalKm;
    }

    @Override
    public void run() {
        while(isAlive && currentKm < totalKm) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }

            currentKm += kmPerCount;
            System.out.println("[적] 현재 주행 거리 : "+ currentKm);

            if(Math.random() > safetyProbability) {
                isAlive = false;
                System.out.println("[적] 사고가 발생해 적이 탈락했습니다!");
                break;
            }

            if(Math.random() < attackProbability) {
                System.out.println("[적] 적이 당신을 공격했습니다!");
                System.out.println("치명상을 입어 운행이 불가합니다. 프로그램을 종료합니다.");
                isAttackSucceed = true;
                break;
            }
        }
    }

    public int getCurrentKm() {
        return this.currentKm;
    }

    public boolean isEnemyAlive() {
        return this.isAlive;
    }

    public boolean isAttackSucceed() {
        return this.isAttackSucceed;
    }

    public void kill() {
        this.isAlive = false;
        System.out.println("[적] 공격을 받아 쓰러졌습니다.");
    }
}
