import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FizzBuzz {
    public List<String> bruteForceFizzBuzz(int n) {
        List<String> result = new ArrayList<>();

        for (int number = 1; number <= n; number++) {
            boolean divisibleByThree = number % 3 == 0;
            boolean divisibleByFive = number % 5 == 0;

            if (divisibleByThree && divisibleByFive) {
                result.add("FizzBuzz");
            } else if (divisibleByThree) {
                result.add("Fizz");
            } else if (divisibleByFive) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(number));
            }
        }

        return result;
    }

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();

        for (int number = 1; number <= n; number++) {
            StringBuilder value = new StringBuilder();

            if (number % 3 == 0) {
                value.append("Fizz");
            }

            if (number % 5 == 0) {
                value.append("Buzz");
            }

            result.add(value.length() == 0 ? String.valueOf(number) : value.toString());
        }

        return result;
    }

    private static void check(String name, List<String> actual, List<String> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FizzBuzz solution = new FizzBuzz();

        check("brute force handles five values",
                solution.bruteForceFizzBuzz(5),
                Arrays.asList("1", "2", "Fizz", "4", "Buzz"));
        check("brute force handles fizz buzz",
                solution.bruteForceFizzBuzz(15),
                Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));

        check("handles one value",
                solution.fizzBuzz(1),
                Arrays.asList("1"));
        check("handles five values",
                solution.fizzBuzz(5),
                Arrays.asList("1", "2", "Fizz", "4", "Buzz"));
        check("handles fizz buzz",
                solution.fizzBuzz(15),
                Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"));
    }
}

/*
 * Brute Force:
 * I check each number against the FizzBuzz cases in priority order, handling
 * the combined divisibility case before the individual divisibility cases.
 *
 * Time Complexity: O(n), because every number from 1 through n is processed.
 * Space Complexity: O(n), because the output list stores one string per number.
 *
 * Optimal Interview Solution:
 * I build the replacement string by appending the word for each matching
 * divisor, then fall back to the number when no divisor matched.
 *
 * Time Complexity: O(n), because every number from 1 through n is processed.
 * Space Complexity: O(n), because the output list stores one string per number.
 */
