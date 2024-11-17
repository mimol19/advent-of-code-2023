import java.util.HashMap;
import java.util.Map;

public class Puzzle6 {
    public static void main(String[] args) {
        int sum =1;
        Map<Integer, Integer> mapOfRaces = new HashMap<>();
        mapOfRaces.put(55,246);
        mapOfRaces.put(82,1441);
        mapOfRaces.put(64,1012);
        mapOfRaces.put(90,1111);

        for (Map.Entry<Integer, Integer> integerIntegerEntry : mapOfRaces.entrySet()) {
            sum *= getCombinationNumber(integerIntegerEntry.getKey(),integerIntegerEntry.getValue());
        }

        System.out.println(sum);
    }

    private static int getCombinationNumber(int time, int distanceRecord) {
        int combinationNumber = 0;
        int buttonPushTime = 0;

        for (int i = 0; i < time; i++) {
            buttonPushTime += 1;
            if ((time -buttonPushTime) * buttonPushTime > distanceRecord){
                combinationNumber += 1;
            }
        }
        return combinationNumber;
    }
}
