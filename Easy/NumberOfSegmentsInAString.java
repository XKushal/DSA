class NumberOfSegmentsInAString {
    public int bruteForceCountSegments(String s) {
        String trimmed = s.trim();

        if (trimmed.isEmpty()) {
            return 0;
        }

        return trimmed.split("\\s+").length;
    }

    public int countSegments(String s) {
        int segments = 0;

        for (int index = 0; index < s.length(); index++) {
            boolean currentIsLetter = s.charAt(index) != ' ';
            boolean startsSegment = index == 0 || s.charAt(index - 1) == ' ';

            if (currentIsLetter && startsSegment) {
                segments++;
            }
        }

        return segments;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NumberOfSegmentsInAString solution = new NumberOfSegmentsInAString();

        check("brute force counts comma separated text",
                solution.bruteForceCountSegments("Hello, my name is John"), 5);
        check("brute force handles only spaces", solution.bruteForceCountSegments("   "), 0);

        check("counts comma separated text", solution.countSegments("Hello, my name is John"), 5);
        check("counts no segments in spaces", solution.countSegments("    "), 0);
        check("counts one compact segment", solution.countSegments("leetcode"), 1);
        check("skips repeated spaces", solution.countSegments("love live! mu'sic forever"), 4);
        check("handles leading and trailing spaces", solution.countSegments("  a  b c  "), 3);
        check("handles empty string", solution.countSegments(""), 0);
    }
}

/*
 * Brute Force:
 * I trim leading and trailing spaces, then split the remaining text around one
 * or more spaces. Each token in the split result is a segment.
 *
 * Time Complexity: O(n), where n is the string length, because trim and split
 * scan the text.
 * Space Complexity: O(n), because splitting can allocate an array of segments.
 *
 * Optimal Interview Solution:
 * I scan once and count a segment whenever a non-space character appears at the
 * start of the string or immediately after a space.
 *
 * Time Complexity: O(n), because each character is checked once.
 * Space Complexity: O(1), because only a counter and booleans are stored.
 */
