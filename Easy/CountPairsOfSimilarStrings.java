import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class CountPairsOfSimilarStrings {
    public int bruteForceSimilarPairs(String[] words) {
        int pairs = 0;

        for (int i = 0; i < words.length; i++) {
            Set<Character> first = uniqueCharacters(words[i]);

            for (int j = i + 1; j < words.length; j++) {
                Set<Character> second = uniqueCharacters(words[j]);
                if (first.equals(second)) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    public int similarPairs(String[] words) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        int pairs = 0;

        for (String word : words) {
            int mask = buildMask(word);
            int previous = frequencies.getOrDefault(mask, 0);
            pairs += previous;
            frequencies.put(mask, previous + 1);
        }

        return pairs;
    }

    private Set<Character> uniqueCharacters(String word) {
        Set<Character> characters = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            characters.add(word.charAt(i));
        }

        return characters;
    }

    private int buildMask(String word) {
        int mask = 0;

        for (int i = 0; i < word.length(); i++) {
            mask |= 1 << (word.charAt(i) - 'a');
        }

        return mask;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountPairsOfSimilarStrings solution = new CountPairsOfSimilarStrings();

        check("brute force sample one", solution.bruteForceSimilarPairs(
                new String[] {"aba", "aabb", "abcd", "bac", "aabc"}), 2);
        check("brute force sample two", solution.bruteForceSimilarPairs(
                new String[] {"aabb", "ab", "ba"}), 3);

        check("sample one", solution.similarPairs(
                new String[] {"aba", "aabb", "abcd", "bac", "aabc"}), 2);
        check("sample two", solution.similarPairs(new String[] {"aabb", "ab", "ba"}), 3);
        check("sample three", solution.similarPairs(new String[] {"nba", "cba", "dba"}), 0);
        check("all match", solution.similarPairs(new String[] {"abc", "bca", "cab", "aabbcc"}), 6);
        check("single word", solution.similarPairs(new String[] {"leetcode"}), 0);
    }
}

/*
 * Brute Force:
 * I build a set of unique characters for every pair of words and count the
 * pairs whose sets are equal.
 *
 * Time Complexity: O(n^2 * k), where n is the number of words and k is the
 * maximum word length.
 * Space Complexity: O(k), because each comparison stores character sets for
 * two words.
 *
 * Optimal Interview Solution:
 * I compress each word's unique lowercase letters into a bitmask. Each new
 * word forms one similar pair with every previously seen word that has the
 * same mask.
 *
 * Time Complexity: O(n * k), where n is the number of words and k is the
 * maximum word length.
 * Space Complexity: O(n), because each distinct character mask can be stored.
 */
