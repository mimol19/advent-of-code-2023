import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Puzzle9 {
    public static void main(String[] args) {
        InputStream inputStream = Puzzle9.class.getResourceAsStream("data9.txt");
        List<List<Integer>> report = new ArrayList<>();
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> reportLine = new LinkedList<>();
                String[] split = line.split(" ");
                for (String string : split) {
                    reportLine.add(Integer.valueOf(string));
                }
                report.add(reportLine);
            }

            for (List<Integer> reportLine : report) {
                Integer nextValue = predictNextValue(reportLine);
                sum += nextValue;
            }
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer predictNextValue(List<Integer> list) {
        List<List<Integer>> sequenceList = new LinkedList<>();
        sequenceList.add(list);
        while (!(list.stream().allMatch(value -> value == 0))) {
            List<Integer> finalList = list;
            List<Integer> differenceList = IntStream.range(0, list.size() - 1).map(i -> finalList.get(i + 1) - finalList.get(i))
                    .boxed().toList();

            sequenceList.add(differenceList);
            list = differenceList;
        }

        int predictedDifference = 0;

        for (int i = sequenceList.size()-1; i >=0 ; i--) {
            Integer last = sequenceList.get(i).getLast();
            predictedDifference += last;
        }
        return predictedDifference;
    }
}
