class LicenseKeyFormatting {
    public String bruteForceLicenseKeyFormatting(String s, int k) {
        StringBuilder cleaned = new StringBuilder();

        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);

            if (current != '-') {
                cleaned.append(Character.toUpperCase(current));
            }
        }

        if (cleaned.length() == 0) {
            return "";
        }

        int firstGroupLength = cleaned.length() % k;
        StringBuilder formatted = new StringBuilder();
        int index = 0;

        if (firstGroupLength > 0) {
            formatted.append(cleaned.substring(0, firstGroupLength));
            index = firstGroupLength;
        }

        while (index < cleaned.length()) {
            if (formatted.length() > 0) {
                formatted.append('-');
            }

            formatted.append(cleaned.substring(index, index + k));
            index += k;
        }

        return formatted.toString();
    }

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder reversed = new StringBuilder();
        int groupSize = 0;

        for (int index = s.length() - 1; index >= 0; index--) {
            char current = s.charAt(index);

            if (current == '-') {
                continue;
            }

            if (groupSize == k) {
                reversed.append('-');
                groupSize = 0;
            }

            reversed.append(Character.toUpperCase(current));
            groupSize++;
        }

        return reversed.reverse().toString();
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LicenseKeyFormatting solution = new LicenseKeyFormatting();

        check("brute force formats uneven first group", solution.bruteForceLicenseKeyFormatting("5F3Z-2e-9-w", 4), "5F3Z-2E9W");
        check("brute force formats one-character first group", solution.bruteForceLicenseKeyFormatting("2-5g-3-J", 2), "2-5G-3J");

        check("formats uneven first group", solution.licenseKeyFormatting("5F3Z-2e-9-w", 4), "5F3Z-2E9W");
        check("formats one-character first group", solution.licenseKeyFormatting("2-5g-3-J", 2), "2-5G-3J");
        check("keeps exact group size", solution.licenseKeyFormatting("abcd", 2), "AB-CD");
        check("removes leading separators", solution.licenseKeyFormatting("--a-a-a-a--", 2), "AA-AA");
        check("handles only separators", solution.licenseKeyFormatting("---", 3), "");
    }
}

/*
 * Brute Force:
 * I first remove every dash and uppercase the remaining characters, then build
 * the formatted key from left to right using the computed first group size.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(n), because the cleaned key and formatted key are stored.
 *
 * Optimal Interview Solution:
 * I scan from right to left, adding uppercase characters and inserting a dash
 * after every k kept characters, then reverse the result.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(n), because the output builder stores the formatted key.
 */
