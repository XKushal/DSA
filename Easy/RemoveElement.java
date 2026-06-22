class RemoveElement {
    public int bruteForceRemoveElement(int[] nums, int val) {
        int[] kept = new int[nums.length];
        int keptCount = 0;

        for (int num : nums) {
            if (num != val) {
                kept[keptCount] = num;
                keptCount++;
            }
        }

        for (int index = 0; index < keptCount; index++) {
            nums[index] = kept[index];
        }

        return keptCount;
    }

    public int removeElement(int[] nums, int val) {
        int nextWrite = 0;

        for (int num : nums) {
            if (num != val) {
                nums[nextWrite] = num;
                nextWrite++;
            }
        }

        return nextWrite;
    }

    private static void check(String name, int[] nums, int actualLength, int expectedLength, int[] expectedPrefix) {
        if (actualLength != expectedLength) {
            throw new AssertionError(name + " expected length " + expectedLength + " but got " + actualLength);
        }

        for (int index = 0; index < expectedPrefix.length; index++) {
            if (nums[index] != expectedPrefix[index]) {
                throw new AssertionError(name + " differed at index " + index);
            }
        }
    }

    public static void main(String[] args) {
        RemoveElement solution = new RemoveElement();

        int[] bruteMixed = {3, 2, 2, 3};
        check("brute force removes matching values", bruteMixed, solution.bruteForceRemoveElement(bruteMixed, 3), 2, new int[] {2, 2});

        int[] bruteNone = {1, 2, 3};
        check("brute force keeps values when none match", bruteNone, solution.bruteForceRemoveElement(bruteNone, 4), 3, new int[] {1, 2, 3});

        int[] mixed = {3, 2, 2, 3};
        check("removes matching values", mixed, solution.removeElement(mixed, 3), 2, new int[] {2, 2});

        int[] repeated = {0, 1, 2, 2, 3, 0, 4, 2};
        check("removes repeated values", repeated, solution.removeElement(repeated, 2), 5, new int[] {0, 1, 3, 0, 4});

        int[] allRemoved = {5, 5};
        check("returns zero when all values match", allRemoved, solution.removeElement(allRemoved, 5), 0, new int[] {});
    }
}

/*
 * Brute Force:
 * I copy every value that is not equal to val into a temporary array, then copy
 * that kept prefix back into nums.
 *
 * Time Complexity: O(n), where n is the number of elements.
 * Space Complexity: O(n), because a temporary array stores the kept values.
 *
 * Optimal Interview Solution:
 * I keep a write pointer for the next non-val position. Each number that should
 * stay is written into that position, so the first returned-length elements are
 * the required result.
 *
 * Time Complexity: O(n), where n is the number of elements.
 * Space Complexity: O(1), because the updates are done in place.
 */
