class NumberOfEmployeesWhoMetTheTarget {
    public int bruteForceNumberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int employees = 0;

        for (int i = 0; i < hours.length; i++) {
            if (hours[i] >= target) {
                employees++;
            }
        }

        return employees;
    }

    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int employees = 0;

        for (int workedHours : hours) {
            if (workedHours >= target) {
                employees++;
            }
        }

        return employees;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NumberOfEmployeesWhoMetTheTarget solution = new NumberOfEmployeesWhoMetTheTarget();

        check("brute force sample", solution.bruteForceNumberOfEmployeesWhoMetTarget(
                new int[] {0, 1, 2, 3, 4}, 2), 3);
        check("brute force none", solution.bruteForceNumberOfEmployeesWhoMetTarget(
                new int[] {5, 1, 4, 2, 2}, 6), 0);

        check("sample", solution.numberOfEmployeesWhoMetTarget(new int[] {0, 1, 2, 3, 4}, 2), 3);
        check("all meet target", solution.numberOfEmployeesWhoMetTarget(new int[] {5, 5, 5}, 5), 3);
        check("none meet target", solution.numberOfEmployeesWhoMetTarget(new int[] {1, 2, 3}, 4), 0);
        check("mixed hours", solution.numberOfEmployeesWhoMetTarget(new int[] {8, 6, 7, 5, 9}, 7), 3);
    }
}

/*
 * Brute Force:
 * I inspect each employee's hours by index and count every value that is at
 * least the target.
 *
 * Time Complexity: O(n), where n is the number of employees.
 * Space Complexity: O(1), because only the count is stored.
 *
 * Optimal Interview Solution:
 * I use the same direct scan with an enhanced for loop, counting every employee
 * whose worked hours meet or exceed the target.
 *
 * Time Complexity: O(n), where n is the number of employees.
 * Space Complexity: O(1), because only the count is stored.
 */
