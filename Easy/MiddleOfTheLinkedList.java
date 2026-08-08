class MiddleOfTheLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode bruteForceMiddleNode(ListNode head) {
        int length = 0;
        ListNode current = head;

        while (current != null) {
            length++;
            current = current.next;
        }

        current = head;
        for (int i = 0; i < length / 2; i++) {
            current = current.next;
        }

        return current;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private static void check(String name, ListNode actual, int expected) {
        if (actual == null || actual.val != expected) {
            throw new AssertionError(name + " expected middle value " + expected);
        }
    }

    public static void main(String[] args) {
        MiddleOfTheLinkedList solution = new MiddleOfTheLinkedList();

        check("brute force handles odd length", solution.bruteForceMiddleNode(buildList(1, 2, 3, 4, 5)), 3);
        check("brute force handles even length", solution.bruteForceMiddleNode(buildList(1, 2, 3, 4, 5, 6)), 4);
        check("brute force handles single node", solution.bruteForceMiddleNode(buildList(1)), 1);

        check("handles odd length", solution.middleNode(buildList(1, 2, 3, 4, 5)), 3);
        check("handles even length", solution.middleNode(buildList(1, 2, 3, 4, 5, 6)), 4);
        check("handles two nodes", solution.middleNode(buildList(1, 2)), 2);
        check("handles single node", solution.middleNode(buildList(1)), 1);
    }
}

/*
 * Brute Force:
 * I count the list length first, then walk length / 2 steps from the head to
 * return the second middle node when the list has an even length.
 *
 * Time Complexity: O(n), because the list is scanned twice.
 * Space Complexity: O(1), because only a few pointers and counters are used.
 *
 * Optimal Interview Solution:
 * I move a slow pointer one step and a fast pointer two steps. When the fast
 * pointer reaches the end, the slow pointer is at the required middle node.
 *
 * Time Complexity: O(n), because each pointer traverses at most the list once.
 * Space Complexity: O(1), because only two pointers are used.
 */
