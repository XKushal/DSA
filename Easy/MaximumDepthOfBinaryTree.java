import java.util.ArrayDeque;
import java.util.Queue;

class MaximumDepthOfBinaryTree {
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

    public int bruteForceMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        int depth = 0;

        while (!nodes.isEmpty()) {
            int levelSize = nodes.size();

            for (int count = 0; count < levelSize; count++) {
                TreeNode current = nodes.poll();

                if (current.left != null) {
                    nodes.offer(current.left);
                }
                if (current.right != null) {
                    nodes.offer(current.right);
                }
            }

            depth++;
        }

        return depth;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();

        TreeNode sample = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode skewed = new TreeNode(1,
                null,
                new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));

        check("brute force handles balanced tree", solution.bruteForceMaxDepth(sample), 3);

        check("handles balanced tree", solution.maxDepth(sample), 3);
        check("handles single node", solution.maxDepth(new TreeNode(1)), 1);
        check("handles empty tree", solution.maxDepth(null), 0);
        check("handles skewed tree", solution.maxDepth(skewed), 4);
    }
}

/*
 * Brute Force:
 * I scan the tree one level at a time and count how many levels are present
 * before the traversal is exhausted.
 *
 * Time Complexity: O(n), where n is the number of nodes in the tree.
 * Space Complexity: O(w), where w is the maximum number of nodes on any level.
 *
 * Optimal Interview Solution:
 * I use depth-first recursion. Each node contributes one level above the deeper
 * of its two child subtrees.
 *
 * Time Complexity: O(n), where n is the number of nodes in the tree.
 * Space Complexity: O(h), where h is the height of the recursion stack.
 */
