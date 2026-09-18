import java.util.Arrays;

class SortThePeople {
    public String[] bruteForceSortPeople(String[] names, int[] heights) {
        int n = names.length;
        String[] sorted = new String[n];
        boolean[] used = new boolean[n];

        for (int position = 0; position < n; position++) {
            int tallestIndex = -1;

            for (int i = 0; i < n; i++) {
                if (!used[i] && (tallestIndex == -1 || heights[i] > heights[tallestIndex])) {
                    tallestIndex = i;
                }
            }

            used[tallestIndex] = true;
            sorted[position] = names[tallestIndex];
        }

        return sorted;
    }

    public String[] sortPeople(String[] names, int[] heights) {
        Integer[] indexes = new Integer[names.length];

        for (int i = 0; i < names.length; i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, (left, right) -> heights[right] - heights[left]);

        String[] sorted = new String[names.length];
        for (int i = 0; i < indexes.length; i++) {
            sorted[i] = names[indexes[i]];
        }

        return sorted;
    }

    private static void check(String name, String[] actual, String[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(
                name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual)
            );
        }
    }

    public static void main(String[] args) {
        SortThePeople solution = new SortThePeople();

        check(
            "brute force sample one",
            solution.bruteForceSortPeople(new String[] {"Mary", "John", "Emma"}, new int[] {180, 165, 170}),
            new String[] {"Mary", "Emma", "John"}
        );
        check(
            "brute force sample two",
            solution.bruteForceSortPeople(new String[] {"Alice", "Bob", "Bob"}, new int[] {155, 185, 150}),
            new String[] {"Bob", "Alice", "Bob"}
        );

        check(
            "sample one",
            solution.sortPeople(new String[] {"Mary", "John", "Emma"}, new int[] {180, 165, 170}),
            new String[] {"Mary", "Emma", "John"}
        );
        check(
            "sample two",
            solution.sortPeople(new String[] {"Alice", "Bob", "Bob"}, new int[] {155, 185, 150}),
            new String[] {"Bob", "Alice", "Bob"}
        );
        check(
            "already sorted",
            solution.sortPeople(new String[] {"Kai", "Mia", "Noah"}, new int[] {190, 180, 170}),
            new String[] {"Kai", "Mia", "Noah"}
        );
        check(
            "single person",
            solution.sortPeople(new String[] {"Zoe"}, new int[] {160}),
            new String[] {"Zoe"}
        );
    }
}

/*
 * Brute Force:
 * I repeatedly find the tallest unused person and place that name in the next
 * output position.
 *
 * Time Complexity: O(n^2), where n is the number of people.
 * Space Complexity: O(n), because the output and used markers are stored.
 *
 * Optimal Interview Solution:
 * I sort indexes by their matching heights in descending order, then read the
 * names through those sorted indexes.
 *
 * Time Complexity: O(n log n), where n is the number of people.
 * Space Complexity: O(n), because the sorted indexes and output are stored.
 */
