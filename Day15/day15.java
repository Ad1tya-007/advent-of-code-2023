package Day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day15 {
    public static void main(String[] args) {

        try {

            List<List<Pair>> BOX = new ArrayList<>();
            for (int i = 0; i < 256; i++) {
                BOX.add(new ArrayList<>());
            }

            for (boolean part2 : new boolean[] { false, true }) {
                int ans = 0;
                BufferedReader br = new BufferedReader(new FileReader("Day15/input.txt"));
                // for (String line : text.split("\n")) {
                for (String line : br.lines().toList()) {
                    if (!part2) {
                        for (String subLine : line.split(",")) {
                            int score = 0;
                            score += hash(subLine);
                            ans += score;
                        }
                    } else {
                        for (String subLine : line.split(",")) {
                            if (subLine.endsWith("-")) {
                                String name = subLine.substring(0, subLine.length() - 1);
                                int h = hash(name);
                                BOX.get(h).removeIf(pair -> pair.name.equals(name));
                            }
                            if (subLine.charAt(subLine.length() - 2) == '=') {
                                String name = subLine.substring(0, subLine.length() - 2);
                                int h = hash(name);
                                int len_ = Integer.parseInt(subLine.substring(subLine.length() - 1));
                                boolean nameExists = BOX.get(h).stream().anyMatch(pair -> pair.name.equals(name));

                                if (nameExists) {
                                    BOX.get(h).replaceAll(
                                            pair -> new Pair(pair.name, name.equals(pair.name) ? len_ : pair.v));
                                } else {
                                    BOX.get(h).add(new Pair(name, len_));
                                }
                            }
                        }

                        for (int i = 0; i < BOX.size(); i++) {
                            for (int j = 0; j < BOX.get(i).size(); j++) {
                                Pair pair = BOX.get(i).get(j);
                                ans += (i + 1) * (j + 1) * pair.v;
                            }
                        }

                    }

                }

                String msg = part2 ? "Part2: " : "Part1: ";

                System.out.println(msg + "" + ans);
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }

    private static int hash(String name) {
        int ans = 0;
        for (Character c : name.toCharArray()) {
            ans += (int) c;
            ans = ans * 17;
            ans = ans % 256;
        }
        return ans;
    }

    static class Pair {
        String name;
        int v;

        Pair(String name, int v) {
            this.name = name;
            this.v = v;
        }
    }
}
