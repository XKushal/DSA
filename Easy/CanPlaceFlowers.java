class CanPlaceFlowers {
    public boolean bruteForceCanPlaceFlowers(int[] flowerbed, int n) {
        if (n <= 0) {
            return true;
        }

        return canPlaceByTrying(flowerbed, n, 0);
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int remaining = n;

        for (int index = 0; index < flowerbed.length && remaining > 0; index++) {
            if (canPlantAt(flowerbed, index)) {
                flowerbed[index] = 1;
                remaining--;
            }
        }

        return remaining == 0;
    }

    private boolean canPlaceByTrying(int[] flowerbed, int remaining, int index) {
        if (remaining == 0) {
            return true;
        }

        if (index == flowerbed.length) {
            return false;
        }

        if (canPlantAt(flowerbed, index)) {
            flowerbed[index] = 1;
            if (canPlaceByTrying(flowerbed, remaining - 1, index + 1)) {
                return true;
            }
            flowerbed[index] = 0;
        }

        return canPlaceByTrying(flowerbed, remaining, index + 1);
    }

    private boolean canPlantAt(int[] flowerbed, int index) {
        boolean leftEmpty = index == 0 || flowerbed[index - 1] == 0;
        boolean rightEmpty = index == flowerbed.length - 1 || flowerbed[index + 1] == 0;

        return flowerbed[index] == 0 && leftEmpty && rightEmpty;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CanPlaceFlowers solution = new CanPlaceFlowers();

        check("brute force places one flower",
                solution.bruteForceCanPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1), true);

        check("places one flower", solution.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1), true);
        check("rejects two flowers in tight bed", solution.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 2), false);
        check("handles empty edges", solution.canPlaceFlowers(new int[] {0, 0, 1, 0, 0}, 2), true);
        check("handles zero requested flowers", solution.canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 0), true);
        check("uses alternating empty spaces", solution.canPlaceFlowers(new int[] {0, 0, 0, 0, 0}, 3), true);
    }
}

/*
 * Brute Force:
 * I try each plot as either planted or skipped, backtracking after every
 * attempted placement until enough new flowers are placed or every plot is
 * exhausted.
 *
 * Time Complexity: O(2^n), where n is the number of plots, because each empty
 * plot can branch into a plant-or-skip choice.
 * Space Complexity: O(n), because the recursive search can use one stack frame
 * per plot.
 *
 * Optimal Interview Solution:
 * I scan the flowerbed once from left to right. Whenever a plot and both
 * neighbors are empty, I plant there immediately because this earliest valid
 * choice cannot reduce the number of later placements.
 *
 * Time Complexity: O(n), because each plot is inspected at most once.
 * Space Complexity: O(1), because the flowerbed is updated in place.
 */
