package Day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day9 {
    public static void main(String[] args) {

        String text = """
                0 3 6 9 12 15
                1 3 6 10 15 21
                10 13 16 21 30 45
                """;

        try {

            int sum = 0;
            BufferedReader br = new BufferedReader(new FileReader("Day9/input.txt"));
            for (String line : br.lines().toList()) {
                // for (String line : text.split("\n")) {
                String[] numbers = line.split(" ");
                List<Integer> values = new ArrayList<>();
                for (String number : numbers) {
                    values.add(Integer.parseInt(number));
                }
                // System.out.println(values);
                // int nextNum = values.get(values.size() - 1) + getNextNumberAddition(values);
                // System.out.println(nextNum);
                // sum += nextNum;
                int prevNum = values.get(0) - getPrevNumberAddition(values);
                // System.out.println(prevNum);
                sum += prevNum;
            }
            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static int getNextNumberAddition(List<Integer> list) {
        List<Integer> new_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                new_list.add(list.get(i + 1) - list.get(i));
            }
        }
        if (!isAllZero(new_list)) {
            // System.out.println(new_list);
            return new_list.get(new_list.size() - 1) + getNextNumberAddition(new_list);
        } else {
            // System.out.println(new_list);
            return 0;
        }
    }

    private static int getPrevNumberAddition(List<Integer> list) {
        List<Integer> new_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                new_list.add(list.get(i + 1) - list.get(i));
            }
        }
        if (!isAllZero(new_list)) {
            // System.out.println(new_list);
            return new_list.get(0) - getPrevNumberAddition(new_list);
        } else {
            // System.out.println(new_list);
            return 0;
        }
    }

    private static boolean isAllZero(List<Integer> list) {
        Integer firstNumber = list.get(0);
        if (firstNumber != 0) {
            return false;
        }

        for (Integer number : list) {
            if (!number.equals(firstNumber)) {
                return false;
            }
        }

        return true;
    }

}
