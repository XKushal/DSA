import java.util.ArrayList;
import java.util.List;

class BaseballGame {
    public int bruteForceCalPoints(String[] operations) {
        List<Integer> scores = new ArrayList<>();

        for (String operation : operations) {
            if (operation.equals("C")) {
                scores.remove(scores.size() - 1);
            } else if (operation.equals("D")) {
                scores.add(scores.get(scores.size() - 1) * 2);
            } else if (operation.equals("+")) {
                scores.add(scores.get(scores.size() - 1) + scores.get(scores.size() - 2));
            } else {
                scores.add(Integer.parseInt(operation));
            }
        }

        int total = 0;
        for (int score : scores) {
            total += score;
        }

        return total;
    }

    public int calPoints(String[] operations) {
        int[] scores = new int[operations.length];
        int size = 0;
        int total = 0;

        for (String operation : operations) {
            if (operation.equals("C")) {
                size--;
                total -= scores[size];
            } else if (operation.equals("D")) {
                scores[size] = scores[size - 1] * 2;
                total += scores[size];
                size++;
            } else if (operation.equals("+")) {
                scores[size] = scores[size - 1] + scores[size - 2];
                total += scores[size];
                size++;
            } else {
                scores[size] = Integer.parseInt(operation);
                total += scores[size];
                size++;
            }
        }

        return total;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        BaseballGame solution = new BaseballGame();

        check("brute force handles cancel and double",
                solution.bruteForceCalPoints(new String[] {"5", "2", "C", "D", "+"}), 30);
        check("brute force handles negative score",
                solution.bruteForceCalPoints(new String[] {"5", "-2", "4", "C", "D", "9", "+", "+"}), 27);

        check("handles cancel and double", solution.calPoints(new String[] {"5", "2", "C", "D", "+"}), 30);
        check("handles negative score", solution.calPoints(new String[] {"5", "-2", "4", "C", "D", "9", "+", "+"}),
                27);
        check("handles a single score", solution.calPoints(new String[] {"1"}), 1);
    }
}

/*
 * Brute Force:
 * I store every valid round score in a list, apply each operation to that list,
 * then make a final pass to sum the remaining scores.
 *
 * Time Complexity: O(n), where n is the number of operations.
 * Space Complexity: O(n), because the list can store one score per operation.
 *
 * Optimal Interview Solution:
 * I use an array as a stack for valid scores and update the running total as
 * each operation is processed, including subtracting canceled scores immediately.
 *
 * Time Complexity: O(n), where n is the number of operations.
 * Space Complexity: O(n), because the stack can store one score per operation.
 */
