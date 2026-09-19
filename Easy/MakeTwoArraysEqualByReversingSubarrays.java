import java.util.Arrays;

class MakeTwoArraysEqualByReversingSubarrays {
    public boolean bruteForceCanBeEqual(int[] target, int[] arr) {
        int[] current = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < target.length; i++) {
            int matchIndex = -1;

            for (int j = i; j < current.length; j++) {
                if (current[j] == target[i]) {
                    matchIndex = j;
                    break;
                }
            }

            if (matchIndex == -1) {
                return false;
            }

            reverse(current, i, matchIndex);
        }

        return true;
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        int[] frequencies = new int[1001];

        for (int value : target) {
            frequencies[value]++;
        }

        for (int value : arr) {
            frequencies[value]--;
        }

        for (int frequency : frequencies) {
            if (frequency != 0) {
                return false;
            }
        }

        return true;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MakeTwoArraysEqualByReversingSubarrays solution = new MakeTwoArraysEqualByReversingSubarrays();

        check("brute force sample one", solution.bruteForceCanBeEqual(
                new int[] {1, 2, 3, 4}, new int[] {2, 4, 1, 3}), true);
        check("brute force sample two", solution.bruteForceCanBeEqual(
                new int[] {7}, new int[] {7}), true);
        check("brute force sample three", solution.bruteForceCanBeEqual(
                new int[] {3, 7, 9}, new int[] {3, 7, 11}), false);

        check("sample one", solution.canBeEqual(new int[] {1, 2, 3, 4}, new int[] {2, 4, 1, 3}), true);
        check("sample two", solution.canBeEqual(new int[] {7}, new int[] {7}), true);
        check("sample three", solution.canBeEqual(new int[] {3, 7, 9}, new int[] {3, 7, 11}), false);
        check("duplicates match", solution.canBeEqual(new int[] {1, 2, 2, 3}, new int[] {2, 3, 2, 1}), true);
        check("duplicates differ", solution.canBeEqual(new int[] {1, 2, 2, 3}, new int[] {1, 1, 2, 3}), false);
    }
}

/*
 * Brute Force:
 * I make a working copy of arr and, for each target position, find a matching
 * value later in the copy. Reversing that subarray moves the match into place.
 *
 * Time Complexity: O(n^2), where n is the array length.
 * Space Complexity: O(n), because the working copy is stored.
 *
 * Optimal Interview Solution:
 * Since reversing length-two subarrays can swap adjacent values, any
 * permutation with the same multiset of values can be formed. I compare value
 * frequencies across both arrays.
 *
 * Time Complexity: O(n + m), where n is the array length and m is the bounded
 * value range 1 through 1000.
 * Space Complexity: O(m), because value frequencies are stored.
 */
