import java.util.HashSet;
import java.util.Set;

class HappyNumber {
    public boolean bruteForceIsHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumOfSquaredDigits(n);
        }

        return n == 1;
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = sumOfSquaredDigits(n);

        while (fast != 1 && slow != fast) {
            slow = sumOfSquaredDigits(slow);
            fast = sumOfSquaredDigits(sumOfSquaredDigits(fast));
        }

        return fast == 1;
    }

    private int sumOfSquaredDigits(int number) {
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }

        return sum;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        HappyNumber solution = new HappyNumber();

        check("brute force accepts a happy number", solution.bruteForceIsHappy(19), true);
        check("brute force rejects an unhappy number", solution.bruteForceIsHappy(2), false);

        check("accepts a happy number", solution.isHappy(19), true);
        check("accepts one as already happy", solution.isHappy(1), true);
        check("rejects an unhappy number", solution.isHappy(2), false);
    }
}

/*
 * Brute Force:
 * I repeatedly replace the number with the sum of the squares of its digits
 * and store each value I have already seen. Reaching one means the number is
 * happy; repeating a previous value means the sequence is stuck in a cycle.
 *
 * Time Complexity: O(k * d), where k is the number of generated values before
 * the sequence reaches one or repeats, and d is the number of digits processed
 * per value.
 * Space Complexity: O(k), because the generated values are stored.
 *
 * Optimal Interview Solution:
 * I treat the repeated digit-square transformation like a linked list and use
 * slow and fast pointers to detect a cycle without storing prior values.
 *
 * Time Complexity: O(k * d), where k is the number of generated values before
 * the sequence reaches one or a cycle is detected, and d is the number of
 * digits processed per value.
 * Space Complexity: O(1), because only two pointer values are tracked.
 */
