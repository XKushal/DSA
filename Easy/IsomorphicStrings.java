import java.util.HashMap;
import java.util.Map;

class IsomorphicStrings {
    public boolean bruteForceIsIsomorphic(String s, String t) {
        for (int first = 0; first < s.length(); first++) {
            for (int second = first + 1; second < s.length(); second++) {
                boolean sameInS = s.charAt(first) == s.charAt(second);
                boolean sameInT = t.charAt(first) == t.charAt(second);

                if (sameInS != sameInT) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int index = 0; index < s.length(); index++) {
            char sCharacter = s.charAt(index);
            char tCharacter = t.charAt(index);

            if (sToT.containsKey(sCharacter) && sToT.get(sCharacter) != tCharacter) {
                return false;
            }

            if (tToS.containsKey(tCharacter) && tToS.get(tCharacter) != sCharacter) {
                return false;
            }

            sToT.put(sCharacter, tCharacter);
            tToS.put(tCharacter, sCharacter);
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        IsomorphicStrings solution = new IsomorphicStrings();

        check("brute force accepts matching character patterns",
                solution.bruteForceIsIsomorphic("egg", "add"), true);

        check("accepts matching character patterns", solution.isIsomorphic("paper", "title"), true);
        check("rejects inconsistent mapping", solution.isIsomorphic("foo", "bar"), false);
        check("rejects two characters mapping to one", solution.isIsomorphic("badc", "baba"), false);
        check("handles single characters", solution.isIsomorphic("a", "z"), true);
    }
}

/*
 * Brute Force:
 * I compare every pair of positions in both strings. Equal characters in one
 * string must appear at exactly the same pairs of positions in the other.
 *
 * Time Complexity: O(n^2), where n is the length of either string.
 * Space Complexity: O(1), because only loop indices and booleans are stored.
 *
 * Optimal Interview Solution:
 * I scan both strings once while maintaining mappings in both directions. The
 * reverse mapping prevents two source characters from mapping to one target.
 *
 * Time Complexity: O(n), where n is the length of either string.
 * Space Complexity: O(k), where k is the number of distinct characters.
 */
