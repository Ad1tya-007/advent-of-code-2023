package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day5 {
    static boolean is_seed_to_soil = false;
    static boolean is_soil_to_fertilizer = false;
    static boolean is_fertilizer_to_water = false;
    static boolean is_water_to_light = false;
    static boolean is_light_to_temperature = false;
    static boolean is_temperature_to_humidity = false;
    static boolean is_humidity_to_location = false;

    // 4239267129 20461805 2775736218 52390530 --> 656988148
    // 3109225152 741325372 1633502651 46906638
    // 967445712 47092469 2354891449 237152885 --> 137516820
    // 2169258488 111184803 2614747853 123738802
    // 620098496 291114156 2072253071 28111202

    public static void main(String[] args) {

        String text = """
                seeds: 79 14 55 13

                seed-to-soil map:
                50 98 2
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15

                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4

                water-to-light map:
                88 18 7
                18 25 70

                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13

                temperature-to-humidity map:
                0 69 1
                1 0 69

                humidity-to-location map:
                60 56 37
                56 93 4
                """;

        try {

            List<Long> seeds = new ArrayList<>();
            List<List<Long>> seeds2 = new ArrayList<>();
            List<Long> new_seeds_values = new ArrayList<>();
            List<List<Long>> seed_to_soil = new ArrayList<>();
            List<List<Long>> soil_to_fertilizer = new ArrayList<>();
            List<List<Long>> fertilizer_to_water = new ArrayList<>();
            List<List<Long>> water_to_light = new ArrayList<>();
            List<List<Long>> light_to_temperature = new ArrayList<>();
            List<List<Long>> temperature_to_humidity = new ArrayList<>();
            List<List<Long>> humidity_to_location = new ArrayList<>();

            int sum = 0;

            BufferedReader br = new BufferedReader(new FileReader("Day5/input.txt"));
            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                if (line.isBlank())
                    continue;
                if (line.startsWith("seeds:")) {
                    String[] values = line.replace("seeds:", "").trim().split(" ");
                    // for (int i = 0; i < values.length; i += 2) {
                    // if (i + 1 < values.length) {
                    // long startSeed = Long.parseLong(values[i]);
                    // long rangeLength = Long.parseLong(values[i + 1]);

                    // // Generate the seed range
                    // for (long j = 0; j < rangeLength; j++) {
                    // seeds.add(startSeed + j);
                    // }
                    // }
                    // }
                    for (int i = 0; i < values.length; i += 2) {
                        long startSeed = Long.parseLong(values[i]);
                        long rangeLength = Long.parseLong(values[i + 1]);
                        List<Long> numberList = new ArrayList<>();
                        numberList.add(startSeed);
                        numberList.add(rangeLength);
                        seeds2.add(numberList);
                    }
                } else {
                    if (line.matches("^\\d.*")) {
                        if (is_seed_to_soil) {
                            getNumbers(line, seed_to_soil);
                        }
                        if (is_soil_to_fertilizer) {
                            getNumbers(line, soil_to_fertilizer);
                        }
                        if (is_fertilizer_to_water) {
                            getNumbers(line, fertilizer_to_water);
                        }
                        if (is_water_to_light) {
                            getNumbers(line, water_to_light);
                        }
                        if (is_light_to_temperature) {
                            getNumbers(line, light_to_temperature);
                        }
                        if (is_temperature_to_humidity) {
                            getNumbers(line, temperature_to_humidity);
                        }
                        if (is_humidity_to_location) {
                            getNumbers(line, humidity_to_location);
                        }
                    } else {
                        if (line.startsWith("seed-to-soil map:")) {
                            setAllToFalse();
                            is_seed_to_soil = true;
                        }
                        if (line.startsWith("soil-to-fertilizer map:")) {
                            setAllToFalse();
                            is_soil_to_fertilizer = true;
                        }
                        if (line.startsWith("fertilizer-to-water map:")) {
                            setAllToFalse();
                            is_fertilizer_to_water = true;
                        }
                        if (line.startsWith("water-to-light map:")) {
                            setAllToFalse();
                            is_water_to_light = true;
                        }
                        if (line.startsWith("light-to-temperature map:")) {
                            setAllToFalse();
                            is_light_to_temperature = true;
                        }
                        if (line.startsWith("temperature-to-humidity map:")) {
                            setAllToFalse();
                            is_temperature_to_humidity = true;
                        }
                        if (line.startsWith("humidity-to-location map:")) {
                            setAllToFalse();
                            is_humidity_to_location = true;
                        }
                    }
                }
            }
            setAllToFalse();
            // System.out.println("seeds:" + seeds2);
            // System.out.println("seed_to_soil:" + seed_to_soil);
            // System.out.println("soil_to_fertilizer:" + soil_to_fertilizer);
            // System.out.println("fertilizer_to_water:" + fertilizer_to_water);
            // System.out.println("water_to_light:" + water_to_light);
            // System.out.println("light_to_temperature:" + light_to_temperature);
            // System.out.println("temperature_to_humidity:" + temperature_to_humidity);
            // System.out.println("humidity_to_location:" + humidity_to_location);

            long min = 0;
            int done = 0;

            for (List<Long> seed2 : seeds2) {
                for (long i = seed2.get(0); i < seed2.get(0) + seed2.get(1); i++) {
                    long seed_value = i;
                    // System.out.print(seed + "->");
                    for (List<Long> soil_values : seed_to_soil) {
                        if (seed_value >= soil_values.get(1)
                                && seed_value < (soil_values.get(1) + soil_values.get(2))) {
                            seed_value = seed_value + (soil_values.get(0) - soil_values.get(1));
                            break;
                        }
                    }
                    // System.out.print(seed + "->");
                    for (List<Long> fertilizers_values : soil_to_fertilizer) {
                        if (seed_value >= fertilizers_values.get(1)
                                && seed_value < (fertilizers_values.get(1) + fertilizers_values.get(2))) {
                            seed_value = seed_value + (fertilizers_values.get(0) - fertilizers_values.get(1));
                            break;
                        }
                    }
                    // System.out.print(seed + "->");
                    for (List<Long> water_values : fertilizer_to_water) {
                        if (seed_value >= water_values.get(1)
                                && seed_value < (water_values.get(1) + water_values.get(2))) {
                            seed_value = seed_value + (water_values.get(0) - water_values.get(1));
                            break;
                        }
                    }
                    // System.out.print(seed + "->");
                    for (List<Long> light_values : water_to_light) {
                        if (seed_value >= light_values.get(1)
                                && seed_value < (light_values.get(1) + light_values.get(2))) {
                            seed_value = seed_value + (light_values.get(0) - light_values.get(1));
                            break;
                        }
                    }
                    // System.out.print(seed + "->");
                    for (List<Long> temperature_values : light_to_temperature) {
                        if (seed_value >= temperature_values.get(1)
                                && seed_value < (temperature_values.get(1) + temperature_values.get(2))) {
                            seed_value = seed_value + (temperature_values.get(0) - temperature_values.get(1));
                            break;
                        }
                    }
                    // System.out.print(seed + "->");
                    for (List<Long> humidity_values : temperature_to_humidity) {
                        if (seed_value >= humidity_values.get(1)
                                && seed_value < (humidity_values.get(1) + humidity_values.get(2))) {
                            seed_value = seed_value + (humidity_values.get(0) - humidity_values.get(1));
                            break;
                        }
                    }
                    // System.out.print(seed + "->");
                    for (List<Long> location_values : humidity_to_location) {
                        if (seed_value >= location_values.get(1)
                                && seed_value < (location_values.get(1) + location_values.get(2))) {
                            seed_value = seed_value + (location_values.get(0) - location_values.get(1));
                            break;
                        }
                    }
                    if (min == 0) {
                        min = seed_value;
                    } else {
                        if (min > seed_value) {
                            min = seed_value;
                        }
                    }
                    // System.out.println();
                }
                done++;
                System.out.println("Done: " + done);
            }

            System.out.println(min);
            // System.out.println("seeds:" + seeds);
            // System.out.println("new seeds:" + new_seeds_values);

            // System.out.println(Collections.min(new_seeds_values));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void setAllToFalse() {
        is_seed_to_soil = false;
        is_soil_to_fertilizer = false;
        is_fertilizer_to_water = false;
        is_water_to_light = false;
        is_light_to_temperature = false;
        is_temperature_to_humidity = false;
        is_humidity_to_location = false;
    }

    private static void getNumbers(String line, List<List<Long>> list) {
        String[] numbers = line.split(" ");
        List<Long> numberList = new ArrayList<>();
        for (String num : numbers) {
            numberList.add(Long.parseLong(num));
        }
        list.add(numberList);
    }
}
