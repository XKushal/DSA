class SameTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean bruteForceIsSameTree(TreeNode p, TreeNode q) {
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();

        serialize(p, first);
        serialize(q, second);

        return first.toString().equals(second.toString());
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private void serialize(TreeNode node, StringBuilder result) {
        if (node == null) {
            result.append("#,");
            return;
        }

        result.append(node.val).append(",");
        serialize(node.left, result);
        serialize(node.right, result);
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SameTree solution = new SameTree();

        TreeNode first = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode firstCopy = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode differentValue = new TreeNode(1, new TreeNode(2), new TreeNode(4));
        TreeNode differentShape = new TreeNode(1, new TreeNode(2), null);

        check("brute force matches equal trees", solution.bruteForceIsSameTree(first, firstCopy), true);

        check("matches equal trees", solution.isSameTree(first, firstCopy), true);
        check("rejects different values", solution.isSameTree(first, differentValue), false);
        check("rejects different shapes", solution.isSameTree(first, differentShape), false);
        check("matches two empty trees", solution.isSameTree(null, null), true);
        check("rejects one empty tree", solution.isSameTree(first, null), false);
    }
}

/*
 * Brute Force:
 * I serialize both trees with null markers, then compare the two serialized
 * forms. Matching serializations mean the values and structure are identical.
 *
 * Time Complexity: O(n + m), where n and m are the number of nodes in the two
 * trees, because both trees are fully serialized.
 * Space Complexity: O(n + m), because both serialized forms are stored.
 *
 * Optimal Interview Solution:
 * I compare the two trees directly with recursive paired traversal. At every
 * step the current nodes must both be empty, or both contain the same value and
 * have matching left and right subtrees.
 *
 * Time Complexity: O(n + m), because each reachable node pair is visited once.
 * Space Complexity: O(h), where h is the larger tree height used by the
 * recursion stack.
 */
