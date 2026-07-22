import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UniqueMorseCodeWords {
    private static final String[] MORSE_CODES = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--.."
    };

    public int bruteForceUniqueMorseRepresentations(String[] words) {
        List<String> transformations = new ArrayList<>();

        for (String word : words) {
            String transformed = transform(word);
            boolean seen = false;

            for (String existing : transformations) {
                if (existing.equals(transformed)) {
                    seen = true;
                    break;
                }
            }

            if (!seen) {
                transformations.add(transformed);
            }
        }

        return transformations.size();
    }

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> transformations = new HashSet<>();

        for (String word : words) {
            transformations.add(transform(word));
        }

        return transformations.size();
    }

    private String transform(String word) {
        StringBuilder transformed = new StringBuilder();

        for (int index = 0; index < word.length(); index++) {
            transformed.append(MORSE_CODES[word.charAt(index) - 'a']);
        }

        return transformed.toString();
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        UniqueMorseCodeWords solution = new UniqueMorseCodeWords();

        check("brute force counts duplicate transformations",
                solution.bruteForceUniqueMorseRepresentations(new String[] {"gin", "zen", "gig", "msg"}), 2);
        check("brute force counts one word once",
                solution.bruteForceUniqueMorseRepresentations(new String[] {"a"}), 1);

        check("counts duplicate transformations",
                solution.uniqueMorseRepresentations(new String[] {"gin", "zen", "gig", "msg"}), 2);
        check("counts repeated same word once",
                solution.uniqueMorseRepresentations(new String[] {"no", "no", "on"}), 2);
        check("counts distinct single letters",
                solution.uniqueMorseRepresentations(new String[] {"a", "b", "c", "a"}), 3);
    }
}

/*
 * Brute Force:
 * I transform each word into Morse code, then scan the transformations already
 * collected to decide whether the new one is unique.
 *
 * Time Complexity: O(n^2 * k), where n is the number of words and k is the
 * maximum word length.
 * Space Complexity: O(n * k), because the list can store one transformed Morse
 * string per unique word.
 *
 * Optimal Interview Solution:
 * I transform each word and store each representation in a hash set so duplicate
 * Morse strings collapse automatically.
 *
 * Time Complexity: O(n * k), where n is the number of words and k is the maximum
 * word length.
 * Space Complexity: O(n * k), because the hash set can store one transformed
 * Morse string per unique word.
 */
