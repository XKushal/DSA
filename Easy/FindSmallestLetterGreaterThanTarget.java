class FindSmallestLetterGreaterThanTarget {
    public char bruteForceNextGreatestLetter(char[] letters, char target) {
        char answer = letters[0];

        for (char letter : letters) {
            if (letter > target) {
                return letter;
            }
        }

        return answer;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return letters[left % letters.length];
    }

    private static void check(String name, char actual, char expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindSmallestLetterGreaterThanTarget solution = new FindSmallestLetterGreaterThanTarget();

        check("brute force finds next letter", solution.bruteForceNextGreatestLetter(new char[] {'c', 'f', 'j'}, 'a'), 'c');
        check("brute force skips equal letters", solution.bruteForceNextGreatestLetter(new char[] {'c', 'f', 'j'}, 'c'), 'f');
        check("brute force wraps around", solution.bruteForceNextGreatestLetter(new char[] {'c', 'f', 'j'}, 'j'), 'c');

        check("finds next letter", solution.nextGreatestLetter(new char[] {'c', 'f', 'j'}, 'a'), 'c');
        check("skips equal letters", solution.nextGreatestLetter(new char[] {'c', 'f', 'j'}, 'c'), 'f');
        check("handles duplicate candidates", solution.nextGreatestLetter(new char[] {'e', 'e', 'g', 'g'}, 'e'), 'g');
        check("wraps around after last letter", solution.nextGreatestLetter(new char[] {'c', 'f', 'j'}, 'j'), 'c');
        check("wraps around after larger target", solution.nextGreatestLetter(new char[] {'x', 'x', 'y', 'y'}, 'z'), 'x');
    }
}

/*
 * Brute Force:
 * I scan the sorted letters from left to right and return the first letter
 * that is greater than the target, wrapping to the first letter if none match.
 *
 * Time Complexity: O(n), because the array may be scanned once.
 * Space Complexity: O(1), because only the current answer is stored.
 *
 * Optimal Interview Solution:
 * I use binary search to find the first index whose letter is greater than the
 * target, then use modulo to wrap around when the insertion point reaches the
 * end of the array.
 *
 * Time Complexity: O(log n), because each search step halves the range.
 * Space Complexity: O(1), because only binary-search pointers are stored.
 */
