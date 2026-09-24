class SumOfAllOddLengthSubarrays {
    public int bruteForceSumOddLengthSubarrays(int[] arr) {
        int total = 0;

        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end += 2) {
                for (int i = start; i <= end; i++) {
                    total += arr[i];
                }
            }
        }

        return total;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int total = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int subarraysIncludingIndex = (i + 1) * (n - i);
            int oddLengthSubarrays = (subarraysIncludingIndex + 1) / 2;
            total += arr[i] * oddLengthSubarrays;
        }

        return total;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SumOfAllOddLengthSubarrays solution = new SumOfAllOddLengthSubarrays();

        check("brute force sample one", solution.bruteForceSumOddLengthSubarrays(new int[] {1, 4, 2, 5, 3}),
                58);
        check("brute force sample two", solution.bruteForceSumOddLengthSubarrays(new int[] {1, 2}), 3);

        check("sample one", solution.sumOddLengthSubarrays(new int[] {1, 4, 2, 5, 3}), 58);
        check("sample two", solution.sumOddLengthSubarrays(new int[] {1, 2}), 3);
        check("sample three", solution.sumOddLengthSubarrays(new int[] {10, 11, 12}), 66);
        check("single element", solution.sumOddLengthSubarrays(new int[] {7}), 7);
        check("four elements", solution.sumOddLengthSubarrays(new int[] {2, 4, 6, 8}), 50);
    }
}

/*
 * Brute Force:
 * I visit every odd-length subarray and add each value inside that subarray to
 * the running total.
 *
 * Time Complexity: O(n^3), because each start and odd end can scan a subarray.
 * Space Complexity: O(1), because only the running total is stored.
 *
 * Optimal Interview Solution:
 * I count how many odd-length subarrays include each index, then add that
 * index's value once for each such subarray.
 *
 * Time Complexity: O(n), where n is the number of values.
 * Space Complexity: O(1), because only counters are stored.
 */
