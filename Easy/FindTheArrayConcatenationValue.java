class FindTheArrayConcatenationValue {
    public long bruteForceFindTheArrayConcVal(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        long total = 0;

        while (left < right) {
            total += Long.parseLong(String.valueOf(nums[left]) + nums[right]);
            left++;
            right--;
        }

        if (left == right) {
            total += nums[left];
        }

        return total;
    }

    public long findTheArrayConcVal(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        long total = 0;

        while (left < right) {
            total += concatenate(nums[left], nums[right]);
            left++;
            right--;
        }

        if (left == right) {
            total += nums[left];
        }

        return total;
    }

    private long concatenate(int first, int second) {
        int placeValue = 10;
        int value = second;

        while (value >= 10) {
            value /= 10;
            placeValue *= 10;
        }

        return (long) first * placeValue + second;
    }

    private static void check(String name, long actual, long expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindTheArrayConcatenationValue solution = new FindTheArrayConcatenationValue();

        check("brute force sample", solution.bruteForceFindTheArrayConcVal(
                new int[] {7, 52, 2, 4}), 596);
        check("brute force odd length", solution.bruteForceFindTheArrayConcVal(
                new int[] {5, 14, 13, 8, 12}), 673);

        check("sample", solution.findTheArrayConcVal(new int[] {7, 52, 2, 4}), 596);
        check("odd length", solution.findTheArrayConcVal(new int[] {5, 14, 13, 8, 12}), 673);
        check("single value", solution.findTheArrayConcVal(new int[] {9}), 9);
        check("multi digit values", solution.findTheArrayConcVal(new int[] {100, 10, 5, 99}), 10099 + 105);
    }
}

/*
 * Brute Force:
 * I use two pointers to take the first and last values, join them as strings,
 * parse the joined value, and add it to the running total.
 *
 * Time Complexity: O(n * d), where n is the number of values and d is the
 * maximum number of digits in a value.
 * Space Complexity: O(d), because each joined string stores the digits for one
 * pair.
 *
 * Optimal Interview Solution:
 * I keep the same two-pointer walk but concatenate the numbers arithmetically
 * by multiplying the left value by the correct place value before adding the
 * right value.
 *
 * Time Complexity: O(n * d), where d is the maximum number of digits needed to
 * find each right value's place value.
 * Space Complexity: O(1), because only counters and the total are stored.
 */
