class CountGoodTriplets {
    public int bruteForceCountGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        int[] leftFrequency = new int[1001];

        for (int j = 1; j < arr.length - 1; j++) {
            leftFrequency[arr[j - 1]]++;

            int[] prefixCounts = new int[1001];
            prefixCounts[0] = leftFrequency[0];

            for (int value = 1; value < prefixCounts.length; value++) {
                prefixCounts[value] = prefixCounts[value - 1] + leftFrequency[value];
            }

            for (int k = j + 1; k < arr.length; k++) {
                if (Math.abs(arr[j] - arr[k]) > b) {
                    continue;
                }

                int low = Math.max(0, Math.max(arr[j] - a, arr[k] - c));
                int high = Math.min(1000, Math.min(arr[j] + a, arr[k] + c));

                if (low <= high) {
                    count += prefixCounts[high] - (low == 0 ? 0 : prefixCounts[low - 1]);
                }
            }
        }

        return count;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountGoodTriplets solution = new CountGoodTriplets();

        check("brute force sample one", solution.bruteForceCountGoodTriplets(
                new int[] {3, 0, 1, 1, 9, 7}, 7, 2, 3), 4);
        check("brute force sample two", solution.bruteForceCountGoodTriplets(
                new int[] {1, 1, 2, 2, 3}, 0, 0, 1), 0);

        check("sample one", solution.countGoodTriplets(new int[] {3, 0, 1, 1, 9, 7}, 7, 2, 3), 4);
        check("sample two", solution.countGoodTriplets(new int[] {1, 1, 2, 2, 3}, 0, 0, 1), 0);
        check("all equal", solution.countGoodTriplets(new int[] {5, 5, 5, 5}, 0, 0, 0), 4);
        check("single triplet", solution.countGoodTriplets(new int[] {1, 3, 5}, 2, 2, 4), 1);
        check("wide limits", solution.countGoodTriplets(new int[] {0, 2, 4, 6}, 10, 10, 10), 4);
    }
}

/*
 * Brute Force:
 * I check every i, j, k triplet and count it when all three absolute
 * difference constraints are satisfied.
 *
 * Time Complexity: O(n^3), where n is the length of the array.
 * Space Complexity: O(1), because only the count is stored.
 *
 * Optimal Interview Solution:
 * I fix the middle index and build prefix counts for values already seen on
 * the left. For each right index, the valid left values are a range
 * intersection that can be counted in constant time.
 *
 * Time Complexity: O(n^2 + n * m), where m is the bounded value range 0
 * through 1000 from the constraints.
 * Space Complexity: O(m), because value counts are stored for the bounded
 * range.
 */
