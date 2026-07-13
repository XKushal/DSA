import java.util.Arrays;

class RelativeRanks {
    public String[] bruteForceFindRelativeRanks(int[] score) {
        String[] ranks = new String[score.length];

        for (int index = 0; index < score.length; index++) {
            int rank = 1;

            for (int other = 0; other < score.length; other++) {
                if (score[other] > score[index]) {
                    rank++;
                }
            }

            ranks[index] = formatRank(rank);
        }

        return ranks;
    }

    public String[] findRelativeRanks(int[] score) {
        int[][] athletes = new int[score.length][2];

        for (int index = 0; index < score.length; index++) {
            athletes[index][0] = score[index];
            athletes[index][1] = index;
        }

        Arrays.sort(athletes, (first, second) -> Integer.compare(second[0], first[0]));

        String[] ranks = new String[score.length];

        for (int rankIndex = 0; rankIndex < athletes.length; rankIndex++) {
            int originalIndex = athletes[rankIndex][1];
            ranks[originalIndex] = formatRank(rankIndex + 1);
        }

        return ranks;
    }

    private static String formatRank(int rank) {
        if (rank == 1) {
            return "Gold Medal";
        }

        if (rank == 2) {
            return "Silver Medal";
        }

        if (rank == 3) {
            return "Bronze Medal";
        }

        return String.valueOf(rank);
    }

    private static void check(String name, String[] actual, String[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        RelativeRanks solution = new RelativeRanks();

        check("brute force ranks five athletes",
                solution.bruteForceFindRelativeRanks(new int[] {5, 4, 3, 2, 1}),
                new String[] {"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"});

        check("ranks five athletes",
                solution.findRelativeRanks(new int[] {5, 4, 3, 2, 1}),
                new String[] {"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"});
        check("preserves original positions",
                solution.findRelativeRanks(new int[] {10, 3, 8, 9, 4}),
                new String[] {"Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"});
        check("handles one athlete",
                solution.findRelativeRanks(new int[] {99}),
                new String[] {"Gold Medal"});
    }
}

/*
 * Brute Force:
 * I compute each athlete's rank by counting how many scores are greater, then
 * convert that rank into the required medal or number.
 *
 * Time Complexity: O(n^2), where n is the number of athletes, because each
 * score is compared with every other score.
 * Space Complexity: O(n), because the output array stores one rank per athlete.
 *
 * Optimal Interview Solution:
 * I pair each score with its original index, sort the pairs from highest score
 * to lowest score, and then write each formatted rank back to the original
 * position.
 *
 * Time Complexity: O(n log n), because sorting the athletes dominates the work.
 * Space Complexity: O(n), because the paired score/index array and result array
 * store n entries.
 */
