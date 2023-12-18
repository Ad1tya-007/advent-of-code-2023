package Day17;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day17 {
    static List<List<Integer>> grid = new ArrayList<>();
    static int R;
    static int C;

    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader br = new BufferedReader(new FileReader("Day17/input.txt"));
        for (String line : br.lines().toList()) {
            List<Integer> row = new ArrayList<>();
            for (String object : line.split("")) {
                row.add(Integer.parseInt(object));
            }
            grid.add(row);
        }

        for (boolean part2 : new boolean[] { false, true }) {
            Set<State> seen = new HashSet<>();
            PriorityQueue<State> pq = new PriorityQueue<>();

            pq.add(new State(0, 0, 0, 0, 0, 0));

            while (!pq.isEmpty()) {
                State state = pq.poll();
                int hl = state.hl, r = state.r, c = state.c, dr = state.dr, dc = state.dc, n = state.n;

                if (!part2) {
                    if (r == grid.size() - 1 && c == grid.get(0).size() - 1) {
                        System.out.println("Part1: " + hl);
                        break;
                    }
                }
                if (part2) {
                    if (r == grid.size() - 1 && c == grid.get(0).size() - 1 && n >= 4) {
                        System.out.println("Part2: " + hl);
                        break;
                    }
                }

                if (seen.contains(state)) {
                    continue;
                }

                seen.add(state);

                if (!part2) {
                    if (n < 3 && (dr != 0 || dc != 0)) {
                        int nr = r + dr;
                        int nc = c + dc;
                        if (0 <= nr && nr < grid.size() && 0 <= nc && nc < grid.get(0).size()) {
                            pq.add(new State(hl + grid.get(nr).get(nc), nr, nc, dr, dc, n + 1));
                        }
                    }

                    int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
                    for (int[] direction : directions) {
                        int ndr = direction[0];
                        int ndc = direction[1];
                        if ((ndr != dr || ndc != dc) && (ndr != -dr || ndc != -dc)) {
                            int nr = r + ndr;
                            int nc = c + ndc;
                            if (0 <= nr && nr < grid.size() && 0 <= nc && nc < grid.get(0).size()) {
                                pq.add(new State(hl + grid.get(nr).get(nc), nr, nc, ndr, ndc, 1));
                            }
                        }
                    }
                }
                if (part2) {
                    if (n < 10 && (dr != 0 || dc != 0)) {
                        int nr = r + dr;
                        int nc = c + dc;
                        if (0 <= nr && nr < grid.size() && 0 <= nc && nc < grid.get(0).size()) {
                            pq.add(new State(hl + grid.get(nr).get(nc), nr, nc, dr, dc, n + 1));
                        }
                    }

                    if (n >= 4 || (dr == 0 && dc == 0)) {
                        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
                        for (int[] direction : directions) {
                            int ndr = direction[0];
                            int ndc = direction[1];
                            if ((ndr != dr || ndc != dc) && (ndr != -dr || ndc != -dc)) {
                                int nr = r + ndr;
                                int nc = c + ndc;
                                if (0 <= nr && nr < grid.size() && 0 <= nc && nc < grid.get(0).size()) {
                                    pq.add(new State(hl + grid.get(nr).get(nc), nr, nc, ndr, ndc, 1));
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    static class State implements Comparable<State> {
        int hl, r, c, dr, dc, n;

        State(int hl, int r, int c, int dr, int dc, int n) {
            this.hl = hl;
            this.r = r;
            this.c = c;
            this.dr = dr;
            this.dc = dc;
            this.n = n;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.hl, other.hl);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            State state = (State) obj;
            return r == state.r && c == state.c && dr == state.dr && dc == state.dc && n == state.n;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, dr, dc, n);
        }
    }
}
