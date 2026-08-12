class AvailableCapturesForRook {
    public int bruteForceNumRookCaptures(char[][] board) {
        int rookRow = -1;
        int rookCol = -1;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'R') {
                    rookRow = row;
                    rookCol = col;
                }
            }
        }

        int captures = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'p' && canCapture(board, rookRow, rookCol, row, col)) {
                    captures++;
                }
            }
        }

        return captures;
    }

    private boolean canCapture(char[][] board, int rookRow, int rookCol, int pawnRow, int pawnCol) {
        if (rookRow != pawnRow && rookCol != pawnCol) {
            return false;
        }

        int rowStep = Integer.compare(pawnRow, rookRow);
        int colStep = Integer.compare(pawnCol, rookCol);
        int row = rookRow + rowStep;
        int col = rookCol + colStep;

        while (row != pawnRow || col != pawnCol) {
            if (board[row][col] != '.') {
                return false;
            }

            row += rowStep;
            col += colStep;
        }

        return true;
    }

    public int numRookCaptures(char[][] board) {
        int rookRow = -1;
        int rookCol = -1;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 'R') {
                    rookRow = row;
                    rookCol = col;
                }
            }
        }

        int captures = 0;
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        for (int[] direction : directions) {
            int row = rookRow + direction[0];
            int col = rookCol + direction[1];

            while (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
                if (board[row][col] == 'B') {
                    break;
                }

                if (board[row][col] == 'p') {
                    captures++;
                    break;
                }

                row += direction[0];
                col += direction[1];
            }
        }

        return captures;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static char[][] board(String... rows) {
        char[][] board = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            board[i] = rows[i].toCharArray();
        }

        return board;
    }

    public static void main(String[] args) {
        AvailableCapturesForRook solution = new AvailableCapturesForRook();

        check("brute force captures in three directions",
                solution.bruteForceNumRookCaptures(board(
                        "........",
                        "...p....",
                        "...R...p",
                        "........",
                        "........",
                        "........",
                        "........",
                        "...p....")),
                3);
        check("brute force respects blocking bishops",
                solution.bruteForceNumRookCaptures(board(
                        "........",
                        "........",
                        "........",
                        "...R.Bp.",
                        "........",
                        "........",
                        "........",
                        "........")),
                0);

        check("captures in three directions",
                solution.numRookCaptures(board(
                        "........",
                        "...p....",
                        "...R...p",
                        "........",
                        "........",
                        "........",
                        "........",
                        "...p....")),
                3);
        check("respects blocking bishops",
                solution.numRookCaptures(board(
                        "........",
                        "........",
                        "........",
                        "...R.Bp.",
                        "........",
                        "........",
                        "........",
                        "........")),
                0);
        check("stops at the first pawn in each direction",
                solution.numRookCaptures(board(
                        "........",
                        "...p....",
                        "...p....",
                        "p..R..pp",
                        "........",
                        "...B....",
                        "...p....",
                        "........")),
                3);
        check("returns zero with no reachable pawns",
                solution.numRookCaptures(board(
                        "........",
                        "........",
                        "...B....",
                        "..BRB...",
                        "...B....",
                        "........",
                        "........",
                        "........")),
                0);
    }
}

/*
 * Brute Force:
 * I find the rook, inspect every pawn on the board, and count that pawn only
 * when it shares a row or column with no piece between it and the rook.
 *
 * Time Complexity: O(n^3), because each square can trigger a row or column
 * scan on an n by n board.
 * Space Complexity: O(1), because only coordinates and counters are stored.
 *
 * Optimal Interview Solution:
 * I find the rook once, then walk in the four rook directions until the path
 * leaves the board, hits a bishop, or captures the first pawn in that line.
 *
 * Time Complexity: O(n^2), because finding the rook scans the board and each
 * direction adds at most one row or column scan.
 * Space Complexity: O(1), because the direction list has constant size.
 */
