class DecryptStringFromAlphabetToIntegerMapping {
    public String bruteForceFreqAlphabets(String s) {
        StringBuilder decoded = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            int value;
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                value = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                i += 3;
            } else {
                value = s.charAt(i) - '0';
                i++;
            }

            decoded.append(toLetter(value));
        }

        return decoded.toString();
    }

    public String freqAlphabets(String s) {
        StringBuilder decoded = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            int value;
            if (s.charAt(i) == '#') {
                value = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                i -= 2;
            } else {
                value = s.charAt(i) - '0';
            }

            decoded.append(toLetter(value));
        }

        return decoded.reverse().toString();
    }

    private static char toLetter(int value) {
        return (char) ('a' + value - 1);
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DecryptStringFromAlphabetToIntegerMapping solution = new DecryptStringFromAlphabetToIntegerMapping();

        check("brute force mixed encodings", solution.bruteForceFreqAlphabets("10#11#12"), "jkab");
        check("brute force single digit run", solution.bruteForceFreqAlphabets("123456789"), "abcdefghi");

        check("mixed encodings", solution.freqAlphabets("10#11#12"), "jkab");
        check("single and two digit encodings", solution.freqAlphabets("1326#"), "acz");
        check("two digit only", solution.freqAlphabets("25#"), "y");
        check("single digit run", solution.freqAlphabets("123456789"), "abcdefghi");
        check("last alphabet letter", solution.freqAlphabets("26#"), "z");
        check("single letter", solution.freqAlphabets("1"), "a");
    }
}

/*
 * Brute Force:
 * I scan from left to right and check whether the current position starts a
 * two-digit code followed by '#'. Otherwise, I decode the current single digit.
 *
 * Time Complexity: O(n), because each encoded character is visited once.
 * Space Complexity: O(n), because the decoded string is built in a StringBuilder.
 *
 * Optimal Interview Solution:
 * I scan from right to left so '#' directly identifies a two-digit code using
 * the two previous digits. Single digits can be decoded immediately, and the
 * builder is reversed once at the end.
 *
 * Time Complexity: O(n), because the scan and final reverse are linear.
 * Space Complexity: O(n), because the decoded string is stored in the builder.
 */
