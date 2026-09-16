class MaximumValueOfAStringInAnArray {
    public int bruteForceMaximumValue(String[] strs) {
        int maximum = 0;

        for (String str : strs) {
            boolean numeric = true;

            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    numeric = false;
                    break;
                }
            }

            int value = numeric ? Integer.parseInt(str) : str.length();
            maximum = Math.max(maximum, value);
        }

        return maximum;
    }

    public int maximumValue(String[] strs) {
        int maximum = 0;

        for (String str : strs) {
            int value = 0;
            boolean numeric = true;

            for (int i = 0; i < str.length(); i++) {
                char current = str.charAt(i);

                if (current < '0' || current > '9') {
                    numeric = false;
                    break;
                }

                value = value * 10 + current - '0';
            }

            maximum = Math.max(maximum, numeric ? value : str.length());
        }

        return maximum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumValueOfAStringInAnArray solution = new MaximumValueOfAStringInAnArray();

        check("brute force sample one", solution.bruteForceMaximumValue(
                new String[] {"alic3", "bob", "3", "4", "00000"}), 5);
        check("brute force sample two", solution.bruteForceMaximumValue(
                new String[] {"1", "01", "001", "0001"}), 1);

        check("sample one", solution.maximumValue(new String[] {"alic3", "bob", "3", "4", "00000"}), 5);
        check("sample two", solution.maximumValue(new String[] {"1", "01", "001", "0001"}), 1);
        check("numeric winner", solution.maximumValue(new String[] {"99", "leetcode", "8"}), 99);
        check("word winner", solution.maximumValue(new String[] {"7", "longword", "4"}), 8);
        check("zeros", solution.maximumValue(new String[] {"0", "000", "z"}), 1);
    }
}

/*
 * Brute Force:
 * I scan each string to decide whether it is numeric. Numeric strings are
 * parsed with Integer.parseInt; alphanumeric strings use their length.
 *
 * Time Complexity: O(n * k), where n is the number of strings and k is the
 * maximum string length.
 * Space Complexity: O(1), because only scalar values are stored.
 *
 * Optimal Interview Solution:
 * I scan every string once and build the numeric value as I go. If any
 * non-digit appears, the string's value is its length.
 *
 * Time Complexity: O(n * k), where n is the number of strings and k is the
 * maximum string length.
 * Space Complexity: O(1), because only scalar values are stored.
 */
