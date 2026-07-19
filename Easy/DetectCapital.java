class DetectCapital {
    public boolean bruteForceDetectCapitalUse(String word) {
        return isAllUppercase(word) || isAllLowercase(word) || isTitleCase(word);
    }

    private boolean isAllUppercase(String word) {
        for (int index = 0; index < word.length(); index++) {
            if (Character.isLowerCase(word.charAt(index))) {
                return false;
            }
        }

        return true;
    }

    private boolean isAllLowercase(String word) {
        for (int index = 0; index < word.length(); index++) {
            if (Character.isUpperCase(word.charAt(index))) {
                return false;
            }
        }

        return true;
    }

    private boolean isTitleCase(String word) {
        if (word.length() == 0 || Character.isLowerCase(word.charAt(0))) {
            return false;
        }

        for (int index = 1; index < word.length(); index++) {
            if (Character.isUpperCase(word.charAt(index))) {
                return false;
            }
        }

        return true;
    }

    public boolean detectCapitalUse(String word) {
        int capitalCount = 0;

        for (int index = 0; index < word.length(); index++) {
            if (Character.isUpperCase(word.charAt(index))) {
                capitalCount++;
            }
        }

        return capitalCount == word.length()
                || capitalCount == 0
                || capitalCount == 1 && Character.isUpperCase(word.charAt(0));
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DetectCapital solution = new DetectCapital();

        check("brute force accepts all capitals", solution.bruteForceDetectCapitalUse("USA"), true);
        check("brute force rejects mixed capitals", solution.bruteForceDetectCapitalUse("FlaG"), false);

        check("accepts all capitals", solution.detectCapitalUse("USA"), true);
        check("accepts all lowercase", solution.detectCapitalUse("leetcode"), true);
        check("accepts first capital only", solution.detectCapitalUse("Google"), true);
        check("rejects trailing capital", solution.detectCapitalUse("FlaG"), false);
        check("rejects lowercase then capital", solution.detectCapitalUse("mL"), false);
        check("accepts one capital letter", solution.detectCapitalUse("A"), true);
        check("accepts one lowercase letter", solution.detectCapitalUse("z"), true);
    }
}

/*
 * Brute Force:
 * I check the word against each valid capitalization pattern separately: all
 * uppercase, all lowercase, or only the first letter uppercase.
 *
 * Time Complexity: O(n), where n is the length of word. Each pattern scan is
 * linear and the number of scans is constant.
 * Space Complexity: O(1), because only loop counters are stored.
 *
 * Optimal Interview Solution:
 * I count the uppercase letters once, then validate that the count matches one
 * of the accepted patterns.
 *
 * Time Complexity: O(n), where n is the length of word.
 * Space Complexity: O(1), because only the capital count is stored.
 */
