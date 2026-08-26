class ShuffleString {
    public String bruteForceRestoreString(String s, int[] indices) {
        StringBuilder restored = new StringBuilder();

        for (int targetIndex = 0; targetIndex < s.length(); targetIndex++) {
            for (int currentIndex = 0; currentIndex < indices.length; currentIndex++) {
                if (indices[currentIndex] == targetIndex) {
                    restored.append(s.charAt(currentIndex));
                    break;
                }
            }
        }

        return restored.toString();
    }

    public String restoreString(String s, int[] indices) {
        char[] restored = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            restored[indices[i]] = s.charAt(i);
        }

        return new String(restored);
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] indices(int... values) {
        return values;
    }

    public static void main(String[] args) {
        ShuffleString solution = new ShuffleString();

        check("brute force sample", solution.bruteForceRestoreString("codeleet",
            indices(4, 5, 6, 7, 0, 2, 1, 3)), "leetcode");
        check("brute force ordered", solution.bruteForceRestoreString("abc", indices(0, 1, 2)), "abc");

        check("sample", solution.restoreString("codeleet", indices(4, 5, 6, 7, 0, 2, 1, 3)), "leetcode");
        check("ordered", solution.restoreString("abc", indices(0, 1, 2)), "abc");
        check("reverse", solution.restoreString("aiohn", indices(3, 1, 4, 2, 0)), "nihao");
        check("single character", solution.restoreString("z", indices(0)), "z");
    }
}

/*
 * Brute Force:
 * I build the answer from left to right. For each target position, I scan the
 * indices array to find the character that belongs there.
 *
 * Time Complexity: O(n^2), because each output position may scan all indices.
 * Space Complexity: O(n), because the restored string is built separately.
 *
 * Optimal Interview Solution:
 * I place each character directly into its destination index, then create the
 * restored string from that character array.
 *
 * Time Complexity: O(n), because each character is placed once.
 * Space Complexity: O(n), because the restored character array is stored.
 */
