class BackspaceStringCompare {
    public boolean bruteForceBackspaceCompare(String s, String t) {
        return buildEditedString(s).equals(buildEditedString(t));
    }

    public boolean backspaceCompare(String s, String t) {
        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;

        while (sIndex >= 0 || tIndex >= 0) {
            sIndex = nextVisibleIndex(s, sIndex);
            tIndex = nextVisibleIndex(t, tIndex);

            if (sIndex < 0 || tIndex < 0) {
                return sIndex == tIndex;
            }

            if (s.charAt(sIndex) != t.charAt(tIndex)) {
                return false;
            }

            sIndex--;
            tIndex--;
        }

        return true;
    }

    private String buildEditedString(String value) {
        StringBuilder edited = new StringBuilder();

        for (char character : value.toCharArray()) {
            if (character == '#') {
                if (edited.length() > 0) {
                    edited.deleteCharAt(edited.length() - 1);
                }
            } else {
                edited.append(character);
            }
        }

        return edited.toString();
    }

    private int nextVisibleIndex(String value, int index) {
        int backspaces = 0;

        while (index >= 0) {
            if (value.charAt(index) == '#') {
                backspaces++;
                index--;
            } else if (backspaces > 0) {
                backspaces--;
                index--;
            } else {
                break;
            }
        }

        return index;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        BackspaceStringCompare solution = new BackspaceStringCompare();

        check("brute force compares edited strings", solution.bruteForceBackspaceCompare("ab#c", "ad#c"), true);

        check("compares equal edited strings", solution.backspaceCompare("ab#c", "ad#c"), true);
        check("rejects different edited strings", solution.backspaceCompare("a#c", "b"), false);
        check("handles repeated backspaces", solution.backspaceCompare("ab##", "c#d#"), true);
        check("ignores excess backspaces", solution.backspaceCompare("a##c", "#a#c"), true);
    }
}

/*
 * Brute Force:
 * I build the final form of each string with a StringBuilder, removing the
 * latest retained character whenever a backspace appears, then compare them.
 *
 * Time Complexity: O(m + n), where m and n are the input lengths.
 * Space Complexity: O(m + n) for the two edited strings.
 *
 * Optimal Interview Solution:
 * I scan both strings backward. Each scan counts pending backspaces and skips
 * erased characters, allowing the next visible characters to be compared
 * without constructing edited strings.
 *
 * Time Complexity: O(m + n), because each input character is visited once.
 * Space Complexity: O(1), because only indices and backspace counts are stored.
 */
