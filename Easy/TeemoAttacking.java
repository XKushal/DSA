import java.util.HashSet;
import java.util.Set;

class TeemoAttacking {
    public int bruteForceFindPoisonedDuration(int[] timeSeries, int duration) {
        Set<Integer> poisonedTimes = new HashSet<>();

        for (int attackTime : timeSeries) {
            for (int time = attackTime; time < attackTime + duration; time++) {
                poisonedTimes.add(time);
            }
        }

        return poisonedTimes.size();
    }

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0 || duration == 0) {
            return 0;
        }

        int totalDuration = 0;

        for (int i = 0; i < timeSeries.length - 1; i++) {
            totalDuration += Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
        }

        return totalDuration + duration;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        TeemoAttacking solution = new TeemoAttacking();

        check("brute force handles overlapping attacks", solution.bruteForceFindPoisonedDuration(new int[] {1, 2}, 2), 3);
        check("brute force handles separate attacks", solution.bruteForceFindPoisonedDuration(new int[] {1, 4}, 2), 4);
        check("brute force handles one attack", solution.bruteForceFindPoisonedDuration(new int[] {5}, 3), 3);

        check("handles overlapping attacks", solution.findPoisonedDuration(new int[] {1, 2}, 2), 3);
        check("handles separate attacks", solution.findPoisonedDuration(new int[] {1, 4}, 2), 4);
        check("handles chained overlaps", solution.findPoisonedDuration(new int[] {1, 2, 3, 7}, 3), 8);
        check("handles one attack", solution.findPoisonedDuration(new int[] {5}, 3), 3);
        check("handles no attacks", solution.findPoisonedDuration(new int[] {}, 3), 0);
    }
}

/*
 * Brute Force:
 * I mark every second covered by every poison interval in a set, letting the
 * set remove overlaps, then count the marked seconds.
 *
 * Time Complexity: O(n * duration), because each attack can mark duration
 * seconds.
 * Space Complexity: O(n * duration), because the set can store every poisoned
 * second.
 *
 * Optimal Interview Solution:
 * I add only the new poison time contributed by each attack, using the gap to
 * the next attack to trim overlapping intervals, then add the final full
 * duration.
 *
 * Time Complexity: O(n), because each attack time is processed once.
 * Space Complexity: O(1), because only the running total is stored.
 */
