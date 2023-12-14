package Day13;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class day13 {
    public static void main(String[] args) {

        try {

            for (boolean part2 : new boolean[] { false, true }) {
                int ans = 0;
                String filePath = "Day13/input.txt";
                Path path = Paths.get(filePath);
                String content = new String(Files.readAllBytes(path)).trim();
                String[] grids = content.split("\n\n");
                for (String grid : grids) {
                    String[] rows = grid.split("\n");
                    char[][] G = new char[rows.length][];

                    for (int i = 0; i < rows.length; i++) {
                        G[i] = rows[i].toCharArray();
                    }

                    int R = G.length;
                    int C = G[0].length;

                    for (int c = 0; c < C - 1; c++) {
                        int badness = 0;
                        for (int dc = 0; dc < C; dc++) {
                            int left = c - dc;
                            int right = c + 1 + dc;
                            if (0 <= left && left < right && right < C) {
                                for (int r = 0; r < R; r++) {
                                    if (G[r][left] != G[r][right]) {
                                        badness += 1;
                                    }
                                }
                            }
                        }
                        if (badness == (part2 ? 1 : 0)) {
                            ans += c + 1;
                        }
                    }

                    for (int r = 0; r < R - 1; r++) {
                        int badness = 0;
                        for (int dr = 0; dr < R; dr++) {
                            int up = r - dr;
                            int down = r + 1 + dr;
                            if (0 <= up && up < down && down < R) {
                                for (int c = 0; c < C; c++) {
                                    if (G[up][c] != G[down][c]) {
                                        badness += 1;
                                    }
                                }
                            }
                        }
                        if (badness == (part2 ? 1 : 0)) {
                            ans += 100 * (r + 1);
                        }
                    }

                }
                System.out.println(ans);
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }
}
