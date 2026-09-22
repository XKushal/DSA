class CountLargestGroup {
    public int bruteForceCountLargestGroup(int n) {
        int largestSize = 0;
        int largestGroupCount = 0;

        for (int i = 1; i <= n; i++) {
            int currentSum = digitSum(i);
            int currentSize = 0;

            for (int j = 1; j <= n; j++) {
                if (digitSum(j) == currentSum) {
                    currentSize++;
                }
            }

            if (currentSize > largestSize) {
                largestSize = currentSize;
                largestGroupCount = 1;
            } else if (currentSize == largestSize) {
                largestGroupCount++;
            }
        }

        return largestGroupCount / largestSize;
    }

    public int countLargestGroup(int n) {
        int[] groups = new int[46];
        int largestSize = 0;
        int largestGroupCount = 0;

        for (int i = 1; i <= n; i++) {
            int sum = digitSum(i);
            groups[sum]++;

            if (groups[sum] > largestSize) {
                largestSize = groups[sum];
                largestGroupCount = 1;
            } else if (groups[sum] == largestSize) {
                largestGroupCount++;
            }
        }

        return largestGroupCount;
    }

    private int digitSum(int number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountLargestGroup solution = new CountLargestGroup();

        check("brute force sample one", solution.bruteForceCountLargestGroup(13), 4);
        check("brute force sample two", solution.bruteForceCountLargestGroup(2), 2);

        check("sample one", solution.countLargestGroup(13), 4);
        check("sample two", solution.countLargestGroup(2), 2);
        check("six largest groups", solution.countLargestGroup(15), 6);
        check("single number", solution.countLargestGroup(1), 1);
        check("one hundred", solution.countLargestGroup(100), 1);
    }
}

/*
 * Brute Force:
 * I calculate each number's digit sum, then compare it with every number from
 * 1 through n to measure that group size.
 *
 * Time Complexity: O(n^2 * d), where d is the number of digits in n.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I count how many numbers belong to each digit-sum group in one pass and keep
 * track of the largest group size as it changes.
 *
 * Time Complexity: O(n * d), where d is the number of digits in n.
 * Space Complexity: O(1), because the digit-sum table has a fixed maximum size
 * for the given constraints.
 */
