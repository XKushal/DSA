class UniqueNumberOfOccurrences {
    public boolean bruteForceUniqueOccurrences(int[] arr) {
        boolean[] visited = new boolean[arr.length];
        int[] counts = new int[arr.length];
        int countSize = 0;

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }

            int count = 0;

            for (int j = i; j < arr.length; j++) {
                if (arr[j] == arr[i]) {
                    visited[j] = true;
                    count++;
                }
            }

            counts[countSize] = count;
            countSize++;
        }

        for (int i = 0; i < countSize; i++) {
            for (int j = i + 1; j < countSize; j++) {
                if (counts[i] == counts[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean uniqueOccurrences(int[] arr) {
        int[] frequencies = new int[2001];

        for (int value : arr) {
            frequencies[value + 1000]++;
        }

        boolean[] seenCounts = new boolean[arr.length + 1];

        for (int frequency : frequencies) {
            if (frequency == 0) {
                continue;
            }

            if (seenCounts[frequency]) {
                return false;
            }

            seenCounts[frequency] = true;
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        UniqueNumberOfOccurrences solution = new UniqueNumberOfOccurrences();

        check("brute force sample one", solution.bruteForceUniqueOccurrences(
                new int[] {1, 2, 2, 1, 1, 3}), true);
        check("brute force sample two", solution.bruteForceUniqueOccurrences(
                new int[] {1, 2}), false);
        check("brute force sample three", solution.bruteForceUniqueOccurrences(
                new int[] {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}), true);

        check("sample one", solution.uniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}), true);
        check("sample two", solution.uniqueOccurrences(new int[] {1, 2}), false);
        check("sample three", solution.uniqueOccurrences(
                new int[] {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}), true);
        check("all same", solution.uniqueOccurrences(new int[] {5, 5, 5}), true);
        check("two matching counts", solution.uniqueOccurrences(new int[] {4, 4, 5, 5}), false);
        check("negative values", solution.uniqueOccurrences(new int[] {-1000, -1000, 0, 0, 0, 1000}), true);
    }
}

/*
 * Brute Force:
 * I count each distinct value by scanning the rest of the array, then compare
 * every pair of occurrence counts for duplicates.
 *
 * Time Complexity: O(n^2), where n is the length of the array.
 * Space Complexity: O(n), because visited flags and occurrence counts are
 * stored.
 *
 * Optimal Interview Solution:
 * I use the bounded value range to count every number, then track whether each
 * nonzero occurrence count has already appeared.
 *
 * Time Complexity: O(n + m), where m is the bounded value range -1000 through
 * 1000.
 * Space Complexity: O(n + m), because value frequencies and seen occurrence
 * counts are stored.
 */
