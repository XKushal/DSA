class FindLuckyIntegerInAnArray {
    public int bruteForceFindLucky(int[] arr) {
        int lucky = -1;

        for (int candidate : arr) {
            int count = 0;

            for (int value : arr) {
                if (value == candidate) {
                    count++;
                }
            }

            if (count == candidate) {
                lucky = Math.max(lucky, candidate);
            }
        }

        return lucky;
    }

    public int findLucky(int[] arr) {
        int[] frequencies = new int[501];

        for (int value : arr) {
            frequencies[value]++;
        }

        for (int value = frequencies.length - 1; value >= 1; value--) {
            if (frequencies[value] == value) {
                return value;
            }
        }

        return -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindLuckyIntegerInAnArray solution = new FindLuckyIntegerInAnArray();

        check("brute force sample one", solution.bruteForceFindLucky(new int[] {2, 2, 3, 4}), 2);
        check("brute force sample two", solution.bruteForceFindLucky(new int[] {1, 2, 2, 3, 3, 3}), 3);

        check("sample one", solution.findLucky(new int[] {2, 2, 3, 4}), 2);
        check("sample two", solution.findLucky(new int[] {1, 2, 2, 3, 3, 3}), 3);
        check("sample three", solution.findLucky(new int[] {2, 2, 2, 3, 3}), -1);
        check("single lucky value", solution.findLucky(new int[] {1}), 1);
        check("chooses largest lucky value", solution.findLucky(new int[] {1, 2, 2, 4, 4, 4, 4}), 4);
    }
}

/*
 * Brute Force:
 * I count every array value by scanning the whole array for each candidate, then
 * keep the largest value whose count equals the value itself.
 *
 * Time Complexity: O(n^2), because every candidate can trigger a full scan.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I count each value with a frequency table, then scan from largest to smallest
 * to return the greatest value whose frequency equals itself.
 *
 * Time Complexity: O(n + k), where n is the array length and k is the value
 * range scanned in the frequency table.
 * Space Complexity: O(k), because the frequency table stores counts by value.
 */
