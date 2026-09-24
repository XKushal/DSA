import java.util.Arrays;

class AverageSalaryExcludingTheMinimumAndMaximumSalary {
    public double bruteForceAverage(int[] salary) {
        int[] sorted = Arrays.copyOf(salary, salary.length);
        Arrays.sort(sorted);

        int total = 0;

        for (int i = 1; i < sorted.length - 1; i++) {
            total += sorted[i];
        }

        return (double) total / (salary.length - 2);
    }

    public double average(int[] salary) {
        int minimum = salary[0];
        int maximum = salary[0];
        int total = 0;

        for (int amount : salary) {
            minimum = Math.min(minimum, amount);
            maximum = Math.max(maximum, amount);
            total += amount;
        }

        return (double) (total - minimum - maximum) / (salary.length - 2);
    }

    private static void check(String name, double actual, double expected) {
        if (Math.abs(actual - expected) > 0.00001) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        AverageSalaryExcludingTheMinimumAndMaximumSalary solution =
                new AverageSalaryExcludingTheMinimumAndMaximumSalary();

        check("brute force sample one", solution.bruteForceAverage(new int[] {4000, 3000, 1000, 2000}),
                2500.0);
        check("brute force sample two", solution.bruteForceAverage(new int[] {1000, 2000, 3000}),
                2000.0);

        check("sample one", solution.average(new int[] {4000, 3000, 1000, 2000}), 2500.0);
        check("sample two", solution.average(new int[] {1000, 2000, 3000}), 2000.0);
        check("multiple middle salaries", solution.average(new int[] {6000, 5000, 4000, 3000, 2000, 1000}),
                3500.0);
        check("unsorted with duplicate middle", solution.average(new int[] {8000, 9000, 2000, 3000, 6000, 1000}),
                4750.0);
    }
}

/*
 * Brute Force:
 * I sort a copy of the salaries, skip the first and last values, then average
 * the remaining salaries.
 *
 * Time Complexity: O(n log n), because sorting dominates the work.
 * Space Complexity: O(n), because the salaries are copied before sorting.
 *
 * Optimal Interview Solution:
 * I scan once to collect the total salary, minimum salary, and maximum salary,
 * then remove the extremes before dividing by the remaining count.
 *
 * Time Complexity: O(n), where n is the number of salaries.
 * Space Complexity: O(1), because only running totals are stored.
 */
