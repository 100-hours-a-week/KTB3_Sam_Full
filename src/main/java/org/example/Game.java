package org.example;

import java.util.List;
import java.util.Map;

public class Game {
    public void start() {
        printLine();
        System.out.println("안녕하십니까. 자동차 미니게임에 오신것을 환영합니다.");
        System.out.println("원하는 거리를 가진 맵과, 운송수단을 골라 도착지까지 완주해보세요!");
        printLine();
    }

    public void showMap(List<Map.Entry<String, Integer>> mapList) {
        System.out.println("다음 맵 중 원하는 번호를 눌러주세요!");
        for(int i =0; i<mapList.size(); i++) {
            System.out.print((i+1)+". "+mapList.get(i).getKey() + ":"+mapList.get(i).getValue()+ "km ");
            System.out.println("");
        }
        printLine();
    }

    public void showVehicles(List<Vehicle> vehicles) {
        System.out.println("현재 사용 가능한 운송 수단음 다음과 같습니다.");
        System.out.println("다음 운송 수단 중 원하는 번호를 눌러주세요!");
        System.out.println("");
        int index = 1;
        for(Vehicle vehicle : vehicles) {
            System.out.println(index + ". "+vehicle.getClass().getSimpleName());
            vehicle.showFixedAttributes();
            vehicle.showActivityStatus();
            System.out.println();
            index++;
        }
        printLine();
    }

    public boolean play(Airplane airplane, int totalKm) {
        int accidentLevel = (int) (Math.random() * 10) + 1; //1~10
        if(accidentLevel > airplane.safetyLevel) {
            if(airplane.canFixCount > 0) {
                airplane.fix(true);
                showSuccessResultsAfterAccident(airplane);
                return true;
            } else {
                showEndResultsAfterAccident(airplane);
                return false;
            }
        } else {
            if(airplane.fly()) {
                if(totalKm <= airplane.totalRunKm) {
                    showEndResulsAllOver(airplane);
                    return false;
                } else {
                    showSuccessResultAfterMove(airplane);
                    return true;
                }
            } else {
                showEndResultsLackOfDurability(airplane);
                return false;
            }
        }
    }

    public boolean play(Sedan sedan, int totalKm) {
        int accidentLevel = (int) (Math.random() * 10) + 1; //1~10
        if(accidentLevel > sedan.safetyLevel) {
            if(sedan.canFixCount > 0) {
                sedan.fix(true);
                showSuccessResultsAfterAccident(sedan);
                return true;
            } else {
                showEndResultsAfterAccident(sedan);
                return false;
            }
        } else {
            if(sedan.drive(totalKm)) {
                if(totalKm <= sedan.totalRunKm) {
                    showEndResulsAllOver(sedan);
                    return false;
                } else {
                    showSuccessResultAfterMove(sedan);
                    return true;
                }
            } else if(sedan.tireDurability <= sedan.tireWearnessPerCount) {
                if(sedan.canFixCount >0) {
                    sedan.changeTires();
                    showSuccessResultsAfterTireFix();
                    return true;
                } else {
                    showEndResultsLackOfTireFix(sedan);
                    return false;
                }
            }
            else {
                showEndResultsLackOfDurability(sedan);
                return false;
            }
        }
    }

    public boolean play(Suv suv, int totalKm) {
        int accidentLevel = (int) (Math.random() * 10) + 1; //1~10
        if(accidentLevel > suv.safetyLevel) {
            if(suv.canFixCount > 0) {
                suv.fix(true);
                showSuccessResultsAfterAccident(suv);
                return true;
            } else {
                showEndResultsAfterAccident(suv);
                return false;
            }
        } else {
            if(suv.drive(totalKm)) {
                if(totalKm <= suv.totalRunKm) {
                    showEndResulsAllOver(suv);
                    return false;
                } else {
                    showSuccessResultAfterMove(suv);
                    return true;
                }
            } else if(suv.tireDurability <= suv.tireWearnessPerCount) {
                if(suv.canFixCount >0) {
                    suv.changeTires();
                    showSuccessResultsAfterTireFix();
                    return true;
                } else {
                    showEndResultsLackOfTireFix(suv);
                    return false;
                }
            }
            else {
                showEndResultsLackOfDurability(suv);
                return false;
            }
        }
    }

    public void chooseAirplaneMenu() {
        System.out.println("비행기 운영팀입니다. 원하시는 메뉴를 선택해주세요");
        System.out.println("0.운행 종료  1.운행");
    }

    public void chooseSedanMenu() {
        System.out.println("세단 운영팀입니다. 원하시는 메뉴를 선택해주세요");
        System.out.println("0.운행 종료  1.운행  2.드라이브 모드 켜고 운행  3.드라이브 모드 끄고 운행");
    }

    public void chooseSuvMenu() {
        System.out.println("Suv 운영팀입니다. 원하시는 메뉴를 선택해주세요");
        System.out.println("0.운행 종료  1.운행  2.안전 모드 켜고 운행  3.안전 모드 끄고 운행");
    }

    private void showSuccessResultAfterMove(Vehicle vehicle) {
        System.out.println("사고없이 운행에 성공했습니다!");
        vehicle.showActivityStatus();
        printLine();
    }

    private void showSuccessResultsAfterAccident(Vehicle vehicle) {
        System.out.println("사고가 났지만 수리 횟수를 소모해 수리가 완료되었습니다!");
        System.out.println("");
        vehicle.showActivityStatus();
        printLine();
    }

    private void showSuccessResultsAfterTireFix() {
        System.out.println("타이어 수리가 완료되었습니다!");
        printLine();
    }

    private void showEndResultsAfterAccident(Vehicle vehicle) {
        System.out.println("사고가 났지만 남은 잔여 수리 횟수가 없어 프로그램을 종료합니다.");
        System.out.println("");
        vehicle.showEndResults();
    }

    private void showEndResultsLackOfDurability(Vehicle vehicle) {
        System.out.println("내구도가 부족해 운행을 종료합니다.");
        vehicle.showEndResults();
    }

    private void showEndResulsAllOver(Vehicle vehicle) {
        System.out.println("무사히 주행을 마쳤습니다. 운행을 종료합니다.");
        System.out.println("");
        vehicle.showEndResults();
    }

    private void showEndResultsLackOfTireFix(Vehicle vehicle) {
        System.out.println("타이어가 수명을 다했지만 남은 잔여 수리 횟수가 없어 프로그램을 종료합니다.");
        vehicle.showEndResults();
    }

    private void printLine() {
        System.out.println("-".repeat(70));
        System.out.println("-".repeat(70));
    }
}
