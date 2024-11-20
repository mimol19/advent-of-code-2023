import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Puzzle10 {
    static String[][] board = new String[140][140];

    public static void main(String[] args) {
        InputStream inputStream = Puzzle9.class.getResourceAsStream("data10.txt");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("");
                board[i] = split;
                i++;
            }
            for (int j = 0; j < 140; j++) {
                for (int k = 0; k < 140; k++) {
                    if (board[j][k].equals("S")) {

                        int halfWay = findNextMove(j, k);
                        System.out.println(halfWay);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findNextMove(int j, int k) {
        int previousJ = j;
        int previousK = k;
        if (board[j][k+1].equals("7") || board[j][k+1].equals("J") || board[j][k+1].equals("-")) {
            k++;
        } else if (board[j][k-1].equals("F") || board[j][k-1].equals("L") || board[j][k-1].equals("-")) {
            k--;
        } else if (board[j-1][k].equals("F") || board[j-1][k].equals("7") || board[j-1][k].equals("|")) {
            j--;
        } else if (board[j+1][k].equals("L") || board[j+1][k].equals("J") || board[j+1][k].equals("|")) {
            j++;
        }
        int count = 1;
        while (!(board[j][k].equals("S"))) {
            switch (board[j][k]) {
                case "F" -> {
                    if (previousJ > j) {
                        previousJ = j;
                        k++;
                    } else if (previousK > k) {
                        previousK = k;
                        j++;
                    }
                }
                case "7" -> {
                    if (previousJ > j) {
                        previousJ = j;
                        k--;
                    } else if (previousK < k) {
                        previousK = k;
                        j++;
                    }
                }
                case "J" -> {
                    if (previousJ < j) {
                        previousJ = j;
                        k--;
                    } else if (previousK < k) {
                        previousK = k;
                        j--;
                    }
                }
                case "L" -> {
                    if (previousJ < j) {
                        previousJ = j;
                        k++;
                    } else if (previousK > k) {
                        previousK = k;
                        j--;
                    }
                }
                case "|" -> {
                    if (previousJ < j) {
                        previousJ = j;
                        j++;
                    } else if (previousJ > j) {
                        previousJ = j;
                        j--;
                    }
                }
                case "-" -> {
                    if (previousK < k) {
                        previousK = k;
                        k++;
                    } else if (previousK > k) {
                        previousK = k;
                        k--;
                    }
                }
            }
            count++;
        }
        return count / 2;
    }

}
