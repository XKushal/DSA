import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MinimumIndexSumOfTwoLists {
    public String[] bruteForceFindRestaurant(String[] list1, String[] list2) {
        int bestSum = Integer.MAX_VALUE;
        int resultSize = 0;

        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    int indexSum = i + j;

                    if (indexSum < bestSum) {
                        bestSum = indexSum;
                        resultSize = 1;
                    } else if (indexSum == bestSum) {
                        resultSize++;
                    }
                }
            }
        }

        String[] result = new String[resultSize];
        int index = 0;

        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j]) && i + j == bestSum) {
                    result[index] = list1[i];
                    index++;
                }
            }
        }

        return result;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> firstListIndexes = new HashMap<>();

        for (int i = 0; i < list1.length; i++) {
            firstListIndexes.put(list1[i], i);
        }

        List<String> result = new ArrayList<>();
        int bestSum = Integer.MAX_VALUE;

        for (int j = 0; j < list2.length; j++) {
            Integer firstIndex = firstListIndexes.get(list2[j]);

            if (firstIndex == null) {
                continue;
            }

            int indexSum = firstIndex + j;

            if (indexSum < bestSum) {
                bestSum = indexSum;
                result.clear();
                result.add(list2[j]);
            } else if (indexSum == bestSum) {
                result.add(list2[j]);
            }
        }

        return result.toArray(new String[0]);
    }

    private static void check(String name, String[] actual, String[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        MinimumIndexSumOfTwoLists solution = new MinimumIndexSumOfTwoLists();

        check("brute force finds single favorite",
                solution.bruteForceFindRestaurant(
                        new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                        new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}),
                new String[] {"Shogun"});
        check("brute force keeps ties",
                solution.bruteForceFindRestaurant(
                        new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                        new String[] {"KFC", "Shogun", "Burger King"}),
                new String[] {"Shogun"});

        check("finds single favorite",
                solution.findRestaurant(
                        new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                        new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}),
                new String[] {"Shogun"});
        check("finds lower index sum",
                solution.findRestaurant(
                        new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                        new String[] {"KFC", "Shogun", "Burger King"}),
                new String[] {"Shogun"});
        check("keeps multiple best restaurants",
                solution.findRestaurant(
                        new String[] {"happy", "sad", "good"},
                        new String[] {"sad", "happy", "good"}),
                new String[] {"sad", "happy"});
    }
}

/*
 * Brute Force:
 * I compare every restaurant from the first list with every restaurant from the
 * second list, track the minimum matching index sum, then collect all matches
 * that have that sum.
 *
 * Time Complexity: O(m * n), because every pair of restaurants can be compared.
 * Space Complexity: O(k), where k is the number of restaurants tied for the
 * minimum index sum.
 *
 * Optimal Interview Solution:
 * I store each restaurant from the first list with its index in a hash map, then
 * scan the second list once to find shared restaurants with the smallest index
 * sum.
 *
 * Time Complexity: O(m + n), because each list is scanned once.
 * Space Complexity: O(m + k), where the map stores the first list and the result
 * stores the tied best restaurants.
 */
