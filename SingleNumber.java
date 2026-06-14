class SingleNumber {
    public int bruteForceSingleNumber(int[] nums) {
        for (int candidate : nums) {
            int count = 0;

            for (int number : nums) {
                if (candidate == number) {
                    count++;
                }
            }

            if (count == 1) {
                return candidate;
            }
        }

        return -1;
    }

    public int singleNumber(int[] nums) {
        int answer = 0;

        for (int number : nums) {
            answer ^= number;
        }

        return answer;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();

        check("brute force finds single value at the end", solution.bruteForceSingleNumber(new int[] {4, 1, 2, 1, 2}), 4);
        check("brute force finds single value in the middle", solution.bruteForceSingleNumber(new int[] {2, 1, 2}), 1);

        check("finds single value at the end", solution.singleNumber(new int[] {4, 1, 2, 1, 2}), 4);
        check("finds single value in the middle", solution.singleNumber(new int[] {2, 1, 2}), 1);
        check("handles one number", solution.singleNumber(new int[] {7}), 7);
    }
}

/*
 * Brute Force:
 * I treat each value as a candidate and count how many times it appears in the
 * whole array. The value with one occurrence is the answer.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because only counters are used.
 *
 * Optimal Interview Solution:
 * I XOR every number together. Equal values cancel each other out, and the only
 * value left is the number that appears once.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because only one running XOR value is stored.
 */
