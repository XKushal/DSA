import java.util.HashSet;
import java.util.Set;

class JewelsAndStones {
    public int bruteForceNumJewelsInStones(String jewels, String stones) {
        int jewelCount = 0;

        for (int stoneIndex = 0; stoneIndex < stones.length(); stoneIndex++) {
            for (int jewelIndex = 0; jewelIndex < jewels.length(); jewelIndex++) {
                if (stones.charAt(stoneIndex) == jewels.charAt(jewelIndex)) {
                    jewelCount++;
                    break;
                }
            }
        }

        return jewelCount;
    }

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelTypes = new HashSet<>();

        for (char jewel : jewels.toCharArray()) {
            jewelTypes.add(jewel);
        }

        int jewelCount = 0;
        for (char stone : stones.toCharArray()) {
            if (jewelTypes.contains(stone)) {
                jewelCount++;
            }
        }

        return jewelCount;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        JewelsAndStones solution = new JewelsAndStones();

        check("brute force counts jewels", solution.bruteForceNumJewelsInStones("aA", "aAAbbbb"), 3);

        check("counts jewels", solution.numJewelsInStones("aA", "aAAbbbb"), 3);
        check("distinguishes letter case", solution.numJewelsInStones("z", "ZZ"), 0);
        check("counts repeated jewel stones", solution.numJewelsInStones("abc", "aabbccdd"), 6);
        check("handles no matching stones", solution.numJewelsInStones("xy", "aabb"), 0);
    }
}

/*
 * Brute Force:
 * I compare every stone with each jewel type and count the stone when the
 * characters match.
 *
 * Time Complexity: O(j * s), where j is the number of jewel types and s is
 * the number of stones.
 * Space Complexity: O(1), because only the count and loop values are stored.
 *
 * Optimal Interview Solution:
 * I store every jewel type in a HashSet, then scan the stones once and use
 * constant-time average lookups to identify jewels.
 *
 * Time Complexity: O(j + s), because the jewel types and stones are each
 * scanned once.
 * Space Complexity: O(j) for the set of jewel types.
 */
