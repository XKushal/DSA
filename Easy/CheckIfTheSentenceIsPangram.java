class CheckIfTheSentenceIsPangram {
    public boolean bruteForceCheckIfPangram(String sentence) {
        for (char current = 'a'; current <= 'z'; current++) {
            boolean found = false;

            for (int i = 0; i < sentence.length(); i++) {
                if (sentence.charAt(i) == current) {
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

    public boolean checkIfPangram(String sentence) {
        boolean[] seen = new boolean[26];
        int uniqueLetters = 0;

        for (int i = 0; i < sentence.length(); i++) {
            int index = sentence.charAt(i) - 'a';

            if (!seen[index]) {
                seen[index] = true;
                uniqueLetters++;

                if (uniqueLetters == 26) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CheckIfTheSentenceIsPangram solution = new CheckIfTheSentenceIsPangram();

        check("brute force sample one",
                solution.bruteForceCheckIfPangram("thequickbrownfoxjumpsoverthelazydog"), true);
        check("brute force sample two",
                solution.bruteForceCheckIfPangram("leetcode"), false);

        check("sample one", solution.checkIfPangram("thequickbrownfoxjumpsoverthelazydog"), true);
        check("sample two", solution.checkIfPangram("leetcode"), false);
        check("missing z", solution.checkIfPangram("abcdefghijklmnopqrstuvwxy"), false);
        check("repeated letters", solution.checkIfPangram("abcdefghijklmnopqrstuvwxyzabc"), true);
    }
}

/*
 * Brute Force:
 * I check every lowercase letter from a through z, scanning the sentence each
 * time to confirm that the letter appears at least once.
 *
 * Time Complexity: O(26 * n), which simplifies to O(n), where n is the sentence
 * length.
 * Space Complexity: O(1), because only loop counters and flags are stored.
 *
 * Optimal Interview Solution:
 * I scan the sentence once, mark each seen letter in a fixed-size table, and
 * return early as soon as all 26 letters have appeared.
 *
 * Time Complexity: O(n), where n is the sentence length.
 * Space Complexity: O(1), because the seen table has fixed alphabet size.
 */
