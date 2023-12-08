package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day4 {
    public static void main(String[] args) {

        String text = """
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
                """;

        try {
            int sum = 0;
            BufferedReader br = new BufferedReader(new FileReader("Day4/input.txt"));
            int[] cards = new int[189];
            Arrays.fill(cards, 1); // initialize as 1
            int card_index = 0;

            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                String[] arr = line.split(":");
                String[] _numbers = arr[1].split("\\|");
                String[] winningNumbers = _numbers[0].trim().split("\\s+");
                String[] givenNumbers = _numbers[1].trim().split("\\s+");
                int match = 0;
                // int points = 0;
                for (int i = 0; i < winningNumbers.length; i++) {

                    for (int j = 0; j < givenNumbers.length; j++) {

                        if (winningNumbers[i].equals(givenNumbers[j])) {
                            match += 1;
                            // if (match == 1) {
                            // points = 1;
                            // } else {
                            // points = points * 2;
                            // }
                        }

                    }

                }

                for (int n = 1; n <= match; n++) {
                    cards[card_index + n] += cards[card_index];
                }

                card_index++;
            }

            // System.out.println(sum);
            for (int a = 0; a < cards.length; a++) {
                // System.out.print(cards[a] + " ");
                sum += cards[a];
            }

            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}