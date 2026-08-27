class FinalValueOfVariableAfterPerformingOperations {
    public int bruteForceFinalValueAfterOperations(String[] operations) {
        int value = 0;

        for (String operation : operations) {
            if (operation.equals("++X") || operation.equals("X++")) {
                value++;
            } else {
                value--;
            }
        }

        return value;
    }

    public int finalValueAfterOperations(String[] operations) {
        int value = 0;

        for (String operation : operations) {
            value += operation.charAt(1) == '+' ? 1 : -1;
        }

        return value;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static String[] operations(String... values) {
        return values;
    }

    public static void main(String[] args) {
        FinalValueOfVariableAfterPerformingOperations solution =
            new FinalValueOfVariableAfterPerformingOperations();

        check("brute force sample", solution.bruteForceFinalValueAfterOperations(operations("--X", "X++", "X++")),
            1);
        check("brute force balanced", solution.bruteForceFinalValueAfterOperations(operations("++X", "X--")), 0);

        check("sample", solution.finalValueAfterOperations(operations("--X", "X++", "X++")), 1);
        check("all increments", solution.finalValueAfterOperations(operations("++X", "++X", "X++")), 3);
        check("mixed", solution.finalValueAfterOperations(operations("X++", "++X", "--X", "X--")), 0);
        check("all decrements", solution.finalValueAfterOperations(operations("--X", "X--")), -2);
    }
}

/*
 * Brute Force:
 * I simulate the variable changes by checking the full operation string and
 * applying the matching increment or decrement.
 *
 * Time Complexity: O(n), because each operation is inspected once.
 * Space Complexity: O(1), because only the current value is stored.
 *
 * Optimal Interview Solution:
 * I use the middle character of each operation. A plus sign means increment,
 * and a minus sign means decrement.
 *
 * Time Complexity: O(n), because each operation contributes one constant-time
 * update.
 * Space Complexity: O(1), because only the running value is stored.
 */
