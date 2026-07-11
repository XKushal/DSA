import java.util.HashSet;
import java.util.Set;

class LinkedListCycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public boolean bruteForceHasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();

        while (head != null) {
            if (seen.contains(head)) {
                return true;
            }
            seen.add(head);
            head = head.next;
        }

        return false;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
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

    private static ListNode nodeAt(ListNode head, int index) {
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            head = head.next;
        }

        return head;
    }

    private static ListNode withCycle(int[] values, int cycleIndex) {
        ListNode head = buildList(values);
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
        }

        tail.next = nodeAt(head, cycleIndex);
        return head;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        check("brute force detects cycle", solution.bruteForceHasCycle(withCycle(new int[] {3, 2, 0, -4}, 1)), true);
        check("brute force rejects acyclic list", solution.bruteForceHasCycle(buildList(new int[] {1, 2})), false);

        check("detects cycle", solution.hasCycle(withCycle(new int[] {3, 2, 0, -4}, 1)), true);
        check("detects short cycle", solution.hasCycle(withCycle(new int[] {1, 2}, 0)), true);
        check("rejects acyclic list", solution.hasCycle(buildList(new int[] {1, 2, 3, 4})), false);
        check("rejects single node", solution.hasCycle(new ListNode(1)), false);
        check("rejects empty list", solution.hasCycle(null), false);
    }
}

/*
 * Brute Force:
 * I keep every visited node in a HashSet. Visiting the same node twice means
 * the list has a cycle.
 *
 * Time Complexity: O(n), where n is the number of reachable nodes before a
 * repeat or the end of the list.
 * Space Complexity: O(n), because the set can store each visited node once.
 *
 * Optimal Interview Solution:
 * I use Floyd's tortoise and hare pointers. The fast pointer eventually meets
 * the slow pointer when a cycle exists; otherwise it reaches the end.
 *
 * Time Complexity: O(n), where n is the number of reachable nodes.
 * Space Complexity: O(1), because only two pointers are used.
 */
