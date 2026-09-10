import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindWordsContainingCharacter {
    public List<Integer> bruteForceFindWordsContaining(String[] words, char x) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            boolean containsTarget = false;

            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == x) {
                    containsTarget = true;
                    break;
                }
            }

            if (containsTarget) {
                indices.add(i);
            }
        }

        return indices;
    }

    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].indexOf(x) != -1) {
                indices.add(i);
            }
        }

        return indices;
    }

    private static void check(String name, List<Integer> actual, List<Integer> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindWordsContainingCharacter solution = new FindWordsContainingCharacter();

        check("brute force sample", solution.bruteForceFindWordsContaining(
                new String[] {"leet", "code"}, 'e'), Arrays.asList(0, 1));
        check("brute force none", solution.bruteForceFindWordsContaining(
                new String[] {"abc", "bcd", "aaaa", "cbc"}, 'z'), Arrays.asList());

        check("sample", solution.findWordsContaining(new String[] {"leet", "code"}, 'e'),
                Arrays.asList(0, 1));
        check("middle values", solution.findWordsContaining(
                new String[] {"abc", "bcd", "aaaa", "cbc"}, 'a'), Arrays.asList(0, 2));
        check("all missing", solution.findWordsContaining(
                new String[] {"abc", "bcd", "aaaa", "cbc"}, 'z'), Arrays.asList());
        check("single match", solution.findWordsContaining(
                new String[] {"hello", "world", "leetcode"}, 'o'), Arrays.asList(0, 1, 2));
    }
}

/*
 * Brute Force:
 * I scan every character of every word and add the word's index after finding
 * the target character.
 *
 * Time Complexity: O(n * m), where n is the number of words and m is the
 * maximum word length.
 * Space Complexity: O(k), where k is the number of matching indices returned.
 *
 * Optimal Interview Solution:
 * I still examine each word once, but delegate the character search to
 * String.indexOf and append matching indices in their original order.
 *
 * Time Complexity: O(n * m), where n is the number of words and m is the
 * maximum word length.
 * Space Complexity: O(k), where k is the number of matching indices returned.
 */
