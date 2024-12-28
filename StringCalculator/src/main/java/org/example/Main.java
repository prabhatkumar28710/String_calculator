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

   //Test cases
    public static void testStringCalculator() {

        try {

            // Empty string
            assert add("") == 0;
            // Single number
            assert add("1") == 1;
            // Two numbers
            assert add("1,5") == 6;
            // Multiple numbers
            assert add("1,2,3,4") == 10;
            // Newline as delimiter
            assert add("1\n2,3") == 6;
            // Custom delimiter
            assert add("//;\n1;2") == 3;
            //Negetive
            try {
                add("1,-2,3,-4");
            } catch (IllegalArgumentException e) {
                assert e.getMessage().equals("negative numbers not allowed [-2, -4]");
            }

            System.out.println("All tests passed!");

        } catch (AssertionError | IllegalAccessException e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
       //test method call
        testStringCalculator();

//        try {
//            System.out.println(add("1"));
//            System.out.println(add("1\n2,3"));
//            System.out.println(add("1,-2,3,-4"));
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }

    }
}