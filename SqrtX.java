class SqrtX {
    public int bruteForceMySqrt(int x) {
        int candidate = 0;

        while ((long) (candidate + 1) * (candidate + 1) <= x) {
            candidate++;
        }

        return candidate;
    }

    public int mySqrt(int x) {
        int left = 1;
        int right = x;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid <= x / mid) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SqrtX solution = new SqrtX();

        check("brute force handles zero", solution.bruteForceMySqrt(0), 0);
        check("brute force floors a non-perfect square", solution.bruteForceMySqrt(8), 2);

        check("handles zero", solution.mySqrt(0), 0);
        check("handles one", solution.mySqrt(1), 1);
        check("finds a perfect square", solution.mySqrt(4), 2);
        check("floors a non-perfect square", solution.mySqrt(8), 2);
        check("handles a large non-perfect square", solution.mySqrt(2147395599), 46339);
        check("handles the integer limit", solution.mySqrt(2147483647), 46340);
    }
}

/*
 * Brute Force:
 * I test every candidate root from zero upward until the next square would be
 * larger than x, then return the last valid candidate.
 *
 * Time Complexity: O(sqrt(x)), because the loop advances once per possible
 * integer square root value.
 * Space Complexity: O(1), because only a few counters are used.
 *
 * Optimal Interview Solution:
 * I binary search the answer range and compare mid against x / mid to avoid
 * integer overflow while finding the largest value whose square fits in x.
 *
 * Time Complexity: O(log x), because the search range is halved each step.
 * Space Complexity: O(1), because only boundary counters are used.
 */
