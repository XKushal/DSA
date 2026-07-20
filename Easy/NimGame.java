class NimGame {
    public boolean bruteForceCanWinNim(int n) {
        boolean[] canWin = new boolean[n + 1];

        for (int stones = 1; stones <= n; stones++) {
            for (int take = 1; take <= 3 && take <= stones; take++) {
                if (!canWin[stones - take]) {
                    canWin[stones] = true;
                    break;
                }
            }
        }

        return canWin[n];
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NimGame solution = new NimGame();

        check("brute force wins with one stone", solution.bruteForceCanWinNim(1), true);
        check("brute force loses with four stones", solution.bruteForceCanWinNim(4), false);

        check("wins with one stone", solution.canWinNim(1), true);
        check("wins with two stones", solution.canWinNim(2), true);
        check("wins with three stones", solution.canWinNim(3), true);
        check("loses with four stones", solution.canWinNim(4), false);
        check("wins after a losing count", solution.canWinNim(5), true);
        check("loses on another multiple of four", solution.canWinNim(8), false);
    }
}

/*
 * Brute Force:
 * I build the win or lose result for every pile size up to n. A position is
 * winning if taking one, two, or three stones can leave the opponent with a
 * losing position.
 *
 * Time Complexity: O(n), because each pile size checks at most three moves.
 * Space Complexity: O(n), because the win or lose state is stored for each
 * pile size up to n.
 *
 * Optimal Interview Solution:
 * I use the fact that every multiple of four is losing. Any other pile size
 * can be reduced to a multiple of four on the first move.
 *
 * Time Complexity: O(1), because only one modulo operation is needed.
 * Space Complexity: O(1), because no extra data structures are used.
 */
