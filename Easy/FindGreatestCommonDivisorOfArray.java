class FindGreatestCommonDivisorOfArray {
    public int bruteForceFindGCD(int[] nums) {
        int minimum = nums[0];
        int maximum = nums[0];

        for (int num : nums) {
            minimum = Math.min(minimum, num);
            maximum = Math.max(maximum, num);
        }

        for (int divisor = minimum; divisor >= 1; divisor--) {
            if (minimum % divisor == 0 && maximum % divisor == 0) {
                return divisor;
            }
        }

        return 1;
    }

    public int findGCD(int[] nums) {
        int minimum = nums[0];
        int maximum = nums[0];

        for (int num : nums) {
            minimum = Math.min(minimum, num);
            maximum = Math.max(maximum, num);
        }

        return gcd(maximum, minimum);
    }

    private int gcd(int larger, int smaller) {
        while (smaller != 0) {
            int remainder = larger % smaller;
            larger = smaller;
            smaller = remainder;
        }

        return larger;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindGreatestCommonDivisorOfArray solution = new FindGreatestCommonDivisorOfArray();

        check("brute force sample one", solution.bruteForceFindGCD(new int[] {2, 5, 6, 9, 10}), 2);
        check("brute force sample two", solution.bruteForceFindGCD(new int[] {7, 5, 6, 8, 3}), 1);

        check("sample one", solution.findGCD(new int[] {2, 5, 6, 9, 10}), 2);
        check("sample two", solution.findGCD(new int[] {7, 5, 6, 8, 3}), 1);
        check("sample three", solution.findGCD(new int[] {3, 3}), 3);
        check("shared factor", solution.findGCD(new int[] {12, 18, 24, 30}), 6);
        check("coprime extremes", solution.findGCD(new int[] {11, 22, 35, 49}), 1);
    }
}

/*
 * Brute Force:
 * I find the smallest and largest values, then test every possible divisor
 * from the smaller value down until one divides both extremes.
 *
 * Time Complexity: O(n + m), where n is the length of nums and m is the
 * smallest value in nums.
 * Space Complexity: O(1), because only scalar values are stored.
 *
 * Optimal Interview Solution:
 * I find the smallest and largest values, then use Euclid's algorithm to
 * compute their greatest common divisor.
 *
 * Time Complexity: O(n + log m), where n is the length of nums and m is the
 * smaller of the minimum and maximum values.
 * Space Complexity: O(1), because only scalar values are stored.
 */
