package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day11 {

    public static void main(String[] args) {
        String text = """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;

        List<Galaxy> galaxyList = new ArrayList<>();
        int GALAXY_SIZE = 140;

        try {

            long sum = 0;

            int x = 0;
            int y = 0;

            int[] xlist = new int[GALAXY_SIZE];
            int[] ylist = new int[GALAXY_SIZE];

            BufferedReader br = new BufferedReader(new FileReader("Day11/input.txt"));
            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                x = 0;
                for (String c : line.split("")) {
                    if (c.equals("#")) {
                        galaxyList.add(new Galaxy(x, y));
                        ylist[y]++;
                        xlist[x]++;
                    }
                    x++;
                }
                y++;
            }

            // expanding the universe
            for (Galaxy g : galaxyList) {
                g.expand(xlist, ylist);
            }

            for (int i = 0; i < galaxyList.size(); i++) {
                for (int j = i; j < galaxyList.size(); j++) {
                    sum += galaxyList.get(i).lengthTo(galaxyList.get(j));
                }
            }
            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}