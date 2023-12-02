package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class day1 {
    public static void main(String[] args) {

        String text = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;

        String newText = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;

        Map<String, String> numbers = new HashMap<>();
        numbers.put("zero", "0");
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Day1/input.txt"));
            int sum = 0;
            // for (String line : newText.split("\n")) { // br.lines().toList()) {//
            for (String line : br.lines().toList()) {
                // System.out.println(line);
                int index = 10000;
                while (index == 10000) {
                    String first = "";
                    for (Map.Entry<String, String> entry : numbers.entrySet()) {
                        int pos = line.indexOf(entry.getKey());
                        if (pos != -1 && pos < index) {
                            index = line.indexOf(entry.getKey());
                            first = entry.getKey();
                        }
                    }
                    index = -1;
                    if (numbers.containsKey(first)) {
                        line = line.replaceFirst(first, numbers.get(first));
                        index = 10000;
                    }
                }

                // System.out.println(line);

                int firstNumber = -1;
                int secondNumber = -1;
                for (String c : line.split((""))) {
                    if (Character.isDigit(c.charAt(0))) {
                        if (firstNumber == -1) {
                            firstNumber = Integer.parseInt(c);
                        }
                        secondNumber = Integer.parseInt(c);
                    }

                }
                firstNumber *= 10;
                System.out.println(firstNumber + secondNumber);
                sum += firstNumber + secondNumber;
                // System.out.println(sum);
            }
            System.out.println(sum);

        } catch (

        Exception e) {
            e.printStackTrace();
        }

    }

}
