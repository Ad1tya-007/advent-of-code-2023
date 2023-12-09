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
            List<Integer> times = new ArrayList<>();
            List<Integer> distances = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("Day6/input.txt"));
            // for (String line : br.lines().toList()) {
            // for (String line : text.split("\n")) {
            // if (line.startsWith("Time:")) {
            // for (String time : line.replace("Time:", "").trim().split("\\s+")) {
            // times.add(Integer.parseInt(time));
            // }

            // }
            // if (line.startsWith("Distance:")) {
            // for (String distance : line.replace("Distance:", "").trim().split("\\s+")) {
            // distances.add(Integer.parseInt(distance));
            // }
            // }
            // }

            // System.out.println("Times: " + times);
            // System.out.println("Distances: " + distances);

            // 40817772
            // 219101213651089

            int count = 0;

            for (long i = 0; i <= 40817772; i++) {

                if ((i * (40817772 - i)) >= 219101213651089L) {
                    count++;
                }

            }

            System.out.println(count);

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }
}
