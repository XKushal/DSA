class ExcelSheetColumnNumber {
    public int bruteForceTitleToNumber(String columnTitle) {
        String current = "";

        for (int number = 1; number <= Integer.MAX_VALUE; number++) {
            current = nextTitle(current);

            if (current.equals(columnTitle)) {
                return number;
            }
        }

        return -1;
    }

    public int titleToNumber(String columnTitle) {
        int columnNumber = 0;

        for (int index = 0; index < columnTitle.length(); index++) {
            int value = columnTitle.charAt(index) - 'A' + 1;
            columnNumber = (columnNumber * 26) + value;
        }

        return columnNumber;
    }

    private String nextTitle(String title) {
        if (title.length() == 0) {
            return "A";
        }

        char[] letters = title.toCharArray();
        int index = letters.length - 1;

        while (index >= 0 && letters[index] == 'Z') {
            letters[index] = 'A';
            index--;
        }

        if (index < 0) {
            return "A" + new String(letters);
        }

        letters[index]++;
        return new String(letters);
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ExcelSheetColumnNumber solution = new ExcelSheetColumnNumber();

        check("brute force converts single column", solution.bruteForceTitleToNumber("C"), 3);

        check("converts single column", solution.titleToNumber("A"), 1);
        check("converts after Z", solution.titleToNumber("AB"), 28);
        check("converts multi-letter title", solution.titleToNumber("ZY"), 701);
        check("converts LeetCode upper-bound example", solution.titleToNumber("FXSHRXW"), 2147483647);
    }
}

/*
 * Brute Force:
 * I generate spreadsheet column titles one by one, starting at A, until the
 * generated title matches the input.
 *
 * Time Complexity: O(answer * L), where answer is the numeric column value and
 * L is the title length.
 * Space Complexity: O(L), because each generated title uses a character array.
 *
 * Optimal Interview Solution:
 * I treat the title as a base-26 number where A is 1 and Z is 26, carrying the
 * running value from left to right.
 *
 * Time Complexity: O(n), where n is the length of columnTitle.
 * Space Complexity: O(1), because only the running column value is stored.
 */
