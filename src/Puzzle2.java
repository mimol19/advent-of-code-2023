import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle2 {
    static int greenNumber = 13;
    static int redNumber = 12;
    static int blueNumber = 14;

    public static void main(String[] args) {


        InputStream inputStream = Puzzle1.class.getResourceAsStream("data2.txt");

        if (inputStream == null) {
            System.out.println("Plik nie został znaleziony!");
            return;
        }
        int sum = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] sets = line.split(":");

                if (Puzzle2.isPossible(sets[1])) {
                    int gameNumber = Puzzle2.getGameNumber(sets[0]);
                    sum += gameNumber;
                }
            }
            System.out.println(sum);


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean isPossible(String line ) {
        String[] sets = line.split(";");

        for (String s : sets) {
            List<String> list = new ArrayList<>(Arrays.asList(s.split(",")));

            for (String string : list) {
                string = string.trim();
                String[] parts = string.split(" ");
                int number = Integer.parseInt(parts[0]);
                String color = parts[1];
                if (color.contains("green") && number > greenNumber) {
                    return false;
                }
                else if (color.contains("red") && number > redNumber) {
                    return false;
                }
                if (color.contains("blue") && number > blueNumber) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public static int getGameNumber(String line) {
        String[] split= line.trim().split(" ");
        return Integer.parseInt(split[1]);
    }
}
