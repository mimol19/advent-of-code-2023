import java.io.*;
import java.util.*;

public class Puzzle3 {
    static Integer sum = 0;

    public static void main(String[] args) {
        InputStream inputStream = Puzzle1.class.getResourceAsStream("data3.txt");
        char[][] chars = new char[3][140];

        for (int i = 0; i < 140; i++) {
            chars[0][i] = '.';
        }

        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                chars[1] = reader.readLine().toCharArray();
                chars[2] = reader.readLine().toCharArray();


                Map<List<Integer>, String> map1 = createMapOfNumbers(chars);

                eraseValues(map1, chars);
                while ((line = reader.readLine()) != null) {
                    chars[0] = chars[1];
                    chars[1] = chars[2];
                    chars[2] = line.toCharArray();

                    Map<List<Integer>, String> map2 = createMapOfNumbers(chars);
                    eraseValues(map2, chars);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static Map<List<Integer>, String> createMapOfNumbers(char[][] chars) {
        Map<List<Integer>, String> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
            }
        }
        for (int i = 0; i < 140; i++) {
            char c = chars[1][i];
            StringBuilder number;
            List<Integer> place = new LinkedList<>();
            if (Character.isDigit(c)) {
                number = new StringBuilder(Character.toString(c));
                place.add(i);
                for (int j = i + 1; j < 140; j++) {
                    if (Character.isDigit(chars[1][j])) {
                        number.append(Character.toString(chars[1][j]));
                        place.add(j);
                        i++;
                    } else {
                        break;
                    }
                }
                map.put(place, number.toString());
            }
        }
        return map;
    }

    private static void eraseValues(Map<List<Integer>, String> map, char[][] chars) {
        List<List<Integer>> keysToRemove = new ArrayList<>();
        outerloop:

        for (List<Integer> integers : map.keySet()) {
            List<Integer> list = new LinkedList<>(integers);
            if (integers.getFirst() != 0) {
                list.addFirst(integers.getFirst() - 1);
            }
            if (integers.getLast() != 139) {
                list.addLast(integers.getLast() + 1);
            }
            for (Integer integer : list) {
                if (
                        !((chars[0][integer] == '.' || Character.isDigit(chars[0][integer]))
                                && (chars[1][integer] == '.' || Character.isDigit(chars[1][integer]))
                                && (chars[2][integer] == '.' || Character.isDigit(chars[2][integer])))

                ) {
                    continue outerloop;
                }
            }
            keysToRemove.add(integers);
        }
        for (List<Integer> integers : keysToRemove) {
            map.remove(integers);
        }
        for (String value : map.values()) {
            sum += Integer.valueOf(value);
        }
    }
}
