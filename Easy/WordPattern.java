import java.util.HashMap;
import java.util.Map;

class WordPattern {
    public boolean bruteForceWordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        for (int left = 0; left < pattern.length(); left++) {
            for (int right = left + 1; right < pattern.length(); right++) {
                boolean samePatternLetter = pattern.charAt(left) == pattern.charAt(right);
                boolean sameWord = words[left].equals(words[right]);

                if (samePatternLetter != sameWord) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> letterToWord = new HashMap<>();
        Map<String, Character> wordToLetter = new HashMap<>();

        for (int index = 0; index < pattern.length(); index++) {
            char letter = pattern.charAt(index);
            String word = words[index];

            if (letterToWord.containsKey(letter) && !letterToWord.get(letter).equals(word)) {
                return false;
            }

            if (wordToLetter.containsKey(word) && wordToLetter.get(word) != letter) {
                return false;
            }

            letterToWord.put(letter, word);
            wordToLetter.put(word, letter);
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        WordPattern solution = new WordPattern();

        check("brute force matches a repeated pattern", solution.bruteForceWordPattern("abba", "dog cat cat dog"), true);

        check("matches a repeated pattern", solution.wordPattern("abba", "dog cat cat dog"), true);
        check("rejects same pattern with different words", solution.wordPattern("abba", "dog cat cat fish"), false);
        check("rejects two pattern letters sharing one word", solution.wordPattern("abba", "dog dog dog dog"), false);
        check("rejects mismatched lengths", solution.wordPattern("aaaa", "dog cat cat dog"), false);
    }
}

/*
 * Brute Force:
 * I compare every pair of positions. Two positions must have the same pattern
 * letter exactly when they also have the same word.
 *
 * Time Complexity: O(n^2), where n is the number of pattern letters and words.
 * Space Complexity: O(n), because the sentence is split into an array of words.
 *
 * Optimal Interview Solution:
 * I scan both sequences once while maintaining mappings in both directions:
 * pattern letter to word and word to pattern letter. Any conflicting mapping
 * means the pattern does not match.
 *
 * Time Complexity: O(n), where n is the number of pattern letters and words.
 * Space Complexity: O(n), because the maps can store each distinct letter and
 * word.
 */
