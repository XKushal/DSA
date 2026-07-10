import java.util.ArrayList;
import java.util.List;

class PathSum {
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

    public boolean bruteForceHasPathSum(TreeNode root, int targetSum) {
        List<Integer> pathSums = new ArrayList<>();
        collectLeafSums(root, 0, pathSums);

        for (int sum : pathSums) {
            if (sum == targetSum) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        int remaining = targetSum - root.val;
        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
    }

    private void collectLeafSums(TreeNode node, int currentSum, List<Integer> pathSums) {
        if (node == null) {
            return;
        }

        int nextSum = currentSum + node.val;
        if (node.left == null && node.right == null) {
            pathSums.add(nextSum);
            return;
        }

        collectLeafSums(node.left, nextSum, pathSums);
        collectLeafSums(node.right, nextSum, pathSums);
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        PathSum solution = new PathSum();

        TreeNode sample = new TreeNode(5,
                new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
                new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
        TreeNode negativeValues = new TreeNode(-2, null, new TreeNode(-3));

        check("brute force finds sample path", solution.bruteForceHasPathSum(sample, 22), true);

        check("finds sample path", solution.hasPathSum(sample, 22), true);
        check("rejects missing path", solution.hasPathSum(sample, 24), false);
        check("handles negative values", solution.hasPathSum(negativeValues, -5), true);
        check("rejects empty tree", solution.hasPathSum(null, 0), false);
        check("handles single matching node", solution.hasPathSum(new TreeNode(1), 1), true);
        check("handles single non-matching node", solution.hasPathSum(new TreeNode(1), 2), false);
    }
}

/*
 * Brute Force:
 * I collect every root-to-leaf path sum in a list, then scan that list for the
 * requested target.
 *
 * Time Complexity: O(n), where n is the number of nodes in the tree.
 * Space Complexity: O(l + h), where l is the number of leaves stored and h is
 * the recursion stack height.
 *
 * Optimal Interview Solution:
 * I subtract each node value from the target while walking down the tree. A
 * leaf is valid only when its value equals the remaining target.
 *
 * Time Complexity: O(n), because each node is visited once.
 * Space Complexity: O(h), where h is the height of the recursion stack.
 */
