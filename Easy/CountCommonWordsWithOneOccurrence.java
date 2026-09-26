import java.util.HashMap;
import java.util.Map;

class CountCommonWordsWithOneOccurrence {
    public int bruteForceCountWords(String[] words1, String[] words2) {
        int common = 0;

        for (int i = 0; i < words1.length; i++) {
            if (appearsEarlier(words1, i)) {
                continue;
            }

            if (countOccurrences(words1, words1[i]) == 1 && countOccurrences(words2, words1[i]) == 1) {
                common++;
            }
        }

        return common;
    }

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> firstCounts = buildCounts(words1);
        Map<String, Integer> secondCounts = buildCounts(words2);
        int common = 0;

        for (Map.Entry<String, Integer> entry : firstCounts.entrySet()) {
            String word = entry.getKey();

            if (entry.getValue() == 1 && secondCounts.getOrDefault(word, 0) == 1) {
                common++;
            }
        }

        return common;
    }

    private int countOccurrences(String[] words, String target) {
        int count = 0;

        for (String word : words) {
            if (word.equals(target)) {
                count++;
            }
        }

        return count;
    }

    private boolean appearsEarlier(String[] words, int index) {
        for (int i = 0; i < index; i++) {
            if (words[i].equals(words[index])) {
                return true;
            }
        }

        return false;
    }

    private Map<String, Integer> buildCounts(String[] words) {
        Map<String, Integer> counts = new HashMap<>();

        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        return counts;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountCommonWordsWithOneOccurrence solution = new CountCommonWordsWithOneOccurrence();

        check("brute force sample one", solution.bruteForceCountWords(
                new String[] {"leetcode", "is", "amazing", "as", "is"},
                new String[] {"amazing", "leetcode", "is"}), 2);
        check("brute force sample two", solution.bruteForceCountWords(
                new String[] {"b", "bb", "bbb"},
                new String[] {"a", "aa", "aaa"}), 0);

        check("sample one", solution.countWords(
                new String[] {"leetcode", "is", "amazing", "as", "is"},
                new String[] {"amazing", "leetcode", "is"}), 2);
        check("sample two", solution.countWords(
                new String[] {"b", "bb", "bbb"},
                new String[] {"a", "aa", "aaa"}), 0);
        check("sample three", solution.countWords(
                new String[] {"a", "ab"},
                new String[] {"a", "a", "a", "ab"}), 1);
        check("duplicate in both arrays", solution.countWords(
                new String[] {"x", "x", "y"},
                new String[] {"y", "z", "z"}), 1);
    }
}

/*
 * Brute Force:
 * I consider each distinct word from the first array, count its occurrences in
 * both arrays by scanning, and count it only when both frequencies are one.
 *
 * Time Complexity: O(n * (n + m)), where n and m are the array lengths.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * I build frequency maps for both arrays, then count words whose frequency is
 * exactly one in both maps.
 *
 * Time Complexity: O(n + m), where n and m are the array lengths.
 * Space Complexity: O(n + m), because both frequency maps store words.
 */
