import java.util.HashSet;
import java.util.Set;

class NumberOfArithmeticTriplets {
    public int bruteForceArithmeticTriplets(int[] nums, int diff) {
        int triplets = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        triplets++;
                    }
                }
            }
        }

        return triplets;
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> values = new HashSet<>();

        for (int num : nums) {
            values.add(num);
        }

        int triplets = 0;

        for (int num : nums) {
            if (values.contains(num + diff) && values.contains(num + diff + diff)) {
                triplets++;
            }
        }

        return triplets;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    public static void main(String[] args) {
        NumberOfArithmeticTriplets solution = new NumberOfArithmeticTriplets();

        check("brute force sample", solution.bruteForceArithmeticTriplets(nums(0, 1, 4, 6, 7, 10), 3), 2);
        check("brute force no triplets", solution.bruteForceArithmeticTriplets(nums(1, 3, 5), 3), 0);

        check("sample", solution.arithmeticTriplets(nums(0, 1, 4, 6, 7, 10), 3), 2);
        check("consecutive values", solution.arithmeticTriplets(nums(4, 5, 6, 7, 8, 9), 2), 2);
        check("single triplet", solution.arithmeticTriplets(nums(1, 2, 3, 4), 1), 2);
        check("no triplets", solution.arithmeticTriplets(nums(1, 3, 5), 3), 0);
    }
}

/*
 * Brute Force:
 * I check every ordered group of three indices and count it when both adjacent
 * differences equal diff.
 *
 * Time Complexity: O(n^3), because every possible triplet is inspected.
 * Space Complexity: O(1), because only the answer counter is stored.
 *
 * Optimal Interview Solution:
 * I store every value in a set, then use each number as the first value and
 * check whether the next two values in the arithmetic sequence exist.
 *
 * Time Complexity: O(n), because each number is inserted and checked once.
 * Space Complexity: O(n), because the set stores the input values.
 */
