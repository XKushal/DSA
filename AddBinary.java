import java.math.BigInteger;

class AddBinary {
    public String bruteForceAddBinary(String a, String b) {
        BigInteger first = new BigInteger(a, 2);
        BigInteger second = new BigInteger(b, 2);

        return first.add(second).toString(2);
    }

    public String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder();
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int carry = 0;

        while (aIndex >= 0 || bIndex >= 0 || carry > 0) {
            int digitSum = carry;

            if (aIndex >= 0) {
                digitSum += a.charAt(aIndex) - '0';
                aIndex--;
            }

            if (bIndex >= 0) {
                digitSum += b.charAt(bIndex) - '0';
                bIndex--;
            }

            sum.append(digitSum % 2);
            carry = digitSum / 2;
        }

        return sum.reverse().toString();
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        AddBinary solution = new AddBinary();

        check("brute force adds two short binary values", solution.bruteForceAddBinary("11", "1"), "100");
        check("brute force carries across multiple digits", solution.bruteForceAddBinary("1010", "1011"), "10101");

        check("adds two short binary values", solution.addBinary("11", "1"), "100");
        check("carries across multiple digits", solution.addBinary("1010", "1011"), "10101");
        check("handles zero", solution.addBinary("0", "0"), "0");
        check("handles a final carry", solution.addBinary("1111", "1"), "10000");
    }
}

/*
 * Brute Force:
 * I parse both binary strings as base-two numbers, add those values, then
 * convert the sum back to a binary string.
 *
 * Time Complexity: O(n + m), where n and m are the lengths of a and b.
 * Space Complexity: O(n + m), because the converted binary result is stored.
 *
 * Optimal Interview Solution:
 * I scan both strings from right to left, add one digit from each string plus
 * the carry, append the resulting binary digit, and reverse the built answer.
 *
 * Time Complexity: O(max(n, m)), where n and m are the lengths of a and b.
 * Space Complexity: O(max(n, m)), because the result can contain one extra
 * carry digit.
 */
