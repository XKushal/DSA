class ConvertDateToBinary {
    public String bruteForceConvertDateToBinary(String date) {
        String[] parts = date.split("-");
        return toBinaryByDivision(parseNumber(parts[0])) + "-"
                + toBinaryByDivision(parseNumber(parts[1])) + "-"
                + toBinaryByDivision(parseNumber(parts[2]));
    }

    private int parseNumber(String value) {
        int number = 0;

        for (int i = 0; i < value.length(); i++) {
            number = number * 10 + value.charAt(i) - '0';
        }

        return number;
    }

    private String toBinaryByDivision(int number) {
        if (number == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();

        while (number > 0) {
            binary.append(number % 2);
            number /= 2;
        }

        return binary.reverse().toString();
    }

    public String convertDateToBinary(String date) {
        String[] parts = date.split("-");
        return Integer.toBinaryString(Integer.parseInt(parts[0])) + "-"
                + Integer.toBinaryString(Integer.parseInt(parts[1])) + "-"
                + Integer.toBinaryString(Integer.parseInt(parts[2]));
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ConvertDateToBinary solution = new ConvertDateToBinary();

        check("brute force sample", solution.bruteForceConvertDateToBinary("2080-02-29"),
                "100000100000-10-11101");
        check("brute force lower bound", solution.bruteForceConvertDateToBinary("1900-01-01"),
                "11101101100-1-1");

        check("sample", solution.convertDateToBinary("2080-02-29"), "100000100000-10-11101");
        check("leap day", solution.convertDateToBinary("2024-02-29"), "11111101000-10-11101");
        check("lower bound", solution.convertDateToBinary("1900-01-01"), "11101101100-1-1");
        check("year end", solution.convertDateToBinary("2025-12-31"), "11111101001-1100-11111");
    }
}

/*
 * Brute Force:
 * I split the date, parse each decimal number by hand, then build its binary
 * representation through repeated division by two.
 *
 * Time Complexity: O(1), because valid date strings have fixed length.
 * Space Complexity: O(1), because the split parts and binary strings are fixed
 * in size.
 *
 * Optimal Interview Solution:
 * I parse the year, month, and day with the standard library and convert each
 * value directly with Integer.toBinaryString.
 *
 * Time Complexity: O(1), because the input date length is fixed.
 * Space Complexity: O(1), because the output has a fixed maximum size.
 */
