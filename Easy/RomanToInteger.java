class RomanToInteger {
    public int bruteForceRomanToInt(String s) {
        String[] symbols = {"CM", "CD", "XC", "XL", "IX", "IV", "M", "D", "C", "L", "X", "V", "I"};
        int[] values = {900, 400, 90, 40, 9, 4, 1000, 500, 100, 50, 10, 5, 1};
        int total = 0;
        int index = 0;

        while (index < s.length()) {
            for (int symbolIndex = 0; symbolIndex < symbols.length; symbolIndex++) {
                String symbol = symbols[symbolIndex];

                if (s.startsWith(symbol, index)) {
                    total += values[symbolIndex];
                    index += symbol.length();
                    break;
                }
            }
        }

        return total;
    }

    public int romanToInt(String s) {
        int total = 0;
        int previousValue = 0;

        for (int index = s.length() - 1; index >= 0; index--) {
            int currentValue = valueOf(s.charAt(index));

            if (currentValue < previousValue) {
                total -= currentValue;
            } else {
                total += currentValue;
                previousValue = currentValue;
            }
        }

        return total;
    }

    private int valueOf(char symbol) {
        switch (symbol) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        RomanToInteger solution = new RomanToInteger();

        check("brute force handles subtractive notation", solution.bruteForceRomanToInt("MCMXCIV"), 1994);

        check("converts additive numerals", solution.romanToInt("III"), 3);
        check("converts mixed numerals", solution.romanToInt("LVIII"), 58);
        check("converts subtractive numerals", solution.romanToInt("MCMXCIV"), 1994);
    }
}

/*
 * Brute Force:
 * I scan from left to right and try each valid Roman token, including the
 * subtractive two-character tokens, until one matches the current position.
 *
 * Time Complexity: O(n), where n is the length of s. The token list is fixed.
 * Space Complexity: O(1), because the token and value arrays have fixed size.
 *
 * Optimal Interview Solution:
 * I scan from right to left. A symbol smaller than the last symbol to its
 * right is subtractive, and every other symbol is additive.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(1), because only running totals are stored.
 */
