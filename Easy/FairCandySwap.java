import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class FairCandySwap {
    public int[] bruteForceFairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int aliceTotal = sum(aliceSizes);
        int bobTotal = sum(bobSizes);

        for (int aliceCandy : aliceSizes) {
            for (int bobCandy : bobSizes) {
                if (aliceTotal - aliceCandy + bobCandy == bobTotal - bobCandy + aliceCandy) {
                    return new int[] {aliceCandy, bobCandy};
                }
            }
        }

        return new int[] {-1, -1};
    }

    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int aliceTotal = sum(aliceSizes);
        int bobTotal = sum(bobSizes);
        int difference = (bobTotal - aliceTotal) / 2;
        Set<Integer> bobCandies = new HashSet<>();

        for (int candy : bobSizes) {
            bobCandies.add(candy);
        }

        for (int aliceCandy : aliceSizes) {
            int bobCandy = aliceCandy + difference;

            if (bobCandies.contains(bobCandy)) {
                return new int[] {aliceCandy, bobCandy};
            }
        }

        return new int[] {-1, -1};
    }

    private int sum(int[] sizes) {
        int total = 0;

        for (int size : sizes) {
            total += size;
        }

        return total;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        FairCandySwap solution = new FairCandySwap();

        check("brute force balances small boxes",
                solution.bruteForceFairCandySwap(values(1, 1), values(2, 2)), values(1, 2));
        check("brute force balances uneven arrays",
                solution.bruteForceFairCandySwap(values(1, 2), values(2, 3)), values(1, 2));
        check("brute force balances larger candy",
                solution.bruteForceFairCandySwap(values(2), values(1, 3)), values(2, 3));

        check("balances small boxes", solution.fairCandySwap(values(1, 1), values(2, 2)), values(1, 2));
        check("balances uneven arrays", solution.fairCandySwap(values(1, 2), values(2, 3)), values(1, 2));
        check("balances larger candy", solution.fairCandySwap(values(2), values(1, 3)), values(2, 3));
        check("balances repeated sizes", solution.fairCandySwap(values(1, 2, 5), values(2, 4)), values(5, 4));
    }
}

/*
 * Brute Force:
 * I try every candy size from Alice against every candy size from Bob and
 * return the first swap that makes the totals equal.
 *
 * Time Complexity: O(n * m), where n is Alice's candy count and m is Bob's.
 * Space Complexity: O(1), because only totals and loop variables are stored.
 *
 * Optimal Interview Solution:
 * I compute the half-difference between the totals. For each Alice candy x,
 * Bob needs candy x + difference, which can be checked quickly in a set.
 *
 * Time Complexity: O(n + m), because both arrays are scanned once.
 * Space Complexity: O(m), because Bob's candy sizes are stored in a set.
 */
