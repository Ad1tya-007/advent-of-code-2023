package Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class day18 {
    public static void main(String[] args) {
        List<BigInteger[]> points = new ArrayList<>();
        points.add(new BigInteger[] { BigInteger.ZERO, BigInteger.ZERO });
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // U D L R
        int[][] dirs2 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // R D L U

        try {
            for (boolean part2 : new boolean[] { false, true }) {
                final BigInteger[] b = { BigInteger.ZERO };
                String msg = part2 ? "Part2: " : "Part1: ";
                if (!part2) {
                    try (BufferedReader br = new BufferedReader(new FileReader("Day18/input.txt"))) {
                        br.lines().forEach(line -> {
                            String[] arr = line.split(" ");
                            String d = arr[0];
                            int num = Integer.parseInt(arr[1]);

                            int[] dir = dirs["UDLR".indexOf(d)];
                            b[0] = b[0].add(BigInteger.valueOf(num));

                            BigInteger[] lastPoint = points.get(points.size() - 1);
                            points.add(new BigInteger[] { lastPoint[0].add(BigInteger.valueOf(dir[0] * num)),
                                    lastPoint[1].add(BigInteger.valueOf(dir[1] * num)) });
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    BigInteger A = calculateArea(points);
                    BigInteger i = A.subtract(b[0].divide(BigInteger.valueOf(2))).add(BigInteger.ONE);

                    System.out.println(msg + i.add(b[0]));
                }

                if (part2) {
                    points.clear();
                    points.add(new BigInteger[] { BigInteger.ZERO, BigInteger.ZERO });

                    try (BufferedReader br = new BufferedReader(new FileReader("Day18/input.txt"))) {
                        br.lines().forEach(line -> {
                            String[] arr = line.split(" ");
                            String x = arr[2].substring(2, arr[2].length() - 1);

                            int[] dir = dirs2[x.charAt(x.length() - 1) - '0'];
                            BigInteger num = new BigInteger(x.substring(0, x.length() - 1), 16);
                            b[0] = b[0].add(num);

                            BigInteger[] lastPoint = points.get(points.size() - 1);
                            points.add(new BigInteger[] { lastPoint[0].add(BigInteger.valueOf(dir[0]).multiply(num)),
                                    lastPoint[1].add(BigInteger.valueOf(dir[1]).multiply(num)) });
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    BigInteger A = calculateArea(points);
                    BigInteger i = A.subtract(b[0].divide(BigInteger.valueOf(2))).add(BigInteger.ONE);

                    System.out.println(msg + i.add(b[0]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BigInteger calculateArea(List<BigInteger[]> points) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < points.size(); i++) {
            BigInteger[] current = points.get(i);
            BigInteger[] prev = points.get((i - 1 + points.size()) % points.size());
            BigInteger[] next = points.get((i + 1) % points.size());

            sum = sum.add(current[0].multiply(prev[1].subtract(next[1])));
        }

        return sum.abs().divide(BigInteger.valueOf(2));
    }
}