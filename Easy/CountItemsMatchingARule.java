import java.util.Arrays;
import java.util.List;

class CountItemsMatchingARule {
    public int bruteForceCountMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int matches = 0;

        for (List<String> item : items) {
            boolean matchesRule = false;

            if (ruleKey.equals("type") && item.get(0).equals(ruleValue)) {
                matchesRule = true;
            } else if (ruleKey.equals("color") && item.get(1).equals(ruleValue)) {
                matchesRule = true;
            } else if (ruleKey.equals("name") && item.get(2).equals(ruleValue)) {
                matchesRule = true;
            }

            if (matchesRule) {
                matches++;
            }
        }

        return matches;
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ruleIndex = getRuleIndex(ruleKey);
        int matches = 0;

        for (List<String> item : items) {
            if (item.get(ruleIndex).equals(ruleValue)) {
                matches++;
            }
        }

        return matches;
    }

    private int getRuleIndex(String ruleKey) {
        if (ruleKey.equals("type")) {
            return 0;
        }
        if (ruleKey.equals("color")) {
            return 1;
        }
        return 2;
    }

    private static List<List<String>> items(String[][] values) {
        List<List<String>> result = new java.util.ArrayList<>();

        for (String[] value : values) {
            result.add(Arrays.asList(value));
        }

        return result;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountItemsMatchingARule solution = new CountItemsMatchingARule();
        List<List<String>> firstItems = items(new String[][] {
            {"phone", "blue", "pixel"},
            {"computer", "silver", "lenovo"},
            {"phone", "gold", "iphone"}
        });
        List<List<String>> secondItems = items(new String[][] {
            {"phone", "blue", "pixel"},
            {"computer", "silver", "phone"},
            {"phone", "gold", "iphone"}
        });

        check("brute force type", solution.bruteForceCountMatches(firstItems, "type", "phone"), 2);
        check("brute force color", solution.bruteForceCountMatches(firstItems, "color", "silver"), 1);

        check("type", solution.countMatches(firstItems, "type", "phone"), 2);
        check("color", solution.countMatches(firstItems, "color", "silver"), 1);
        check("name", solution.countMatches(secondItems, "name", "phone"), 1);
        check("missing", solution.countMatches(secondItems, "color", "black"), 0);
    }
}

/*
 * Brute Force:
 * I check every possible rule key inside the loop and count the item when the
 * matching field equals the requested value.
 *
 * Time Complexity: O(n), because each item is inspected once.
 * Space Complexity: O(1), because only counters and flags are stored.
 *
 * Optimal Interview Solution:
 * I convert the rule key to its item index once, then compare only that field
 * for every item.
 *
 * Time Complexity: O(n), because every item is checked once.
 * Space Complexity: O(1), because the scan uses constant extra storage.
 */
