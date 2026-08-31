class SplitAStringInBalancedStrings {
    public int bruteForceBalancedStringSplit(String s) {
        int balancedCount = 0;
        int start = 0;

        while (start < s.length()) {
            int rCount = 0;
            int lCount = 0;

            for (int end = start; end < s.length(); end++) {
                if (s.charAt(end) == 'R') {
                    rCount++;
                } else {
                    lCount++;
                }

                if (rCount == lCount) {
                    balancedCount++;
                    start = end + 1;
                    break;
                }
            }
        }

        return balancedCount;
    }

    public int balancedStringSplit(String s) {
        int balance = 0;
        int balancedCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                balance++;
            } else {
                balance--;
            }

            if (balance == 0) {
                balancedCount++;
            }
        }

        return balancedCount;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SplitAStringInBalancedStrings solution = new SplitAStringInBalancedStrings();

        check("brute force sample", solution.bruteForceBalancedStringSplit("RLRRLLRLRL"), 4);
        check("brute force nested", solution.bruteForceBalancedStringSplit("RLLLLRRRLR"), 3);

        check("sample", solution.balancedStringSplit("RLRRLLRLRL"), 4);
        check("nested", solution.balancedStringSplit("RLLLLRRRLR"), 3);
        check("single segment", solution.balancedStringSplit("LLLLRRRR"), 1);
    }
}

/*
 * Brute Force:
 * I restart a local count for each balanced piece and scan until the current
 * piece has the same number of L and R characters.
 *
 * Time Complexity: O(n), because each character is visited once across all
 * pieces.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I track one running balance, increasing for R and decreasing for L. Every
 * time the balance returns to zero, the current substring is balanced.
 *
 * Time Complexity: O(n), because each character is visited once.
 * Space Complexity: O(1), because only the running balance and answer are
 * stored.
 */
