package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game carGame = new Game();
        Airplane airplane = new Airplane();
        Sedan sedan = new Sedan();
        Suv suv = new Suv();

        List vehicles = new ArrayList<Vehicle>();
        vehicles.add(airplane);
        vehicles.add(sedan);
        vehicles.add(suv);

        Map<String,Integer> map = new HashMap<>();
        map.put("서울-부산", 300);
        map.put("수원-화성", 30);
        List<Map.Entry<String, Integer>> mapList = map.entrySet()
            .stream()
            .toList();



        carGame.start();
        carGame.showMap(mapList);
        int mapIndexAnswer = sc.nextInt();
        int totalKm = mapList.get(mapIndexAnswer-1).getValue();

        carGame.showVehicles(vehicles);
        int carIndexAnswer = sc.nextInt();
        boolean flag = true;

        while(flag) {
            switch(carIndexAnswer) {
                case 1 -> {
                    carGame.chooseAirplaneMenu();
                    int menuNumber = sc.nextInt();
                    switch(menuNumber) {
                        case 0 -> flag = false;
                        case 1 -> flag = carGame.play(airplane,totalKm);
                    }
                }
                case 2 -> {
                    carGame.chooseSedanMenu();
                    int menuNumber = sc.nextInt();
                    switch(menuNumber) {
                        case 0 -> flag = false;
                        case 1 -> flag = carGame.play(sedan, totalKm);
                        case 2 -> {
                            if(sedan.checkDrivateFastMode()) {
                                System.out.println("이미 드라이브모드가 켜져있습니다. 다시 선택해주세요");
                            } else {
                                sedan.turnOnDriveFastMode();
                                flag = carGame.play(sedan, totalKm);
                            }
                        }
                        case 3 -> {
                            if(!sedan.checkDrivateFastMode()) {
                                System.out.println("이미 드라이브모드가 꺼져있습니다. 다시 선택해주세요");
                            } else {
                                sedan.turnOffDriveFastMode();
                                flag = carGame.play(sedan, totalKm);
                            }
                        }
                    }
                }
                case 3 -> {
                    carGame.chooseSuvMenu();
                    int menuNumber = sc.nextInt();
                    switch(menuNumber) {
                        case 0 -> flag = false;
                        case 1 -> flag = carGame.play(suv, totalKm);
                        case 2 -> {
                            suv.turnOnSafetyMode();
                            flag = carGame.play(suv, totalKm);
                        }
                        case 3 -> {
                            suv.turnOffSafetyMode();
                            flag = carGame.play(suv, totalKm);
                        }
                    }
                }
                default -> System.out.println("잘못된 번호를 입력했습니다.");
            }
        }

    }
}
