import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KidsWithTheGreatestNumberOfCandies {
    public List<Boolean> bruteForceKidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        for (int i = 0; i < candies.length; i++) {
            int candidateTotal = candies[i] + extraCandies;
            boolean canHaveGreatest = true;

            for (int candy : candies) {
                if (candidateTotal < candy) {
                    canHaveGreatest = false;
                    break;
                }
            }

            result.add(canHaveGreatest);
        }

        return result;
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int greatestCandies = 0;
        for (int candy : candies) {
            greatestCandies = Math.max(greatestCandies, candy);
        }

        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= greatestCandies);
        }

        return result;
    }

    private static void check(String name, List<Boolean> actual, List<Boolean> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static List<Boolean> answers(Boolean... values) {
        return Arrays.asList(values);
    }

    public static void main(String[] args) {
        KidsWithTheGreatestNumberOfCandies solution = new KidsWithTheGreatestNumberOfCandies();

        check("brute force sample", solution.bruteForceKidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3),
            answers(true, true, true, false, true));
        check("brute force low extra candies", solution.bruteForceKidsWithCandies(new int[] {4, 2, 1, 1, 2}, 1),
            answers(true, false, false, false, false));

        check("sample", solution.kidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3),
            answers(true, true, true, false, true));
        check("low extra candies", solution.kidsWithCandies(new int[] {4, 2, 1, 1, 2}, 1),
            answers(true, false, false, false, false));
        check("single kid", solution.kidsWithCandies(new int[] {12}, 10), answers(true));
        check("ties for greatest", solution.kidsWithCandies(new int[] {5, 5, 2}, 0), answers(true, true, false));
    }
}

/*
 * Brute Force:
 * I give the extra candies to each kid in turn, then compare that total against
 * every other kid's current candy count.
 *
 * Time Complexity: O(n^2), because every kid may be compared with every kid.
 * Space Complexity: O(n), because the returned answer stores one value per kid.
 *
 * Optimal Interview Solution:
 * I find the current greatest candy count once, then each kid only needs to be
 * checked against that maximum after receiving the extra candies.
 *
 * Time Complexity: O(n), because the candies are scanned twice.
 * Space Complexity: O(n), because the returned answer stores one value per kid.
 */
