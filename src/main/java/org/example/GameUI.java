package org.example;

import org.example.dto.MapEntryDto;
import org.example.enums.PlayResult;

import java.util.List;

public class GameUI {
    public void showStartMenu() {
        printLine();
        System.out.println("안녕하십니까. 자동차 미니게임에 오신것을 환영합니다.");
        System.out.println("원하는 거리를 가진 맵과, 운송수단을 골라 도착지까지 완주해보세요!");
        printLine();
    }

    public void showMap(List<MapEntryDto> mapList) {
        System.out.println("다음 맵 중 원하는 번호를 눌러주세요!");
        for(int i =0; i<mapList.size(); i++) {
            System.out.print((i+1)+". "+mapList.get(i).mapName() + ":"+mapList.get(i).distance()+ "km ");
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

    public boolean showPlayResults(Vehicle vehicle, PlayResult playResult) {
        switch(playResult) {
            case SUCCESS_MOVE -> showSuccessResultAfterMove(vehicle);
            case SUCCESS_ACCIDENT_FIX -> showSuccessResultsAfterAccident(vehicle);
            case SUCCESS_TIRE_FIX -> showSuccessResultsAfterTireFix();
            case SUCCESS_ALL_OVER -> showEndResulsAllOver(vehicle);
            case FAIL_ACCIDENT_FIX -> showEndResultsAfterAccident(vehicle);
            case FAIL_LACK_VEHICLE_DURABILITY -> showEndResultsLackOfDurability(vehicle);
            case FAIL_TIRE_FIX -> showEndResultsLackOfTireFix(vehicle);
        }
        return playResult.getValue();
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
