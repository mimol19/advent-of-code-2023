import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Puzzle7 {
    public static void main(String[] args) {
        Map<Five, Integer> sortedMap = new TreeMap<>();
        int sum = 0;
        int mapIndex = 0;

        InputStream inputStream = Puzzle1.class.getResourceAsStream("data7.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                String[] split2 = split[0].split("");
                for (int i = 0; i < split2.length; i++) {
                    if (split2[i].equals("2")) {
                        split2[i] = "TWO";
                    } else if (split2[i].equals("3")) {
                        split2[i] = "THREE";
                    } else if (split2[i].equals("4")) {
                        split2[i] = "FOUR";
                    } else if (split2[i].equals("5")) {
                        split2[i] = "FIVE";
                    } else if (split2[i].equals("6")) {
                        split2[i] = "SIX";
                    } else if (split2[i].equals("7")) {
                        split2[i] = "SEVEN";
                    } else if (split2[i].equals("8")) {
                        split2[i] = "EIGHT";
                    } else if (split2[i].equals("9")) {
                        split2[i] = "NINE";
                    }
                }
                sortedMap.put(new Five(
                                new ArrayList<>(),
                                Five.Card.valueOf(split2[0]),
                                Five.Card.valueOf(split2[1]),
                                Five.Card.valueOf(split2[2]),
                                Five.Card.valueOf(split2[3]),
                                Five.Card.valueOf(split2[4])),
                        Integer.valueOf(split[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Integer value : sortedMap.values()) {
            mapIndex++;
            sum += value * mapIndex;
        }

        System.out.println(sum);}

}

 class Five implements Comparable<Five> {
    @Override
    public String toString() {
        return "Five{" +
                "cards=" + cards +
                '}';
    }

    private List<Card> cards;
    private Card card1;
    private Card card2;
    private Card card3;

    public List<Card> getCards() {
        return cards;
    }

    private Card card4;
    private Card card5;

    public Five(List<Card> cards, Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.cards = Arrays.asList(card1, card2, card3, card4, card5);
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
    }

    @Override
    public int compareTo(Five five) {
        int repetitionMap1 = countRepetitions(this.cards);
        int repetitionMap2 = countRepetitions(five.cards);

        int comparing = Integer.compare(repetitionMap1, repetitionMap2);
        if (comparing != 0) {
            return comparing;
        } else {
            for (int i = 0; i < 5; i++) {
                int comparing2 = this.cards.get(i).compareTo(five.cards.get(i));
                if (comparing2 != 0){
                    return comparing2;
                }
            }
            System.out.println("Piątki są takie same !!!");
            return 0;
        }
    }

    public int countRepetitions(List<Card> cards) {
        Map<Card, Integer> repetitionsMap = new HashMap<>();
        for (Card card : cards) {
            repetitionsMap.put(card, repetitionsMap.getOrDefault(card, 0) + 1);
        }
        int count = 0;
        for (Integer value : repetitionsMap.values()) {
            if (value == 1) {
            } else if (value == 2) {
                count += 1;
            } else if (value == 3) {
                count += 3;
            } else if (value == 4) {
                count += 5;
            } else if (value == 5) {
                count += 6;
            }
        }
        return count;
    }

    public enum Card {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        T(10),
        J(11),
        Q(12),
        K(13),
        A(14);

        public int getNumericValue() {
            return numericValue;
        }

        public final int numericValue;

        Card(int numericValue) {
            this.numericValue = numericValue;
        }


    }


}
