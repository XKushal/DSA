import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KeyboardRow {
    private static final String[] ROWS = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

    public String[] bruteForceFindWords(String[] words) {
        List<String> matches = new ArrayList<>();

        for (String word : words) {
            if (canTypeWithOneRowBruteForce(word)) {
                matches.add(word);
            }
        }

        return matches.toArray(new String[0]);
    }

    public String[] findWords(String[] words) {
        int[] rowByCharacter = buildRowLookup();
        List<String> matches = new ArrayList<>();

        for (String word : words) {
            if (canTypeWithOneRow(word, rowByCharacter)) {
                matches.add(word);
            }
        }

        return matches.toArray(new String[0]);
    }

    private boolean canTypeWithOneRowBruteForce(String word) {
        int targetRow = findRow(word.charAt(0));

        for (int index = 1; index < word.length(); index++) {
            if (findRow(word.charAt(index)) != targetRow) {
                return false;
            }
        }

        return true;
    }

    private int findRow(char character) {
        char lower = Character.toLowerCase(character);

        for (int row = 0; row < ROWS.length; row++) {
            if (ROWS[row].indexOf(lower) >= 0) {
                return row;
            }
        }

        return -1;
    }

    private int[] buildRowLookup() {
        int[] rowByCharacter = new int[26];

        for (int row = 0; row < ROWS.length; row++) {
            for (int index = 0; index < ROWS[row].length(); index++) {
                rowByCharacter[ROWS[row].charAt(index) - 'a'] = row;
            }
        }

        return rowByCharacter;
    }

    private boolean canTypeWithOneRow(String word, int[] rowByCharacter) {
        int targetRow = rowByCharacter[Character.toLowerCase(word.charAt(0)) - 'a'];

        for (int index = 1; index < word.length(); index++) {
            int currentRow = rowByCharacter[Character.toLowerCase(word.charAt(index)) - 'a'];

            if (currentRow != targetRow) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, String[] actual, String[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        KeyboardRow solution = new KeyboardRow();

        check("brute force finds words typed with one row",
                solution.bruteForceFindWords(new String[] {"Hello", "Alaska", "Dad", "Peace"}),
                new String[] {"Alaska", "Dad"});
        check("brute force handles all rows",
                solution.bruteForceFindWords(new String[] {"adsdf", "sfd"}),
                new String[] {"adsdf", "sfd"});

        check("finds words typed with one row",
                solution.findWords(new String[] {"Hello", "Alaska", "Dad", "Peace"}),
                new String[] {"Alaska", "Dad"});
        check("handles all words in one row",
                solution.findWords(new String[] {"adsdf", "sfd"}),
                new String[] {"adsdf", "sfd"});
        check("handles mixed case words",
                solution.findWords(new String[] {"type", "ROW", "Pop"}),
                new String[] {"type", "ROW", "Pop"});
        check("handles no matches",
                solution.findWords(new String[] {"cat", "dog"}),
                new String[0]);
    }
}

/*
 * Brute Force:
 * I find the keyboard row for each character by scanning the three row strings,
 * then keep a word only when every character maps to the first character's row.
 *
 * Time Complexity: O(n * k), where n is the total number of characters across
 * all words and k is the fixed keyboard-row length scanned for each character.
 * Space Complexity: O(m), where m is the number of matching words stored in the
 * result list.
 *
 * Optimal Interview Solution:
 * I precompute a row lookup for the 26 lowercase letters, then check each word
 * with constant-time row lookups for every character.
 *
 * Time Complexity: O(n), where n is the total number of characters across all
 * words, because each character is checked once after the fixed lookup build.
 * Space Complexity: O(m), where m is the number of matching words stored in the
 * result list; the 26-entry lookup table is constant auxiliary space.
 */
