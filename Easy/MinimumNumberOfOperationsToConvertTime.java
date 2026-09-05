import java.util.Arrays;

class MinimumNumberOfOperationsToConvertTime {
    public int bruteForceConvertTime(String current, String correct) {
        int difference = toMinutes(correct) - toMinutes(current);
        int[] increments = {1, 5, 15, 60};
        int[] dp = new int[difference + 1];
        Arrays.fill(dp, difference + 1);
        dp[0] = 0;

        for (int minutes = 1; minutes <= difference; minutes++) {
            for (int increment : increments) {
                if (minutes >= increment) {
                    dp[minutes] = Math.min(dp[minutes], dp[minutes - increment] + 1);
                }
            }
        }

        return dp[difference];
    }

    public int convertTime(String current, String correct) {
        int difference = toMinutes(correct) - toMinutes(current);
        int[] increments = {60, 15, 5, 1};
        int operations = 0;

        for (int increment : increments) {
            operations += difference / increment;
            difference %= increment;
        }

        return operations;
    }

    private int toMinutes(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));

        return hours * 60 + minutes;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MinimumNumberOfOperationsToConvertTime solution = new MinimumNumberOfOperationsToConvertTime();

        check("brute force sample", solution.bruteForceConvertTime("02:30", "04:35"), 3);
        check("brute force one minute", solution.bruteForceConvertTime("11:00", "11:01"), 1);

        check("sample", solution.convertTime("02:30", "04:35"), 3);
        check("one minute", solution.convertTime("11:00", "11:01"), 1);
        check("mixed increments", solution.convertTime("09:41", "10:34"), 7);
        check("same time", solution.convertTime("12:00", "12:00"), 0);
    }
}

/*
 * Brute Force:
 * I compute the minute difference, then use dynamic programming to try every
 * allowed operation as the last step for each reachable minute count.
 *
 * Time Complexity: O(d), where d is the minute difference between the two
 * times.
 * Space Complexity: O(d), because the DP table stores the best count for each
 * minute total up to d.
 *
 * Optimal Interview Solution:
 * I greedily use as many 60, 15, 5, then 1 minute operations as possible,
 * because each larger operation is always worth at least as much as the
 * smaller operations that compose it.
 *
 * Time Complexity: O(1), because there are always four operation sizes.
 * Space Complexity: O(1), because only the remaining difference and operation
 * count are stored.
 */
