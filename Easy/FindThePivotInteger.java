class FindThePivotInteger {
    public int bruteForcePivotInteger(int n) {
        for (int candidate = 1; candidate <= n; candidate++) {
            int leftSum = 0;
            int rightSum = 0;

            for (int value = 1; value <= candidate; value++) {
                leftSum += value;
            }

            for (int value = candidate; value <= n; value++) {
                rightSum += value;
            }

            if (leftSum == rightSum) {
                return candidate;
            }
        }

        return -1;
    }

    public int pivotInteger(int n) {
        int total = n * (n + 1) / 2;
        int pivot = (int) Math.sqrt(total);

        return pivot * pivot == total ? pivot : -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindThePivotInteger solution = new FindThePivotInteger();

        check("brute force sample one", solution.bruteForcePivotInteger(8), 6);
        check("brute force sample two", solution.bruteForcePivotInteger(1), 1);
        check("brute force sample three", solution.bruteForcePivotInteger(4), -1);

        check("sample one", solution.pivotInteger(8), 6);
        check("sample two", solution.pivotInteger(1), 1);
        check("sample three", solution.pivotInteger(4), -1);
        check("larger pivot", solution.pivotInteger(49), 35);
        check("no pivot", solution.pivotInteger(20), -1);
    }
}

/*
 * Brute Force:
 * I try every possible pivot, summing 1 through the pivot and the pivot
 * through n each time.
 *
 * Time Complexity: O(n^2), where n is the upper bound.
 * Space Complexity: O(1), because only running sums are stored.
 *
 * Optimal Interview Solution:
 * The pivot exists when the total sum from 1 to n is a perfect square, because
 * balancing the two sides reduces to pivot * pivot = total.
 *
 * Time Complexity: O(1), because the solution does a constant amount of math.
 * Space Complexity: O(1), because no extra data structures are used.
 */
