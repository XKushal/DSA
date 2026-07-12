import java.util.Arrays;

class AssignCookies {
    public int bruteForceFindContentChildren(int[] greed, int[] cookies) {
        boolean[] used = new boolean[cookies.length];
        return findBestAssignment(greed, cookies, used, 0);
    }

    public int findContentChildren(int[] greed, int[] cookies) {
        int[] sortedGreed = Arrays.copyOf(greed, greed.length);
        int[] sortedCookies = Arrays.copyOf(cookies, cookies.length);
        Arrays.sort(sortedGreed);
        Arrays.sort(sortedCookies);

        int child = 0;
        int cookie = 0;

        while (child < sortedGreed.length && cookie < sortedCookies.length) {
            if (sortedCookies[cookie] >= sortedGreed[child]) {
                child++;
            }
            cookie++;
        }

        return child;
    }

    private int findBestAssignment(int[] greed, int[] cookies, boolean[] used, int childIndex) {
        if (childIndex == greed.length) {
            return 0;
        }

        int best = findBestAssignment(greed, cookies, used, childIndex + 1);

        for (int cookieIndex = 0; cookieIndex < cookies.length; cookieIndex++) {
            if (!used[cookieIndex] && cookies[cookieIndex] >= greed[childIndex]) {
                used[cookieIndex] = true;
                best = Math.max(best, 1 + findBestAssignment(greed, cookies, used, childIndex + 1));
                used[cookieIndex] = false;
            }
        }

        return best;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        AssignCookies solution = new AssignCookies();

        check("brute force maximizes assignments",
                solution.bruteForceFindContentChildren(new int[] {1, 2, 3}, new int[] {1, 1}), 1);

        check("assigns one child", solution.findContentChildren(new int[] {1, 2, 3}, new int[] {1, 1}), 1);
        check("assigns both children", solution.findContentChildren(new int[] {1, 2}, new int[] {1, 2, 3}), 2);
        check("handles no cookies", solution.findContentChildren(new int[] {1, 2}, new int[] {}), 0);
        check("uses larger cookies carefully", solution.findContentChildren(new int[] {10, 9, 8, 7}, new int[] {5, 6, 7, 8}), 2);
        check("handles extra small cookies", solution.findContentChildren(new int[] {2, 3}, new int[] {1, 1, 2}), 1);
    }
}

/*
 * Brute Force:
 * I try assigning each usable unused cookie to the current child, and I also
 * try skipping that child, then keep the best result across all choices.
 *
 * Time Complexity: O((m + 1)^n * m), where n is the number of children and m
 * is the number of cookies, because each child can branch across unused cookies
 * or be skipped, and each recursive state scans the cookies.
 * Space Complexity: O(n + m), because recursion can use one stack frame per
 * child and the used array tracks cookies.
 *
 * Optimal Interview Solution:
 * I sort both arrays and give the smallest cookie that can satisfy the least
 * greedy remaining child. If a cookie is too small, it cannot satisfy any later
 * child, so it is skipped.
 *
 * Time Complexity: O(n log n + m log m), where n is the number of children and
 * m is the number of cookies, because both arrays are sorted.
 * Space Complexity: O(n + m), because the input arrays are copied before
 * sorting.
 */
