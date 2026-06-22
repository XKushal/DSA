import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MergeTwoSortedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode bruteForceMergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> values = new ArrayList<>();

        while (list1 != null) {
            values.add(list1.val);
            list1 = list1.next;
        }

        while (list2 != null) {
            values.add(list2.val);
            list2 = list2.next;
        }

        Collections.sort(values);

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
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
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        check(
                "brute force merges interleaved lists",
                solution.bruteForceMergeTwoLists(buildList(new int[] {1, 2, 4}), buildList(new int[] {1, 3, 4})),
                new int[] {1, 1, 2, 3, 4, 4});
        check(
                "brute force handles an empty list",
                solution.bruteForceMergeTwoLists(buildList(new int[] {}), buildList(new int[] {0})),
                new int[] {0});

        check(
                "merges interleaved lists",
                solution.mergeTwoLists(buildList(new int[] {1, 2, 4}), buildList(new int[] {1, 3, 4})),
                new int[] {1, 1, 2, 3, 4, 4});
        check(
                "handles an empty first list",
                solution.mergeTwoLists(buildList(new int[] {}), buildList(new int[] {0})),
                new int[] {0});
        check(
                "handles both lists empty",
                solution.mergeTwoLists(buildList(new int[] {}), buildList(new int[] {})),
                new int[] {});
    }
}

/*
 * Brute Force:
 * I collect every value from both lists into an array list, sort those values,
 * then build a new linked list from the sorted sequence.
 *
 * Time Complexity: O((n + m) log(n + m)), where n and m are the lengths of the
 * two lists.
 * Space Complexity: O(n + m), because the collected values and rebuilt list are
 * both stored.
 *
 * Optimal Interview Solution:
 * I keep one pointer in each already-sorted list and repeatedly link the
 * smaller current node to the merged list, then attach the remaining tail.
 *
 * Time Complexity: O(n + m), where n and m are the lengths of the two lists.
 * Space Complexity: O(1), because the existing list nodes are relinked in
 * place apart from the dummy head.
 */
