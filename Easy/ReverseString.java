import java.util.Arrays;

class ReverseString {
    public void bruteForceReverseString(char[] s) {
        char[] reversed = new char[s.length];

        for (int index = 0; index < s.length; index++) {
            reversed[index] = s[s.length - 1 - index];
        }

        for (int index = 0; index < s.length; index++) {
            s[index] = reversed[index];
        }
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }

    private static void check(String name, char[] actual, char[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        ReverseString solution = new ReverseString();

        char[] bruteForceEven = {'h', 'e', 'l', 'l', 'o'};
        solution.bruteForceReverseString(bruteForceEven);
        check("brute force reverses odd length word", bruteForceEven, new char[] {'o', 'l', 'l', 'e', 'h'});

        char[] optimalEven = {'H', 'a', 'n', 'n', 'a', 'h'};
        solution.reverseString(optimalEven);
        check("reverses even length word", optimalEven, new char[] {'h', 'a', 'n', 'n', 'a', 'H'});

        char[] optimalSingle = {'a'};
        solution.reverseString(optimalSingle);
        check("keeps single character word", optimalSingle, new char[] {'a'});
    }
}

/*
 * Brute Force:
 * I copy the characters into a second array in reverse order, then copy that
 * reversed order back into the input array.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(n), because the extra array stores every character.
 *
 * Optimal Interview Solution:
 * I keep one pointer at each end of the array and swap those characters while
 * moving both pointers toward the middle.
 *
 * Time Complexity: O(n), where n is the length of s.
 * Space Complexity: O(1), because the reversal happens in place.
 */
