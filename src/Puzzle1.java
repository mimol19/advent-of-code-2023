import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Puzzle1 {
    public static void main(String[] args) {

        InputStream inputStream = Puzzle1.class.getResourceAsStream("data.txt");

        Map<String, Integer> digitList = new HashMap<>();
        digitList.put("zero", 0);
        digitList.put("one", 1);
        digitList.put("two", 2);
        digitList.put("three", 3);
        digitList.put("four", 4);
        digitList.put("five", 5);
        digitList.put("six", 6);
        digitList.put("seven", 7);
        digitList.put("eight", 8);
        digitList.put("nine", 9);

        if (inputStream == null) {
            System.out.println("Plik nie został znaleziony!");
            return;
        }
        int sum = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String first = "";
                String last = "";

                for (
                        int i = 0; i < line.length(); i++) {
                    char currentChar = line.charAt(i);
                    if (Character.isDigit(currentChar)) {
                        first = Character.toString(currentChar);
                        break;
                    } else {
                        String s = line.substring(0, i);
                        Integer number = contains(digitList, s);
                    }
                }
                for (
                        int i = line.length() - 1; i >= 0; i--) {
                    char x = line.charAt(i);
                    if (Character.isDigit(x)) {
                        last = Character.toString(x);
                        break;
                    }
                }
                String x = first + last;
                int value = Integer.parseInt(x);

                sum += value;


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

    }

    private static Integer contains(Map<String, Integer> digitList, String s) {

        for (String i : digitList.keySet()) {
            if (s.contains(i)) {
                return digitList.get(i);
            }
        }
        return null;
    }

}