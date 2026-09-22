import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindCommonCharacters {
    public List<String> bruteForceCommonChars(String[] words) {
        List<String> result = new ArrayList<>();

        for (char candidate = 'a'; candidate <= 'z'; candidate++) {
            int commonCount = Integer.MAX_VALUE;

            for (String word : words) {
                int count = 0;

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == candidate) {
                        count++;
                    }
                }

                commonCount = Math.min(commonCount, count);
            }

            for (int i = 0; i < commonCount; i++) {
                result.add(String.valueOf(candidate));
            }
        }

        return result;
    }

    public List<String> commonChars(String[] words) {
        int[] commonFrequencies = new int[26];
        Arrays.fill(commonFrequencies, Integer.MAX_VALUE);

        for (String word : words) {
            int[] currentFrequencies = new int[26];

            for (int i = 0; i < word.length(); i++) {
                currentFrequencies[word.charAt(i) - 'a']++;
            }

            for (int i = 0; i < 26; i++) {
                commonFrequencies[i] = Math.min(commonFrequencies[i], currentFrequencies[i]);
            }
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            for (int count = 0; count < commonFrequencies[i]; count++) {
                result.add(String.valueOf((char) ('a' + i)));
            }
        }

        return result;
    }

    private static void check(String name, List<String> actual, List<String> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindCommonCharacters solution = new FindCommonCharacters();

        check("brute force sample one", solution.bruteForceCommonChars(
                new String[] {"bella", "label", "roller"}), Arrays.asList("e", "l", "l"));
        check("brute force sample two", solution.bruteForceCommonChars(
                new String[] {"cool", "lock", "cook"}), Arrays.asList("c", "o"));

        check("sample one", solution.commonChars(
                new String[] {"bella", "label", "roller"}), Arrays.asList("e", "l", "l"));
        check("sample two", solution.commonChars(
                new String[] {"cool", "lock", "cook"}), Arrays.asList("c", "o"));
        check("single word keeps every character", solution.commonChars(
                new String[] {"abc"}), Arrays.asList("a", "b", "c"));
        check("no common characters", solution.commonChars(
                new String[] {"abc", "def", "ghi"}), new ArrayList<>());
    }
}

/*
 * Brute Force:
 * I try every lowercase character and scan every word to count how many times
 * that character appears in all words.
 *
 * Time Complexity: O(26 * t), where t is the total number of characters across
 * all words.
 * Space Complexity: O(1), not counting the returned list.
 *
 * Optimal Interview Solution:
 * I keep the minimum frequency for each character across all words, then build
 * the answer from those shared frequencies.
 *
 * Time Complexity: O(t + 26 * w), where t is the total character count and w is
 * the number of words.
 * Space Complexity: O(1), not counting the returned list, because the frequency
 * arrays have a fixed size of 26.
 */
