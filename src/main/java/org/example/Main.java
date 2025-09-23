package org.example;

import org.example.dto.MapEntryDto;
import org.example.strategy.AirplaneStrategy;
import org.example.strategy.SedanStrategy;
import org.example.strategy.StrategyContext;
import org.example.strategy.SuvStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController carGameController = new GameController();
        GameUI carGameUI = new GameUI();
        Airplane airplane = new Airplane();
        Sedan sedan = new Sedan();
        Suv suv = new Suv();

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(airplane);
        vehicles.add(sedan);
        vehicles.add(suv);

        Map<String,Integer> map = new HashMap<>();
        map.put("서울-부산", 300);
        map.put("수원-화성", 30);
        List<MapEntryDto> mapList = MapEntryDto.from(map);

        carGameUI.showStartMenu();

        int mapIndexAnswer;
        do {
            carGameUI.showMap(mapList);
            mapIndexAnswer = sc.nextInt();
        } while(mapIndexAnswer < 1 || mapIndexAnswer > mapList.size());
        int totalKm = mapList.get(mapIndexAnswer-1).distance();


        int carIndexAnswer;
        do {
            carGameUI.showVehicles(vehicles);
            carIndexAnswer = sc.nextInt();
        }while(carIndexAnswer < 1 || carIndexAnswer > vehicles.size());
        Vehicle chosenVehicle = vehicles.get(carIndexAnswer-1);

        StrategyContext strategyContext = new StrategyContext();
        switch(carIndexAnswer) {
            case 1 -> strategyContext.setVehicleStrategy(new AirplaneStrategy(carGameController,carGameUI,airplane));
            case 2 -> strategyContext.setVehicleStrategy(new SedanStrategy(carGameController,carGameUI,sedan));
            case 3 -> strategyContext.setVehicleStrategy(new SuvStrategy(carGameController,carGameUI,suv));
        }

        TimeThread timeThread = new TimeThread();
        timeThread.setDaemon(true);
        timeThread.start();

        EnemyThread enemy = new EnemyThread(totalKm);
        enemy.start();

        boolean flag = true;
        while(flag) {
            if (!enemy.isEnemyAlive()) {
                System.out.println("적이 탈락했습니다. 당신의 승리!");
                break;
            }
            if (enemy.isAttackSucceed()) {
                System.out.println("적의 공격에 맞아 패배했습니다.");
                break;
            }
            if (enemy.getCurrentKm() >= totalKm) {
                System.out.println("적이 먼저 결승선에 도착했습니다. 당신의 패배!");
                break;
            }

            chosenVehicle.chooseMenu();
            int menuNumber = sc.nextInt();
            flag = strategyContext.executeMenu(menuNumber,totalKm);
        }
    }
}
