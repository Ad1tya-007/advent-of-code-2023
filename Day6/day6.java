package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day6 {
    public static void main(String[] args) {

        String text = """
                Time:      7  15   30
                Distance:  9  40  200
                """;

        try {

            for (boolean part2 : new boolean[] { false, true }) {
                List<Integer> times = new ArrayList<>();
                List<Integer> distances = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader("Day6/input.txt"));
                for (String line : br.lines().toList()) {
                    // for (String line : text.split("\n")) {
                    if (line.startsWith("Time:")) {
                        for (String time : line.replace("Time:", "").trim().split("\\s+")) {
                            times.add(Integer.parseInt(time));
                        }

                    }
                    if (line.startsWith("Distance:")) {
                        for (String distance : line.replace("Distance:", "").trim().split("\\s+")) {
                            distances.add(Integer.parseInt(distance));
                        }
                    }
                }

                int sum = 0;
                int count = 0;

                if (!part2) {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < times.get(i); j++) {
                            if ((j * (times.get(i) - j)) > distances.get(i)) {
                                count += 1;
                            }
                        }
                        if (sum == 0) {
                            sum = 1;
                        }
                        sum *= count;
                        count = 0;
                    }
                } else {
                    StringBuilder newTimeString = new StringBuilder();
                    for (Integer time : times) {
                        newTimeString.append(time);
                    }

                    long newTime = Long.parseLong(newTimeString.toString());

                    StringBuilder newDistanceString = new StringBuilder();
                    for (Integer distance : distances) {
                        newDistanceString.append(distance);
                    }

                    long newDistance = Long.parseLong(newDistanceString.toString());

                    for (long i = 0; i <= newTime; i++) {
                        if ((i * (newTime - i)) >= newDistance) {
                            sum++;
                        }
                    }
                }

                String msg = part2 ? "Part2: " : "Part1: ";

                System.out.println(msg + "" + sum);
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }
}
