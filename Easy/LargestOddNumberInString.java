class LargestOddNumberInString {
    public String bruteForceLargestOddNumber(String num) {
        String best = "";

        for (int end = 0; end < num.length(); end++) {
            String candidate = num.substring(0, end + 1);

            if (isOdd(candidate)) {
                best = candidate;
            }
        }

        return best;
    }

    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }

        return "";
    }

    private boolean isOdd(String value) {
        return (value.charAt(value.length() - 1) - '0') % 2 == 1;
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LargestOddNumberInString solution = new LargestOddNumberInString();

        check("brute force sample one", solution.bruteForceLargestOddNumber("52"), "5");
        check("brute force sample two", solution.bruteForceLargestOddNumber("4206"), "");

        check("sample one", solution.largestOddNumber("52"), "5");
        check("sample two", solution.largestOddNumber("4206"), "");
        check("sample three", solution.largestOddNumber("35427"), "35427");
        check("last odd prefix", solution.largestOddNumber("239537672423884969653287101"), "239537672423884969653287101");
        check("trim even suffix", solution.largestOddNumber("1010"), "101");
    }
}

/*
 * Brute Force:
 * I generate every prefix, keep only odd values, and retain the longest odd
 * prefix seen so far.
 *
 * Time Complexity: O(n^2), where n is the length of num, because each prefix
 * substring can copy up to n characters.
 * Space Complexity: O(n), because the best substring can contain all digits.
 *
 * Optimal Interview Solution:
 * The largest odd-valued substring must be a prefix ending at the rightmost odd
 * digit. Scanning from the end finds that digit and returns the prefix.
 *
 * Time Complexity: O(n), where n is the length of num.
 * Space Complexity: O(n), because the returned prefix can contain all digits.
 */
