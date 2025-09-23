package org.example;

public class TimeThread extends Thread{
    @Override
    public void run() {
        int second = 0;
        try {
            while(true) {
                Thread.sleep(1000);
                second++;
                System.out.println("[시스템] - 현재 게임을 실행한 지 "+second+"초 지났습니다.");
            }
        } catch(Exception e) {
            System.out.println("시간 스레드 종료");
        }
    }
}
