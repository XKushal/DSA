class MajorityElement {
    public int bruteForceMajorityElement(int[] nums) {
        int neededCount = nums.length / 2;

        for (int candidate : nums) {
            int count = 0;

            for (int number : nums) {
                if (candidate == number) {
                    count++;
                }
            }

            if (count > neededCount) {
                return candidate;
            }
        }

        return -1;
    }

    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int number : nums) {
            if (count == 0) {
                candidate = number;
            }

            count += number == candidate ? 1 : -1;
        }

        return candidate;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();

        check("brute force finds majority in short array", solution.bruteForceMajorityElement(new int[] {3, 2, 3}), 3);
        check("brute force finds majority after repeats", solution.bruteForceMajorityElement(new int[] {2, 2, 1, 1, 1, 2, 2}), 2);

        check("finds majority in short array", solution.majorityElement(new int[] {3, 2, 3}), 3);
        check("finds majority after repeats", solution.majorityElement(new int[] {2, 2, 1, 1, 1, 2, 2}), 2);
        check("handles one number", solution.majorityElement(new int[] {5}), 5);
    }
}

/*
 * Brute Force:
 * I treat each value as a candidate and count its occurrences across the whole
 * array. The majority element is the value whose count is greater than n / 2.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because only counters are used.
 *
 * Optimal Interview Solution:
 * I use Boyer-Moore voting. Matching values increase the vote count, different
 * values decrease it, and the guaranteed majority value remains as the final
 * candidate.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because only the candidate and count are stored.
 */
