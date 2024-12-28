package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public  static int add(String numbers) throws  IllegalAccessException{

        // Step 1: Handle empty string
        if (numbers.isEmpty()) {
            return 0;
        }

        // Step 2: Check for custom delimiter
        String delimiter = ",";
        if (numbers.startsWith("//")) {
            int delimiterEndIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterEndIndex);
            numbers = numbers.substring(delimiterEndIndex + 1);
        }

        // Step 3: Handle newlines as delimiters along with commas or custom delimiter
        String[] numberArray = numbers.split(delimiter + "|\\n");

        // Step 4: Convert to integers and validate negatives
        List<Integer> integers = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        for (String number : numberArray) {
            if (!number.trim().isEmpty()) { // Ignore empty strings
                int value = Integer.parseInt(number);
                if (value < 0) {
                    negatives.add(value);
                } else {
                    integers.add(value);
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed " + negatives);
        }

        return integers.stream().mapToInt(Integer::intValue).sum();
    }


    public static void main(String[] args) {

        try {
            System.out.println(add("1"));
            System.out.println(add("1\n2,3"));
            System.out.println(add("1,-2,3,-4"));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        System.out.println();
    }
}