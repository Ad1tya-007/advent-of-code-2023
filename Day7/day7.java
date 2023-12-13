package Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day7 {

    public static void main(String[] args) {

        try {

            for (boolean p : new boolean[] { false, true }) {
                BufferedReader br = new BufferedReader(new FileReader("Day7/input.txt"));
                long val = 0;
                List<Hand> cards = new ArrayList<>();

                for (String line : br.lines().toList()) {
                    cards.add(new Hand(line, p));
                }

                Collections.sort(cards);

                int rank = cards.size();
                for (Hand card : cards) {
                    card.setRank(rank);
                    val += card.getScore();
                    rank--;
                }

                String msg = p ? "Part2: " : "Part1: ";

                System.out.println(msg + "" + val);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}