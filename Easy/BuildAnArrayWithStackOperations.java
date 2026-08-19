import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BuildAnArrayWithStackOperations {
    public List<String> bruteForceBuildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        int targetIndex = 0;

        for (int value = 1; value <= n && targetIndex < target.length; value++) {
            operations.add("Push");
            stack.add(value);

            if (stack.get(stack.size() - 1) == target[targetIndex]) {
                targetIndex++;
            } else {
                operations.add("Pop");
                stack.remove(stack.size() - 1);
            }
        }

        return operations;
    }

    public List<String> buildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();
        int current = 1;

        for (int value : target) {
            while (current < value) {
                operations.add("Push");
                operations.add("Pop");
                current++;
            }

            operations.add("Push");
            current++;
        }

        return operations;
    }

    private static void check(String name, List<String> actual, List<String> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static List<String> operations(String... values) {
        return Arrays.asList(values);
    }

    public static void main(String[] args) {
        BuildAnArrayWithStackOperations solution = new BuildAnArrayWithStackOperations();

        check("brute force skip middle", solution.bruteForceBuildArray(new int[] {1, 3}, 3),
            operations("Push", "Push", "Pop", "Push"));
        check("brute force consecutive", solution.bruteForceBuildArray(new int[] {1, 2, 3}, 3),
            operations("Push", "Push", "Push"));

        check("skip middle", solution.buildArray(new int[] {1, 3}, 3), operations("Push", "Push", "Pop", "Push"));
        check("consecutive values", solution.buildArray(new int[] {1, 2, 3}, 3), operations("Push", "Push", "Push"));
        check("single target after skips", solution.buildArray(new int[] {2}, 4), operations("Push", "Pop", "Push"));
        check("stop before n", solution.buildArray(new int[] {1, 2}, 4), operations("Push", "Push"));
    }
}

/*
 * Brute Force:
 * I simulate reading values from 1 through n with an actual stack. Each value is
 * pushed, and values that do not match the next target value are popped.
 *
 * Time Complexity: O(n), because values can be read from 1 through n.
 * Space Complexity: O(n), because the simulated stack and operations list grow
 * with the number of values read.
 *
 * Optimal Interview Solution:
 * I walk target directly and emit one Push/Pop pair for each skipped value, then
 * one Push for each target value. The stack itself does not need to be stored.
 *
 * Time Complexity: O(m), where m is the last target value read from the stream.
 * Space Complexity: O(m), because the returned operations list is proportional
 * to the number of read values.
 */
