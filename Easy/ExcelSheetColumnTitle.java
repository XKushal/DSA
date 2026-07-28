class ExcelSheetColumnTitle {
    public String bruteForceConvertToTitle(int columnNumber) {
        String title = "";

        for (int current = 1; current <= columnNumber; current++) {
            title = nextTitle(title);
        }

        return title;
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder title = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;
            title.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return title.reverse().toString();
    }

    private String nextTitle(String title) {
        if (title.isEmpty()) {
            return "A";
        }

        StringBuilder next = new StringBuilder(title);
        int index = next.length() - 1;

        while (index >= 0 && next.charAt(index) == 'Z') {
            next.setCharAt(index, 'A');
            index--;
        }

        if (index < 0) {
            next.insert(0, 'A');
        } else {
            next.setCharAt(index, (char) (next.charAt(index) + 1));
        }

        return next.toString();
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ExcelSheetColumnTitle solution = new ExcelSheetColumnTitle();

        check("brute force converts first column", solution.bruteForceConvertToTitle(1), "A");
        check("brute force handles two letters", solution.bruteForceConvertToTitle(28), "AB");
        check("brute force carries across z", solution.bruteForceConvertToTitle(52), "AZ");

        check("converts first column", solution.convertToTitle(1), "A");
        check("converts final one-letter column", solution.convertToTitle(26), "Z");
        check("converts two-letter title", solution.convertToTitle(28), "AB");
        check("converts carry case", solution.convertToTitle(701), "ZY");
        check("converts three-letter title", solution.convertToTitle(18278), "ZZZ");
    }
}

/*
 * Brute Force:
 * I start at the first title and repeatedly advance to the next spreadsheet
 * column title until reaching the requested column number.
 *
 * Time Complexity: O(n * log n), where n is columnNumber and each title can
 * contain O(log n) characters.
 * Space Complexity: O(log n), because the current title grows with the number
 * of base-26 digits.
 *
 * Optimal Interview Solution:
 * I convert the number directly to spreadsheet base 26 by subtracting one before
 * each modulo operation, which accounts for titles being 1-indexed from A.
 *
 * Time Complexity: O(log n), because each loop removes one base-26 digit.
 * Space Complexity: O(log n), because the result stores one character per
 * base-26 digit.
 */
