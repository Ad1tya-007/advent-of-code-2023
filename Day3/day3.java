package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class day3 {
    static int MAX_X = 140;
    static int MAX_Y = 140;

    public static void main(String[] args) {

        String text = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
                """;

        // String text = """
        // 467.114...
        // ...*......
        // ......633.
        // ......#...
        // 617*......
        // .....+.58.
        // ..592.....
        // ......755.
        // ...$.*....
        // .664.598..
        // """;

        int[][] map = new int[MAX_X][MAX_Y];

        try {
            Pattern pattern = Pattern.compile("(\\d+) (\\w+)");
            int sum = 0;
            int x = 0, y = 0;

            // NOTE
            // 0 - 9 = Number
            // -1 = any special character except dot
            // -2 = dot
            // -3 = number that is used
            // -4 = *

            BufferedReader br = new BufferedReader(new FileReader("Day3/input.txt"));
            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                for (String c : line.split("")) {
                    if (c.equals(".")) {
                        map[x][y] = -2; // dot
                    } else if (Character.isDigit(c.charAt(0))) {
                        map[x][y] = Integer.parseInt(c); // number
                    } else if (c.equals("*")) {
                        map[x][y] = -4; // *
                    } else {
                        map[x][y] = -1; // any special character except *
                    }
                    x++;
                }
                x = 0;
                y++;
            }

            // find all numbers near the same *
            for (int b = 0; b < MAX_Y; b++) {
                for (int a = 0; a < MAX_X; a++) {
                    if (isFour(map[a][b])) {
                        // find part numbers now
                        sum += getAdjacentToStar(a, b, map);
                    }
                }
            }

            // System.out.println("Sum at 5 x 8: = " + getAdjacentToStar(5, 8, map));

            // for (int b = 0; b < MAX_Y; b++) {
            // for (int a = 0; a < MAX_X; a++) {
            // if (isAdjacent(a, b, map) && isNumber(map[a][b])) {
            // int number = 0;
            // number = map[a][b];
            // map[a][b] = -3;

            // if (a > 0 && isNumber(map[a - 1][b])) {
            // if (!isThree(map[a - 1][b])) {
            // number = map[a - 1][b] * 10 + number;
            // }
            // if (!isNumber(map[a - 2][b])) {
            // map[a - 1][b] = -3;
            // }

            // }
            // if (a > 1 && isNumber(map[a - 1][b]) && isNumber(map[a - 2][b])) {
            // if (!isThree(map[a - 2][b])) {
            // number = map[a - 2][b] * 100 + number;
            // map[a - 2][b] = -3;
            // map[a - 1][b] = -3;
            // }
            // }
            // // right side
            // if (a < MAX_X - 1 && isNumber(map[a + 1][b])) {
            // if (!isThree(map[a + 1][b])) {
            // number = number * 10 + map[a + 1][b];
            // }
            // if (!isNumber(map[a + 2][b])) {
            // map[a + 2][b] = -3;
            // }
            // }
            // if (a < MAX_X - 2 && isNumber(map[a + 1][b]) && isNumber(map[a + 2][b])) {
            // if (!isThree(map[a + 2][b])) {
            // number = number * 10 + map[a + 2][b];
            // map[a + 2][b] = -3;
            // map[a + 1][b] = -3;
            // }
            // }
            // sum += number;
            // }

            // }

            // }

            System.out.println(sum);

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean isAdjacent(int x, int y, int[][] map) {
        if (x > 0 && y > 0 && map[x - 1][y - 1] == -1) {
            return true;
        }
        if (y > 0 && map[x][y - 1] == -1) {
            return true;
        }
        if (x < MAX_X - 1 && y > 0 && map[x + 1][y - 1] == -1) {
            return true;
        }
        if (x > 0 && map[x - 1][y] == -1) {
            return true;
        }
        if (x < MAX_X - 1 && y > 0 && map[x + 1][y] == -1) {
            return true;
        }
        if (x > 0 && y < MAX_Y - 1 && map[x - 1][y + 1] == -1) {
            return true;
        }
        if (y < MAX_Y - 1 && map[x][y + 1] == -1) {
            return true;
        }
        if (x < MAX_X - 1 && y < MAX_Y - 1 && map[x + 1][y + 1] == -1) {
            return true;
        }
        return false;
    }

    private static int getAdjacentToStar(int x, int y, int[][] map) {
        int number = 1;
        int count = 0;

        if (x > 0 && y > 0 && isNumber(map[x - 1][y - 1])) {
            number *= getNumber(x - 1, y - 1, map);
            count++;
        }

        if (y > 0 && isNumber(map[x][y - 1])) {
            number *= getNumber(x, y - 1, map);
            count++;
        }

        if (x < MAX_X - 1 && y > 0 && isNumber(map[x + 1][y - 1])) {
            number *= getNumber(x + 1, y - 1, map);
            count++;
        }

        if (x > 0 && isNumber(map[x - 1][y])) {
            number *= getNumber(x - 1, y, map);
            count++;
        }

        if (x < MAX_X - 1 && y > 0 && isNumber(map[x + 1][y])) {
            number *= getNumber(x + 1, y, map);
            count++;
        }

        if (x > 0 && y < MAX_Y - 1 && isNumber(map[x - 1][y + 1])) {
            number *= getNumber(x - 1, y + 1, map);
            count++;
        }

        if (y < MAX_Y - 1 && isNumber(map[x][y + 1])) {
            number *= getNumber(x, y + 1, map);
            count++;
        }

        if (x < MAX_X - 1 && y < MAX_Y - 1 && isNumber(map[x + 1][y + 1])) {
            number *= getNumber(x + 1, y + 1, map);
            count++;
        }
        if (count == 2) {
            return number;
        }
        return 0;
    }

    private static boolean isNumber(int x) {
        return (x >= 0) && (x < 10);
    }

    private static boolean isThree(int x) {
        return x == -3;
    }

    private static boolean isFour(int x) {
        return x == -4;
    }

    private static int getNumber(int a, int b, int[][] map) {
        int number = map[a][b];
        if (isThree(number)) {
            return 1;
        }
        // left side
        if (a > 0 && isNumber(map[a - 1][b])) {
            number = map[a - 1][b] * 10 + number;
            if (a > 1 && isNumber(map[a - 2][b])) {
                number = map[a - 2][b] * 100 + number;
                map[a - 1][b] = -3;
                map[a - 2][b] = -3;
            } else {
                map[a - 1][b] = -3;
            }

        }
        // right side
        if (a < MAX_X - 1 && isNumber(map[a + 1][b])) {
            number = number * 10 + map[a + 1][b];
            if (a < MAX_X - 2 && isNumber(map[a + 2][b])) {
                number = number * 10 + map[a + 2][b];
                map[a + 1][b] = -3;
                map[a + 2][b] = -3;
            } else {
                map[a + 1][b] = -3;
            }
        }

        return number;
    }

}
