class MaximumProductDifferenceBetweenTwoPairs {
    public int bruteForceMaxProductDifference(int[] nums) {
        int best = Integer.MIN_VALUE;

        for (int first = 0; first < nums.length; first++) {
            for (int second = 0; second < nums.length; second++) {
                if (second == first) {
                    continue;
                }

                for (int third = 0; third < nums.length; third++) {
                    if (third == first || third == second) {
                        continue;
                    }

                    for (int fourth = 0; fourth < nums.length; fourth++) {
                        if (fourth == first || fourth == second || fourth == third) {
                            continue;
                        }

                        int difference = nums[first] * nums[second] - nums[third] * nums[fourth];
                        best = Math.max(best, difference);
                    }
                }
            }
        }

        return best;
    }

    public int maxProductDifference(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest) {
                secondLargest = num;
            }

            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest) {
                secondSmallest = num;
            }
        }

        return largest * secondLargest - smallest * secondSmallest;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumProductDifferenceBetweenTwoPairs solution = new MaximumProductDifferenceBetweenTwoPairs();

        check("brute force sample", solution.bruteForceMaxProductDifference(new int[] {5, 6, 2, 7, 4}), 34);
        check("brute force sorted", solution.bruteForceMaxProductDifference(new int[] {4, 2, 5, 9, 7, 4, 8}), 64);

        check("sample", solution.maxProductDifference(new int[] {5, 6, 2, 7, 4}), 34);
        check("sorted", solution.maxProductDifference(new int[] {4, 2, 5, 9, 7, 4, 8}), 64);
        check("duplicates", solution.maxProductDifference(new int[] {10, 10, 1, 1}), 99);
        check("middle values", solution.maxProductDifference(new int[] {1, 6, 7, 5, 2, 4, 10, 6, 4}), 68);
    }
}

/*
 * Brute Force:
 * I try every ordered choice of four distinct indices and keep the largest
 * product difference.
 *
 * Time Complexity: O(n^4), because four nested loops enumerate all index
 * combinations.
 * Space Complexity: O(1), because only loop counters and the best value are
 * stored.
 *
 * Optimal Interview Solution:
 * I track the two largest values and the two smallest values in one pass, then
 * subtract the smallest pair product from the largest pair product.
 *
 * Time Complexity: O(n), because the array is scanned once.
 * Space Complexity: O(1), because only four tracked values are stored.
 */
