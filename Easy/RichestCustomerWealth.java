class RichestCustomerWealth {
    public int bruteForceMaximumWealth(int[][] accounts) {
        int richestWealth = 0;

        for (int i = 0; i < accounts.length; i++) {
            boolean isRichest = true;
            int currentWealth = wealth(accounts[i]);

            for (int j = 0; j < accounts.length; j++) {
                if (currentWealth < wealth(accounts[j])) {
                    isRichest = false;
                    break;
                }
            }

            if (isRichest) {
                richestWealth = currentWealth;
                break;
            }
        }

        return richestWealth;
    }

    public int maximumWealth(int[][] accounts) {
        int richestWealth = 0;

        for (int[] customerAccounts : accounts) {
            richestWealth = Math.max(richestWealth, wealth(customerAccounts));
        }

        return richestWealth;
    }

    private int wealth(int[] customerAccounts) {
        int total = 0;
        for (int money : customerAccounts) {
            total += money;
        }
        return total;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[][] accounts(int[]... rows) {
        return rows;
    }

    private static int[] row(int... values) {
        return values;
    }

    public static void main(String[] args) {
        RichestCustomerWealth solution = new RichestCustomerWealth();

        check("brute force sample", solution.bruteForceMaximumWealth(accounts(row(1, 2, 3), row(3, 2, 1))), 6);
        check("brute force later richest",
            solution.bruteForceMaximumWealth(accounts(row(1, 5), row(7, 3), row(3, 5))), 10);

        check("sample", solution.maximumWealth(accounts(row(1, 2, 3), row(3, 2, 1))), 6);
        check("later richest", solution.maximumWealth(accounts(row(1, 5), row(7, 3), row(3, 5))), 10);
        check("single customer", solution.maximumWealth(accounts(row(2, 8, 1))), 11);
        check("tie", solution.maximumWealth(accounts(row(5, 5), row(4, 6), row(3, 3))), 10);
    }
}

/*
 * Brute Force:
 * I calculate one customer's wealth, then compare it with every other
 * customer's wealth until I find a customer no one exceeds.
 *
 * Time Complexity: O(m^2 * n), where m is the number of customers and n is the
 * number of banks per customer.
 * Space Complexity: O(1), because only counters and sums are stored.
 *
 * Optimal Interview Solution:
 * I calculate each customer's wealth once and keep the largest total seen so
 * far.
 *
 * Time Complexity: O(m * n), because every account value is read once.
 * Space Complexity: O(1), because only the current and maximum totals are
 * stored.
 */
