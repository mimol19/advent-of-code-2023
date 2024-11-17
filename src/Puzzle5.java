import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Puzzle5 {

    public static class RangeMapping {
        @Override
        public String toString() {
            return "RangeMapping{" +
                    "value=" + value +
                    ", min=" + min +
                    ", distance=" + distance +
                    '}';
        }

        private final Long value;
        private final Long min;
        private final Long distance;

        public RangeMapping(Long value, Long min, Long distance) {
            this.value = value;
            this.min = min;
            this.distance = distance;
        }
    }

    static List<Long> seed = new ArrayList<>();
    static List<RangeMapping> seedToSoil = new ArrayList<>();
    static List<RangeMapping> soilToFertilizer = new ArrayList<>();
    static List<RangeMapping> fertilizerToWater = new ArrayList<>();
    static List<RangeMapping> waterToLight = new ArrayList<>();
    static List<RangeMapping> lightToTemperature = new ArrayList<>();
    static List<RangeMapping> temperatureToHumidity = new ArrayList<>();
    static List<RangeMapping> humidityToLocation = new ArrayList<>();

    public static void main(String[] args) {
        InputStream inputStream = Puzzle1.class.getResourceAsStream("data5.txt");

        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                List<RangeMapping> currentList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(" ");
                    for (String s : split) {
                        s.trim();
                    }
                    if (line.startsWith("seeds:")) {
                        String substring = line.substring(7);
                        String[] split1 = substring.split(" ");
                        for (String s : split1) {
                            s.trim();
                            seed.add(Long.valueOf(s));
                        }
                    } else if (line.startsWith("seed-to-soil map:")) {
                        currentList = seedToSoil;
                    } else if (line.startsWith("soil-to-fertilizer map:")) {
                        currentList = soilToFertilizer;
                    } else if (line.startsWith("fertilizer-to-water map:")) {
                        currentList = fertilizerToWater;
                    } else if (line.startsWith("water-to-light map:")) {
                        currentList = waterToLight;
                    } else if (line.startsWith("light-to-temperature map:")) {
                        currentList = lightToTemperature;
                    } else if (line.startsWith("temperature-to-humidity map:")) {
                        currentList = temperatureToHumidity;
                    } else if (line.startsWith("humidity-to-location map:")) {
                        currentList = humidityToLocation;
                    } else if (!line.isBlank()) {
                        RangeMapping rangeMapping = new RangeMapping(Long.valueOf(split[0]), Long.valueOf(split[1]), Long.valueOf(split[2]));
                        currentList.add(rangeMapping);
                    }
                }
                Long finalValue = Long.MAX_VALUE;
                for (Long l : seed) {
                    Long value = map(l);
                    if (value < finalValue) {
                        finalValue = value;
                    }
                }

                System.out.println(finalValue);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    private static Long map(Long l) {
        for (RangeMapping rangeMapping : seedToSoil) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        for (RangeMapping rangeMapping : soilToFertilizer) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        for (RangeMapping rangeMapping : fertilizerToWater) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        for (RangeMapping rangeMapping : waterToLight) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        for (RangeMapping rangeMapping : lightToTemperature) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        for (RangeMapping rangeMapping : temperatureToHumidity) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        for (RangeMapping rangeMapping : humidityToLocation) {
            if (l >= rangeMapping.min && l < (rangeMapping.min + rangeMapping.distance)) {
                l = rangeMapping.value + (l - rangeMapping.min);
                break;
            }
        }
        return l;
    }


}


