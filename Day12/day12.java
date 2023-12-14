package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day12 {
    private static Map<String, Long> DP = new HashMap<>();

    public static void main(String[] args) {

        try {
            for (boolean part2 : new boolean[] { false, true }) {
                BufferedReader br = new BufferedReader(new FileReader("Day12/input.txt"));
                long ans = 0;

                for (String line : br.lines().toList()) {
                    String[] arr = line.split(" ");
                    String dots = arr[0];
                    String blockString = arr[1];

                    if (part2) {
                        StringBuilder dotsBuilder = new StringBuilder();
                        StringBuilder blocksBuilder = new StringBuilder();

                        for (int i = 0; i < 5; i++) {
                            dotsBuilder.append(dots);
                            blocksBuilder.append(blockString);
                            if (i != 4) {
                                dotsBuilder.append("?");
                                blocksBuilder.append(",");
                            }
                        }
                        dots = dotsBuilder.toString();
                        blockString = blocksBuilder.toString();

                    }

                    int[] blocks = new int[blockString.split(",").length];
                    int i = 0;
                    for (String b : blockString.split(",")) {
                        blocks[i] = Integer.parseInt(b);
                        i++;
                    }

                    DP.clear();

                    ans += getScore(dots, blocks, 0, 0, 0);
                }
                String msg = part2 ? "Part2: " : "Part1: ";

                System.out.println(msg + "" + ans);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long getScore(String dots, int[] blocks, int i, int bi, int current) {
        String key = i + "," + bi + "," + current;

        if (DP.containsKey(key)) {
            return DP.get(key);
        }

        if (i == dots.length()) {
            if (bi == blocks.length && current == 0) {
                return 1;
            }
            if (bi == blocks.length - 1 && blocks[bi] == current) {
                return 1;
            }
            return 0;
        }

        long ans = 0;
        for (char c : new char[] { '.', '#' }) {
            if (dots.charAt(i) == c || dots.charAt(i) == '?') {
                if (c == '#') {
                    ans += getScore(dots, blocks, i + 1, bi, current + 1);
                } else if (c == '.') {
                    if (current == 0) {
                        ans += getScore(dots, blocks, i + 1, bi, 0);
                    } else if (current > 0 && bi < blocks.length && blocks[bi] == current) {
                        ans += getScore(dots, blocks, i + 1, bi + 1, 0);
                    }
                }
            }
        }

        DP.put(key, ans);
        return ans;
    }
}
