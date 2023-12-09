package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day8 {
    public static void main(String[] args) {

        String text = """
                LLR

                AAA = (BBB, BBB)
                BBB = (AAA, ZZZ)
                ZZZ = (ZZZ, ZZZ)
                """;

        try {

            int sum = 0;
            String intructions = "";
            List<List<String>> values = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("Day8/input.txt"));
            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                if (line.startsWith("LLLL")) {
                    intructions = line;
                } else if (line.isBlank())
                    continue;
                else {
                    String[] body = line.trim().split(" = ");
                    String[] l_r = body[1].replace("(", "").replace(")", "").trim().split(", ");
                    List<String> temp = new ArrayList<>();
                    temp.add(body[0]);
                    temp.add(l_r[0]);
                    temp.add(l_r[1]);
                    values.add(temp);
                }

            }

            // System.out.println(intructions);
            // System.out.println(values);

            List<String> current_starts = new ArrayList<>();

            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).get(0).charAt(2) == 'A') {
                    current_starts.add(values.get(i).get(0));
                }
            }

            // System.out.println(current_starts);

            // [DNA, HNA, AAA, LMA, VGA, LLA]
            // steps to reach should be
            // [20569 , 18727 , 14429 , 13201 , 18113 , 22411]
            // find LCM of this using wolfram lol

            String current = "LLA";
            int steps = 0;

            // while (!current.equals("ZZZ")) {
            while (current.charAt(2) != 'Z') {
                for (int i = 0; i < values.size(); i++) {
                    if (current.equals(values.get(i).get(0))) {
                        if (intructions.charAt(steps % intructions.length()) == 'L') {
                            current = values.get(i).get(1);
                            // System.out.println("L | " + current);
                            steps++;
                            // System.out.println(steps);
                            break;
                        }
                        if (intructions.charAt(steps % intructions.length()) == 'R') {
                            current = values.get(i).get(2);
                            // System.out.println("R | " + current);
                            steps++;
                            // System.out.println(steps);
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            }

            // System.out.println("final = " + steps);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}