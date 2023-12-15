package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout.Group;

public class day14 {
    public static void main(String[] args) {

        Map<List<List<String>>, Long> GRID = new HashMap<>();

        String text = """
                O....#....
                O.OO#....#
                .....##...
                OO.#O....O
                .O.....O#.
                O.#..O.#.#
                ..O..#O..O
                .......O..
                #....###..
                #OO..#....
                """;

        try {
            List<List<String>> grid = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("Day14/input.txt"));
            for (String row : br.lines().toList()) {
                // for (String row : text.split("\n")) {
                List<String> new_row = new ArrayList<>();
                for (String c : row.split("")) {
                    new_row.add(c);
                }
                grid.add(new_row);
            }

            long start = 0;
            long end = 1000000000;

            while (start < end) {
                start += 1;
                for (int j = 0; j < 4; j++) {
                    grid = roll(grid);
                    if (start == 1 && j == 0) {
                        System.out.println("Part1: " + score(grid));
                    }
                    grid = rotate(grid);
                }
                List<List<String>> GH = grid;
                if (GRID.containsKey(GH)) {
                    long cycle_length = start - GRID.get(GH);
                    long amount = (end - start) / cycle_length;
                    start += amount * cycle_length;
                }
                GRID.put(GH, start);
            }
            System.out.println("Part2: " + score(grid));

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }

    private static List<List<String>> roll(List<List<String>> G) {
        int rows = G.size();
        int cols = G.get(0).size();
        for (int c = 0; c < cols; c++) {
            for (int i = 0; i < rows; i++) {
                for (int r = 0; r < rows; r++) {
                    if (G.get(r).get(c).equals("O") && r > 0 && G.get(r - 1).get(c).equals(".")) {
                        G.get(r).set(c, ".");
                        G.get(r - 1).set(c, "O");
                    }

                }
            }
        }
        return G;
    }

    // 1, 2, 3
    // 4, 5, 6
    // 7, 8, 9

    // 7, 4, 1
    // 8, 5, 2
    // 9, 6, 3

    private static List<List<String>> rotate(List<List<String>> G) {
        int rows = G.size();
        int cols = G.get(0).size();
        List<List<String>> rotate_G = empty(cols, rows);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotate_G.get(c).set(rows - r - 1, G.get(r).get(c));
            }
        }
        return rotate_G;
    }

    private static List<List<String>> empty(int rows, int cols) {
        List<List<String>> new_G = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> new_row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                new_row.add("?");
            }
            new_G.add(new_row);
        }
        return new_G;
    }

    private static long score(List<List<String>> G) {
        int rows = G.size();
        int cols = G.get(0).size();
        long ans = 0;
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (G.get(r).get(c).equals("O")) {
                    ans += rows - r;
                }
            }
        }
        return ans;

    }

    private static void printGrid(List<List<String>> G) {
        for (int i = 0; i < G.size(); i++) {
            System.out.println(G.get(i));
        }
    }
}
