import java.util.ArrayList;
import java.util.List;

class RemoveDuplicatesFromSortedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode bruteForceDeleteDuplicates(ListNode head) {
        List<Integer> values = new ArrayList<>();

        while (head != null) {
            if (values.isEmpty() || values.get(values.size() - 1) != head.val) {
                values.add(head.val);
            }
            head = head.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;

        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private static void check(String name, ListNode actual, int[] expected) {
        int index = 0;
        ListNode current = actual;

        while (current != null && index < expected.length) {
            if (current.val != expected[index]) {
                throw new AssertionError(name + " differed at index " + index);
            }
            current = current.next;
            index++;
        }

        if (current != null || index != expected.length) {
            throw new AssertionError(name + " had an unexpected length");
        }
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList solution = new RemoveDuplicatesFromSortedList();

        check(
                "brute force removes grouped duplicates",
                solution.bruteForceDeleteDuplicates(buildList(new int[] {1, 1, 2, 3, 3})),
                new int[] {1, 2, 3});
        check(
                "brute force handles all duplicates",
                solution.bruteForceDeleteDuplicates(buildList(new int[] {1, 1, 1})),
                new int[] {1});

        check(
                "removes grouped duplicates",
                solution.deleteDuplicates(buildList(new int[] {1, 1, 2, 3, 3})),
                new int[] {1, 2, 3});
        check(
                "handles no duplicates",
                solution.deleteDuplicates(buildList(new int[] {1, 2, 3})),
                new int[] {1, 2, 3});
        check(
                "handles all duplicates",
                solution.deleteDuplicates(buildList(new int[] {1, 1, 1})),
                new int[] {1});
        check(
                "handles empty list",
                solution.deleteDuplicates(buildList(new int[] {})),
                new int[] {});
    }
}

/*
 * Brute Force:
 * I scan the sorted list and collect only the first value from each duplicate
 * group, then build a new list from those unique values.
 *
 * Time Complexity: O(n), where n is the number of nodes in the list.
 * Space Complexity: O(n), because the unique values and rebuilt list are
 * stored separately.
 *
 * Optimal Interview Solution:
 * I walk the sorted list once and skip each duplicate node by relinking the
 * current node to the next new value.
 *
 * Time Complexity: O(n), where n is the number of nodes in the list.
 * Space Complexity: O(1), because the list is updated in place.
 */
