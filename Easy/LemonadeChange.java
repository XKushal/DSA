import java.util.ArrayList;
import java.util.List;

class LemonadeChange {
    public boolean bruteForceLemonadeChange(int[] bills) {
        List<Integer> cashDrawer = new ArrayList<>();

        for (int bill : bills) {
            if (!giveChangeBySearching(cashDrawer, bill - 5)) {
                return false;
            }

            cashDrawer.add(bill);
        }

        return true;
    }

    public boolean lemonadeChange(int[] bills) {
        int fiveDollarBills = 0;
        int tenDollarBills = 0;

        for (int bill : bills) {
            if (bill == 5) {
                fiveDollarBills++;
            } else if (bill == 10) {
                if (fiveDollarBills == 0) {
                    return false;
                }

                fiveDollarBills--;
                tenDollarBills++;
            } else {
                if (tenDollarBills > 0 && fiveDollarBills > 0) {
                    tenDollarBills--;
                    fiveDollarBills--;
                } else if (fiveDollarBills >= 3) {
                    fiveDollarBills -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean giveChangeBySearching(List<Integer> cashDrawer, int change) {
        if (change == 0) {
            return true;
        }

        if (change == 5) {
            return removeBills(cashDrawer, 5, 1);
        }

        if (change == 15) {
            if (countBills(cashDrawer, 10) >= 1 && countBills(cashDrawer, 5) >= 1) {
                removeBills(cashDrawer, 10, 1);
                removeBills(cashDrawer, 5, 1);
                return true;
            }

            return removeBills(cashDrawer, 5, 3);
        }

        return false;
    }

    private int countBills(List<Integer> cashDrawer, int denomination) {
        int count = 0;

        for (int bill : cashDrawer) {
            if (bill == denomination) {
                count++;
            }
        }

        return count;
    }

    private boolean removeBills(List<Integer> cashDrawer, int denomination, int amount) {
        if (countBills(cashDrawer, denomination) < amount) {
            return false;
        }

        int removed = 0;

        for (int index = cashDrawer.size() - 1; index >= 0 && removed < amount; index--) {
            if (cashDrawer.get(index) == denomination) {
                cashDrawer.remove(index);
                removed++;
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LemonadeChange solution = new LemonadeChange();

        check("brute force handles exact five dollar payments",
                solution.bruteForceLemonadeChange(new int[] {5, 5, 5}),
                true);
        check("brute force rejects missing change",
                solution.bruteForceLemonadeChange(new int[] {10, 10}),
                false);

        check("handles exact five dollar payments", solution.lemonadeChange(new int[] {5, 5, 5}), true);
        check("gives change with a ten and a five",
                solution.lemonadeChange(new int[] {5, 5, 5, 10, 20}),
                true);
        check("rejects when the first customer needs change", solution.lemonadeChange(new int[] {10}), false);
        check("rejects when greedy change runs out",
                solution.lemonadeChange(new int[] {5, 5, 10, 10, 20}),
                false);
    }
}

/*
 * Brute Force:
 * I keep every collected bill in a list, scan the drawer to count available
 * bills, and remove the bills needed for each customer's change.
 *
 * Time Complexity: O(n^2), where n is the number of customers, because each
 * change operation can scan the current drawer.
 * Space Complexity: O(n), because the drawer can hold one bill per customer.
 *
 * Optimal Interview Solution:
 * I only track how many five-dollar and ten-dollar bills are available. For a
 * twenty-dollar bill, I prefer giving one ten and one five, saving three fives
 * for cases where no ten-dollar bill is available.
 *
 * Time Complexity: O(n), where n is the number of customers.
 * Space Complexity: O(1).
 */
