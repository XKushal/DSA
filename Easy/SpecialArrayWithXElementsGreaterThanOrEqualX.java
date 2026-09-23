class SpecialArrayWithXElementsGreaterThanOrEqualX {
    public int bruteForceSpecialArray(int[] nums) {
        for (int candidate = 0; candidate <= nums.length; candidate++) {
            int count = 0;

            for (int num : nums) {
                if (num >= candidate) {
                    count++;
                }
            }

            if (count == candidate) {
                return candidate;
            }
        }

        return -1;
    }

    public int specialArray(int[] nums) {
        int n = nums.length;
        int[] buckets = new int[n + 1];

        for (int num : nums) {
            buckets[Math.min(num, n)]++;
        }

        int atLeast = 0;

        for (int candidate = n; candidate >= 0; candidate--) {
            atLeast += buckets[candidate];

            if (atLeast == candidate) {
                return candidate;
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
        SpecialArrayWithXElementsGreaterThanOrEqualX solution =
                new SpecialArrayWithXElementsGreaterThanOrEqualX();

        check("brute force sample one", solution.bruteForceSpecialArray(new int[] {3, 5}), 2);
        check("brute force sample two", solution.bruteForceSpecialArray(new int[] {0, 0}), -1);

        check("sample one", solution.specialArray(new int[] {3, 5}), 2);
        check("sample two", solution.specialArray(new int[] {0, 0}), -1);
        check("sample three", solution.specialArray(new int[] {0, 4, 3, 0, 4}), 3);
        check("all large values", solution.specialArray(new int[] {100, 100, 100}), 3);
        check("single zero", solution.specialArray(new int[] {0}), -1);
    }
}

/*
 * Brute Force:
 * I try every possible special value and count how many numbers are at least
 * that value.
 *
 * Time Complexity: O(n^2), because there are n + 1 candidate values and each
 * candidate can scan the full array.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I bucket every number above n into the n bucket, then scan candidate values
 * from n down to 0 while maintaining how many numbers are at least the current
 * candidate.
 *
 * Time Complexity: O(n), where n is the number of values.
 * Space Complexity: O(n), because the bucket table has n + 1 entries.
 */
