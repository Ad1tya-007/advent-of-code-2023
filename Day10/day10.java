package Day10;

import java.io.BufferedReader;
import java.io.FileReader;

public class day10 {

    private static String NORTH_SOUTH = "|";
    private static String EAST_WEST = "-";
    private static String NORTH_EAST = "L";
    private static String NORTH_WEST = "J";
    private static String SOUTH_WEST = "7";
    private static String SOUTH_EAST = "F";
    private static String START = "S";

    private static int MAP_SIZE = 140;

    public static void main(String[] args) {
        String text = """
                ...........
                .S-------7.
                .|F-----7|.
                .||.....||.
                .||.....||.
                .|L-7.F-J|.
                .|..|.|..|.
                .L--J.L--J.
                ...........
                """;

        String[] map = new String[MAP_SIZE * MAP_SIZE];

        try {
            BufferedReader br = new BufferedReader(new FileReader("day10/input.txt"));

            int x = 0;
            int y = 0;

            boolean[] visit = new boolean[MAP_SIZE * MAP_SIZE];

            int startX = 0;
            int startY = 0;

            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                if (line.isBlank())
                    continue;
                x = 0;
                for (String c : line.split("")) {
                    map[y * MAP_SIZE + x] = c;
                    if (c.equals(START)) {
                        startX = x;
                        startY = y;
                    }
                    x++;
                }
                y++;
            }

            Position westP = new Position(startX, startY);
            westP.move(Position.WEST);
            while (westP.isCanMove()) {
                westP = loopLength(westP, visit, map);
            }

            visit[startY * MAP_SIZE + startX] = true;

            // printMap(map, visit);

            System.out.println(westP.getCount() / 2);
            System.out.println(westP.getArea(visit));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Position loopLength(Position p, boolean[] visit, String[] map) {
        int startX = p.getX();
        int startY = p.getY();
        int dir = p.getDirection();
        if (dir == Position.NORTH) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_SOUTH) && !visit[(startY + 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.NORTH);
                return p;
            }
            if (tile.equals(NORTH_EAST) && !visit[startY * MAP_SIZE + startX + 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.EAST);
                return p;
            }
            if (tile.equals(NORTH_WEST) && !visit[startY * MAP_SIZE + startX - 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.WEST);
                return p;
            }
        }

        if (dir == Position.SOUTH) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_SOUTH) && !visit[(startY - 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.SOUTH);
                return p;
            }
            if (tile.equals(SOUTH_EAST) && !visit[startY * MAP_SIZE + startX + 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.EAST);
                return p;
            }
            if (tile.equals(SOUTH_WEST) && !visit[startY * MAP_SIZE + startX - 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.WEST);
                return p;
            }
        }

        if (dir == Position.WEST) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_EAST) && !visit[(startY - 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.SOUTH);
                return p;
            }
            if (tile.equals(SOUTH_EAST) && !visit[(startY + 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.NORTH);
                return p;
            }
            if (tile.equals(EAST_WEST) && !visit[startY * MAP_SIZE + startX - 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.WEST);
                return p;
            }
        }

        if (dir == Position.EAST) {
            String tile = map[startY * MAP_SIZE + startX];
            if (tile.equals(NORTH_WEST) && !visit[(startY - 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.SOUTH);
                return p;
            }
            if (tile.equals(SOUTH_WEST) && !visit[(startY + 1) * MAP_SIZE + startX]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.NORTH);
                return p;
            }
            if (tile.equals(EAST_WEST) && !visit[startY * MAP_SIZE + startX + 1]) {
                visit[startY * MAP_SIZE + startX] = true;
                p.move(Position.EAST);
                return p;
            }
        }

        p.setCanMove(false);
        return p;
    }

    // private static void printMap(String[] map, boolean[] visit) {
    // for (int y = 0; y < MAP_SIZE; y++) {
    // for (int x = 0; x < MAP_SIZE; x++) {
    // if (visit[y * MAP_SIZE + x]) {
    // System.out.print(map[y * MAP_SIZE + x]);
    // } else {
    // System.out.print(".");
    // }
    // }
    // System.out.println();
    // }
    // }
}