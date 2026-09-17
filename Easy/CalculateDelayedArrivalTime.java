class CalculateDelayedArrivalTime {
    public int bruteForceFindDelayedArrivalTime(int arrivalTime, int delayedTime) {
        int hour = arrivalTime;

        for (int i = 0; i < delayedTime; i++) {
            hour++;

            if (hour == 24) {
                hour = 0;
            }
        }

        return hour;
    }

    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CalculateDelayedArrivalTime solution = new CalculateDelayedArrivalTime();

        check("brute force sample one", solution.bruteForceFindDelayedArrivalTime(15, 5), 20);
        check("brute force sample two", solution.bruteForceFindDelayedArrivalTime(13, 11), 0);

        check("sample one", solution.findDelayedArrivalTime(15, 5), 20);
        check("sample two", solution.findDelayedArrivalTime(13, 11), 0);
        check("wrap around", solution.findDelayedArrivalTime(23, 2), 1);
        check("same hour", solution.findDelayedArrivalTime(8, 24), 8);
        check("midnight start", solution.findDelayedArrivalTime(0, 10), 10);
    }
}

/*
 * Brute Force:
 * I advance the arrival hour one hour at a time and wrap back to 0 after 23.
 *
 * Time Complexity: O(d), where d is the delayed time.
 * Space Complexity: O(1), because only the current hour is stored.
 *
 * Optimal Interview Solution:
 * I add the delay to the arrival hour and use modulo 24 to handle wrapping
 * around the clock.
 *
 * Time Complexity: O(1), because the calculation uses one arithmetic step.
 * Space Complexity: O(1), because no extra data structures are used.
 */
