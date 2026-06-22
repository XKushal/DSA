class ValidPalindrome {
    public boolean bruteForceIsPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder();

        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);
            if (Character.isLetterOrDigit(current)) {
                cleaned.append(Character.toLowerCase(current));
            }
        }

        String normalized = cleaned.toString();
        String reversed = cleaned.reverse().toString();

        return normalized.equals(reversed);
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();

        check("brute force accepts sentence palindrome", solution.bruteForceIsPalindrome("A man, a plan, a canal: Panama"), true);
        check("brute force rejects non-palindrome", solution.bruteForceIsPalindrome("race a car"), false);

        check("accepts sentence palindrome", solution.isPalindrome("A man, a plan, a canal: Panama"), true);
        check("rejects non-palindrome", solution.isPalindrome("race a car"), false);
        check("accepts empty normalized string", solution.isPalindrome(" "), true);
        check("accepts mixed case letters and numbers", solution.isPalindrome("0P"), false);
    }
}

/*
 * Brute Force:
 * I build a normalized string containing only lowercase letters and digits,
 * then compare it with its reversed version.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(n), because the normalized and reversed strings are stored.
 *
 * Optimal Interview Solution:
 * I use two pointers from both ends, skipping characters that are not letters
 * or digits. Each valid character pair is compared case-insensitively.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(1), because only pointer variables are used.
 */