import java.io.*;
import java.util.*;

public class Puzzle4 {
    static int sum = 0;
    public static void main(String[] args) {

        InputStream inputStream = Puzzle1.class.getResourceAsStream("data4.txt");
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] split1 = line.split(":");
                    String[] split2 = split1[1].split("\\|");

                    String[] winningNumbers = split2[0].trim().split("\\s+");
                    String[] myNumbers = split2[1].trim().split("\\s+");

                    HashSet<Integer> winningNumbersSet = new HashSet<>();
                    HashSet<Integer> myNumberSet = new HashSet<>();

                    for (String winningNumber : winningNumbers) {
                        winningNumbersSet.add(Integer.parseInt(winningNumber));
                    }
                    for (String myNumber : myNumbers) {
                        myNumberSet.add(Integer.parseInt(myNumber));
                    }

                    winningNumbersSet.retainAll(myNumberSet);
                    int result;
                    int exponent = winningNumbersSet.size() - 1;
                    if (exponent == -1) {
                        result = 0;
                    } else {
                        result = (int) Math.pow(2, exponent);
                    }
                    sum += result;;

                }
                System.out.println(sum);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
