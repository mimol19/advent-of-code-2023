import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Puzzle8 {
    public static void main(String[] args) {
        Map<String, InstructionPair> instructionMap = new HashMap<>();
        List<String> instructionList = new LinkedList<>();

        InputStream inputStream = Puzzle7.class.getResourceAsStream("data8.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            String[] split = reader.readLine().split("");
            for (String s : split) {
                instructionList.add(s);
            }
            reader.readLine(); //skip empty line
            while ((line = reader.readLine()) != null) {
                String[] split1 = line.split("");
                String key = split1[0] + split1[1] + split1[2];
                String left = split1[7] + split1[8] + split1[9];
                String right = split1[12] + split1[13] + split1[14];
                InstructionPair instructionPair = new InstructionPair(left, right);
                instructionMap.put(key, instructionPair);

            }
            int index = 0;
            int count = 1;
            InstructionPair instructionPair = instructionMap.get("AAA");
            while (true) {
                String direction = instructionList.get(index);
                String instruction = "";
                if (direction.equals("L")) {
                    instruction = instructionPair.getLeft();
                } else if (direction.equals("R")) {
                    instruction = instructionPair.getRight();
                }
                if (instruction.equals("ZZZ")) {
                    break;
                }

                instructionPair = instructionMap.get(instruction);
                index = (index + 1) % instructionList.size();
                count += 1;
            }
            System.out.println(count);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class InstructionPair {
        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "InstructionPair{" +
                    left + '\'' +
                    ",'" + right + '\'' +
                    '}';
        }

        String left;
        String right;

        public InstructionPair(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }
}
