class NumberOfStudentsDoingHomeworkAtAGivenTime {
    public int bruteForceBusyStudent(int[] startTime, int[] endTime, int queryTime) {
        boolean[] activeMinutes = new boolean[1001];

        for (int i = 0; i < startTime.length; i++) {
            for (int minute = startTime[i]; minute <= endTime[i]; minute++) {
                activeMinutes[minute] = true;
            }
        }

        int count = 0;

        for (int i = 0; i < startTime.length; i++) {
            if (activeMinutes[queryTime] && startTime[i] <= queryTime && queryTime <= endTime[i]) {
                count++;
            }
        }

        return count;
    }

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;

        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                count++;
            }
        }

        return count;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NumberOfStudentsDoingHomeworkAtAGivenTime solution = new NumberOfStudentsDoingHomeworkAtAGivenTime();

        check("brute force sample one", solution.bruteForceBusyStudent(
                new int[] {1, 2, 3}, new int[] {3, 2, 7}, 4), 1);
        check("brute force sample two", solution.bruteForceBusyStudent(
                new int[] {4}, new int[] {4}, 4), 1);

        check("sample one", solution.busyStudent(new int[] {1, 2, 3}, new int[] {3, 2, 7}, 4), 1);
        check("sample two", solution.busyStudent(new int[] {4}, new int[] {4}, 4), 1);
        check("before every interval", solution.busyStudent(new int[] {5, 6}, new int[] {7, 8}, 4), 0);
        check("shared query time", solution.busyStudent(new int[] {1, 1, 1}, new int[] {1, 2, 3}, 1), 3);
        check("after one interval", solution.busyStudent(new int[] {1, 5, 10}, new int[] {2, 8, 10}, 10), 1);
    }
}

/*
 * Brute Force:
 * I mark every homework minute that appears in any interval, then count the
 * students whose own interval covers the query time.
 *
 * Time Complexity: O(n * t), where n is the number of students and t is the
 * largest possible homework interval length.
 * Space Complexity: O(t), because the active minute table is stored.
 *
 * Optimal Interview Solution:
 * I scan each student's interval once and count the intervals containing the
 * query time.
 *
 * Time Complexity: O(n), where n is the number of students.
 * Space Complexity: O(1), because only the running count is stored.
 */
