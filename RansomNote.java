class RansomNote {
    public boolean bruteForceCanConstruct(String ransomNote, String magazine) {
        boolean[] used = new boolean[magazine.length()];

        for (int noteIndex = 0; noteIndex < ransomNote.length(); noteIndex++) {
            char needed = ransomNote.charAt(noteIndex);
            boolean found = false;

            for (int magazineIndex = 0; magazineIndex < magazine.length(); magazineIndex++) {
                if (!used[magazineIndex] && magazine.charAt(magazineIndex) == needed) {
                    used[magazineIndex] = true;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counts = new int[26];

        for (int index = 0; index < magazine.length(); index++) {
            counts[magazine.charAt(index) - 'a']++;
        }

        for (int index = 0; index < ransomNote.length(); index++) {
            int letterIndex = ransomNote.charAt(index) - 'a';
            counts[letterIndex]--;

            if (counts[letterIndex] < 0) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        RansomNote solution = new RansomNote();

        check("brute force builds note from available letters", solution.bruteForceCanConstruct("aa", "aab"), true);
        check("brute force rejects missing repeated letter", solution.bruteForceCanConstruct("aa", "ab"), false);

        check("builds note from available letters", solution.canConstruct("aa", "aab"), true);
        check("rejects missing repeated letter", solution.canConstruct("aa", "ab"), false);
        check("accepts empty note", solution.canConstruct("", "abc"), true);
    }
}

/*
 * Brute Force:
 * I match each ransom note character against the first unused matching
 * character in the magazine. If any character cannot be matched, the note
 * cannot be constructed.
 *
 * Time Complexity: O(n * m), where n is the length of ransomNote and m is the
 * length of magazine.
 * Space Complexity: O(m), because the used magazine positions are tracked.
 *
 * Optimal Interview Solution:
 * I count how many times each lowercase letter appears in the magazine, then
 * spend those counts while scanning the ransom note. A negative count means the
 * note needs a letter more times than the magazine provides it.
 *
 * Time Complexity: O(n + m), where n is the length of ransomNote and m is the
 * length of magazine.
 * Space Complexity: O(1), because the alphabet count array has a fixed size.
 */
