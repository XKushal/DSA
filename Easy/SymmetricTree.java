class SymmetricTree {
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

    public boolean bruteForceIsSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        StringBuilder leftMirror = new StringBuilder();
        StringBuilder rightNormal = new StringBuilder();

        serializeMirror(root.left, leftMirror);
        serializeNormal(root.right, rightNormal);

        return leftMirror.toString().equals(rightNormal.toString());
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    private void serializeMirror(TreeNode node, StringBuilder result) {
        if (node == null) {
            result.append("#,");
            return;
        }

        result.append(node.val).append(",");
        serializeMirror(node.right, result);
        serializeMirror(node.left, result);
    }

    private void serializeNormal(TreeNode node, StringBuilder result) {
        if (node == null) {
            result.append("#,");
            return;
        }

        result.append(node.val).append(",");
        serializeNormal(node.left, result);
        serializeNormal(node.right, result);
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SymmetricTree solution = new SymmetricTree();

        TreeNode symmetric = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        TreeNode differentShape = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3)));
        TreeNode differentValue = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(5)));

        check("brute force accepts mirror tree", solution.bruteForceIsSymmetric(symmetric), true);

        check("accepts mirror tree", solution.isSymmetric(symmetric), true);
        check("rejects different shape", solution.isSymmetric(differentShape), false);
        check("rejects different value", solution.isSymmetric(differentValue), false);
        check("accepts empty tree", solution.isSymmetric(null), true);
        check("accepts single node", solution.isSymmetric(new TreeNode(1)), true);
    }
}

/*
 * Brute Force:
 * I serialize the left subtree in mirrored order and the right subtree in
 * normal order, keeping null markers so structure differences are preserved.
 *
 * Time Complexity: O(n), where n is the number of nodes in the tree.
 * Space Complexity: O(n), because both serialized forms are stored.
 *
 * Optimal Interview Solution:
 * I compare matching mirrored node pairs directly. Each left child must match
 * the opposite right child, and each right child must match the opposite left
 * child.
 *
 * Time Complexity: O(n), because each node is compared once.
 * Space Complexity: O(h), where h is the height of the recursion stack.
 */
