class MaxConsecutiveOnes {
    public int bruteForceFindMaxConsecutiveOnes(int[] nums) {
        int best = 0;

        for (int start = 0; start < nums.length; start++) {
            int consecutiveOnes = 0;

            for (int end = start; end < nums.length && nums[end] == 1; end++) {
                consecutiveOnes++;
            }

            best = Math.max(best, consecutiveOnes);
        }

        return best;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int best = 0;
        int currentRun = 0;

        for (int num : nums) {
            if (num == 1) {
                currentRun++;
                best = Math.max(best, currentRun);
            } else {
                currentRun = 0;
            }
        }

        return best;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

        check("brute force handles middle run", solution.bruteForceFindMaxConsecutiveOnes(new int[] {1, 1, 0, 1, 1, 1}), 3);
        check("brute force handles all zeroes", solution.bruteForceFindMaxConsecutiveOnes(new int[] {0, 0, 0}), 0);

        check("handles middle run", solution.findMaxConsecutiveOnes(new int[] {1, 1, 0, 1, 1, 1}), 3);
        check("handles run at beginning", solution.findMaxConsecutiveOnes(new int[] {1, 1, 1, 0, 1}), 3);
        check("handles run at end", solution.findMaxConsecutiveOnes(new int[] {0, 1, 1, 1, 1}), 4);
        check("handles all ones", solution.findMaxConsecutiveOnes(new int[] {1, 1, 1}), 3);
        check("handles all zeroes", solution.findMaxConsecutiveOnes(new int[] {0, 0, 0}), 0);
    }
}

/*
 * Brute Force:
 * I start a scan at each index and count how many consecutive ones appear from
 * that position before taking the best count seen.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I make one pass, tracking the current run of ones and resetting it whenever
 * a zero appears.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because only the current and best counts are stored.
 */
