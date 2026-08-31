class FindTheHighestAltitude {
    public int bruteForceLargestAltitude(int[] gain) {
        int[] altitudes = new int[gain.length + 1];

        for (int i = 0; i < gain.length; i++) {
            altitudes[i + 1] = altitudes[i] + gain[i];
        }

        int highest = altitudes[0];

        for (int altitude : altitudes) {
            highest = Math.max(highest, altitude);
        }

        return highest;
    }

    public int largestAltitude(int[] gain) {
        int currentAltitude = 0;
        int highest = 0;

        for (int currentGain : gain) {
            currentAltitude += currentGain;
            highest = Math.max(highest, currentAltitude);
        }

        return highest;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindTheHighestAltitude solution = new FindTheHighestAltitude();

        check("brute force sample", solution.bruteForceLargestAltitude(
            new int[] {-5, 1, 5, 0, -7}
        ), 1);
        check("brute force all climbs", solution.bruteForceLargestAltitude(
            new int[] {4, 2, 1}
        ), 7);

        check("sample", solution.largestAltitude(new int[] {-5, 1, 5, 0, -7}), 1);
        check("starts highest", solution.largestAltitude(new int[] {-4, -3, -2, -1, 4, 3, 2}), 0);
        check("all climbs", solution.largestAltitude(new int[] {4, 2, 1}), 7);
    }
}

/*
 * Brute Force:
 * I build the full altitude history from the gain array, then scan that history
 * to find the highest altitude reached.
 *
 * Time Complexity: O(n), because the gains are scanned once to build altitudes
 * and the altitude array is scanned once to find the maximum.
 * Space Complexity: O(n), because every altitude is stored.
 *
 * Optimal Interview Solution:
 * I keep only the current altitude and update the best altitude after each
 * gain.
 *
 * Time Complexity: O(n), because each gain is processed once.
 * Space Complexity: O(1), because only the current and best altitudes are
 * stored.
 */
