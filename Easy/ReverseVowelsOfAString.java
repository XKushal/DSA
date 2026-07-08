class ReverseVowelsOfAString {
    public String bruteForceReverseVowels(String s) {
        StringBuilder vowels = new StringBuilder();

        for (int index = 0; index < s.length(); index++) {
            if (isVowel(s.charAt(index))) {
                vowels.append(s.charAt(index));
            }
        }

        char[] result = s.toCharArray();
        int reversedIndex = vowels.length() - 1;

        for (int index = 0; index < result.length; index++) {
            if (isVowel(result[index])) {
                result[index] = vowels.charAt(reversedIndex);
                reversedIndex--;
            }
        }

        return new String(result);
    }

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            while (left < right && !isVowel(chars[left])) {
                left++;
            }

            while (left < right && !isVowel(chars[right])) {
                right--;
            }

            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    private boolean isVowel(char value) {
        return value == 'a' || value == 'e' || value == 'i' || value == 'o' || value == 'u'
                || value == 'A' || value == 'E' || value == 'I' || value == 'O' || value == 'U';
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ReverseVowelsOfAString solution = new ReverseVowelsOfAString();

        check("brute force reverses vowels", solution.bruteForceReverseVowels("IceCreAm"), "AceCreIm");

        check("reverses vowels", solution.reverseVowels("IceCreAm"), "AceCreIm");
        check("handles lowercase example", solution.reverseVowels("leetcode"), "leotcede");
        check("leaves consonants unchanged", solution.reverseVowels("rhythm"), "rhythm");
        check("handles two vowels", solution.reverseVowels("hello"), "holle");
        check("preserves vowel case positions", solution.reverseVowels("aA"), "Aa");
    }
}

/*
 * Brute Force:
 * I collect every vowel in order, then scan the string again and place those
 * collected vowels back from right to left.
 *
 * Time Complexity: O(n), where n is the string length, because the string is
 * scanned twice.
 * Space Complexity: O(n), because the collected vowels and output characters
 * can both grow with the input length.
 *
 * Optimal Interview Solution:
 * I use two pointers to find the next vowel from each side, then swap those
 * vowels in a character array until the pointers cross.
 *
 * Time Complexity: O(n), because each pointer moves across the string at most
 * once.
 * Space Complexity: O(n), because Java strings are immutable and the character
 * array stores the returned result.
 */
