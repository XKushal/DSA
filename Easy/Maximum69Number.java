class Maximum69Number {
    public int bruteForceMaximum69Number(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int best = num;

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                best = Math.max(best, Integer.parseInt(new String(digits)));
                digits[i] = '6';
            }
        }

        return best;
    }

    public int maximum69Number(int num) {
        char[] digits = String.valueOf(num).toCharArray();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break;
            }
        }

        return Integer.parseInt(new String(digits));
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        Maximum69Number solution = new Maximum69Number();

        check("brute force leading change", solution.bruteForceMaximum69Number(9669), 9969);
        check("brute force no change", solution.bruteForceMaximum69Number(9999), 9999);

        check("leading change", solution.maximum69Number(9669), 9969);
        check("middle change", solution.maximum69Number(9966), 9996);
        check("single digit", solution.maximum69Number(6), 9);
        check("already maximum", solution.maximum69Number(9999), 9999);
    }
}

/*
 * Brute Force:
 * I try changing each 6 to a 9 one position at a time and keep the largest
 * number created by those choices.
 *
 * Time Complexity: O(d^2), where d is the number of digits, because each trial
 * builds and parses a d-digit number.
 * Space Complexity: O(d), because the digit array stores the number.
 *
 * Optimal Interview Solution:
 * I change the first 6 to 9 because the leftmost changed digit produces the
 * largest possible place-value gain.
 *
 * Time Complexity: O(d), where d is the number of digits.
 * Space Complexity: O(d), because the digit array stores the number.
 */
