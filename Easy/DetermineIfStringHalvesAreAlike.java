class DetermineIfStringHalvesAreAlike {
    public boolean bruteForceHalvesAreAlike(String s) {
        int middle = s.length() / 2;
        int firstHalfVowels = 0;
        int secondHalfVowels = 0;
        String vowels = "aeiouAEIOU";

        for (int i = 0; i < middle; i++) {
            for (int j = 0; j < vowels.length(); j++) {
                if (s.charAt(i) == vowels.charAt(j)) {
                    firstHalfVowels++;
                    break;
                }
            }
        }

        for (int i = middle; i < s.length(); i++) {
            for (int j = 0; j < vowels.length(); j++) {
                if (s.charAt(i) == vowels.charAt(j)) {
                    secondHalfVowels++;
                    break;
                }
            }
        }

        return firstHalfVowels == secondHalfVowels;
    }

    public boolean halvesAreAlike(String s) {
        int balance = 0;
        int middle = s.length() / 2;

        for (int i = 0; i < middle; i++) {
            if (isVowel(s.charAt(i))) {
                balance++;
            }

            if (isVowel(s.charAt(i + middle))) {
                balance--;
            }
        }

        return balance == 0;
    }

    private boolean isVowel(char letter) {
        return letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'
                || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U';
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DetermineIfStringHalvesAreAlike solution = new DetermineIfStringHalvesAreAlike();

        check("brute force sample one", solution.bruteForceHalvesAreAlike("book"), true);
        check("brute force sample two", solution.bruteForceHalvesAreAlike("textbook"), false);

        check("sample one", solution.halvesAreAlike("book"), true);
        check("sample two", solution.halvesAreAlike("textbook"), false);
        check("uppercase vowels", solution.halvesAreAlike("AbCdEfGh"), true);
        check("no vowels", solution.halvesAreAlike("bcdfghjk"), true);
        check("unbalanced vowels", solution.halvesAreAlike("aezzzzzz"), false);
    }
}

/*
 * Brute Force:
 * I count vowels in each half by comparing every character with every vowel
 * candidate.
 *
 * Time Complexity: O(n * v), where n is the string length and v is the number
 * of vowel candidates.
 * Space Complexity: O(1), because only counters and the fixed vowel string are
 * stored.
 *
 * Optimal Interview Solution:
 * I walk both halves together and keep a single balance: vowels in the first
 * half add one, vowels in the second half subtract one.
 *
 * Time Complexity: O(n), because each mirrored pair is inspected once.
 * Space Complexity: O(1), because only the balance and loop values are stored.
 */
