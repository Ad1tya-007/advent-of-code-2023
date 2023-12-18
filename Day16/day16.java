package Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class day16 {
    static int[] DR = { -1, 0, 1, 0 };
    static int[] DC = { 0, 1, 0, -1 };

    static int R;
    static int C;

    static List<List<String>> map = new ArrayList<>();

    public static void main(String[] args) {

        try {

            int sum = 0;
            BufferedReader br = new BufferedReader(new FileReader("Day16/input.txt"));
            for (String line : br.lines().toList()) {
                List<String> row = new ArrayList<>();
                for (String object : line.split("")) {
                    row.add(object);
                }
                map.add(row);
            }

            R = map.size();
            C = map.get(0).size();

            // printMap(map);

            int ans = 0;
            for (int r = 0; r < R; r++) {
                ans = max(ans, score(r, 0, 1));
                ans = max(ans, score(r, C - 1, 3));
            }

            for (int c = 0; c < C; c++) {
                ans = max(ans, score(0, c, 2));
                ans = max(ans, score(R - 1, c, 0));
            }

            System.out.println("Part 1:" + score(0, 0, 1));
            System.out.println("Part 2:" + ans);

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }

    private static int max(int ans, int score) {
        if (ans >= score) {
            return ans;
        }
        return score;
    }

    private static void printMap(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(0).size(); j++) {
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }
    }

    static int[] step(int r, int c, int d) {
        int[] result = new int[3];
        result[0] = r + DR[d];
        result[1] = c + DC[d];
        result[2] = d;

        return result;
    }

    static int score(int sr, int sc, int sd) {
        int[][] POS = { { sr, sc, sd } };
        Set<String> SEEN = new HashSet<>();
        Set<String> SEEN2 = new HashSet<>();

        while (true) {
            int[][] NP = new int[0][0];
            if (POS.length == 0) {
                break;
            }

            for (int[] pos : POS) {
                int r = pos[0];
                int c = pos[1];
                int d = pos[2];

                if (0 <= r && r < R && 0 <= c && c < C) {
                    SEEN.add(r + "," + c);
                    String posString = r + "," + c + "," + d;
                    if (SEEN2.contains(posString)) {
                        continue;
                    }

                    SEEN2.add(posString);

                    String ch = map.get(r).get(c);

                    if (ch.equals(".")) {
                        NP = append(NP, step(r, c, d));
                    } else if (ch.equals("/")) {
                        NP = append(NP, step(r, c, d == 0 ? 1 : d == 1 ? 0 : d == 2 ? 3 : 2));
                    } else if (ch.equals("\\")) {
                        NP = append(NP, step(r, c, d == 0 ? 3 : d == 1 ? 2 : d == 2 ? 1 : 0));
                    } else if (ch.equals("|")) {
                        if (d == 0 || d == 2) {
                            NP = append(NP, step(r, c, d));
                        } else {
                            NP = append(NP, step(r, c, 0));
                            NP = append(NP, step(r, c, 2));
                        }
                    } else if (ch.equals("-")) {
                        if (d == 1 || d == 3) {
                            NP = append(NP, step(r, c, d));
                        } else {
                            NP = append(NP, step(r, c, 1));
                            NP = append(NP, step(r, c, 3));
                        }
                    } else {
                        assert false;
                    }
                }
            }
            POS = NP;
        }
        return SEEN.size();
    }

    static int[][] append(int[][] array, int[] element) {
        int length = array.length;
        int[][] newArray = new int[length + 1][];
        System.arraycopy(array, 0, newArray, 0, length);
        newArray[length] = element;
        return newArray;
    }
}
