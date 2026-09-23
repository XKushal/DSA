import java.util.Arrays;

class MaximumUnitsOnATruck {
    public int bruteForceMaximumUnits(int[][] boxTypes, int truckSize) {
        int[][] remaining = copy(boxTypes);
        int units = 0;

        while (truckSize > 0) {
            int bestIndex = -1;

            for (int i = 0; i < remaining.length; i++) {
                if (remaining[i][0] > 0
                        && (bestIndex == -1 || remaining[i][1] > remaining[bestIndex][1])) {
                    bestIndex = i;
                }
            }

            if (bestIndex == -1) {
                break;
            }

            remaining[bestIndex][0]--;
            units += remaining[bestIndex][1];
            truckSize--;
        }

        return units;
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[][] sorted = copy(boxTypes);
        Arrays.sort(sorted, (a, b) -> b[1] - a[1]);

        int units = 0;

        for (int[] boxType : sorted) {
            int boxes = Math.min(truckSize, boxType[0]);
            units += boxes * boxType[1];
            truckSize -= boxes;

            if (truckSize == 0) {
                break;
            }
        }

        return units;
    }

    private int[][] copy(int[][] boxTypes) {
        int[][] result = new int[boxTypes.length][2];

        for (int i = 0; i < boxTypes.length; i++) {
            result[i][0] = boxTypes[i][0];
            result[i][1] = boxTypes[i][1];
        }

        return result;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumUnitsOnATruck solution = new MaximumUnitsOnATruck();

        check("brute force sample one", solution.bruteForceMaximumUnits(
                new int[][] {{1, 3}, {2, 2}, {3, 1}}, 4), 8);
        check("brute force sample two", solution.bruteForceMaximumUnits(
                new int[][] {{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10), 91);

        check("sample one", solution.maximumUnits(new int[][] {{1, 3}, {2, 2}, {3, 1}}, 4), 8);
        check("sample two", solution.maximumUnits(
                new int[][] {{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10), 91);
        check("truck smaller than best type", solution.maximumUnits(new int[][] {{3, 9}, {2, 1}}, 2), 18);
        check("truck larger than all boxes", solution.maximumUnits(new int[][] {{1, 4}, {2, 6}}, 5), 16);
    }
}

/*
 * Brute Force:
 * I repeatedly choose one box from the remaining type with the most units per
 * box until the truck is full or no boxes remain.
 *
 * Time Complexity: O(t * n), where t is the truck size and n is the number of
 * box types.
 * Space Complexity: O(n), because the box counts are copied before decrementing.
 *
 * Optimal Interview Solution:
 * I sort box types by units per box in descending order, then load as many as
 * possible from each most valuable type.
 *
 * Time Complexity: O(n log n), because sorting dominates the loading pass.
 * Space Complexity: O(n), because the box types are copied before sorting.
 */
