class CountAsterisks {
    public int bruteForceCountAsterisks(String s) {
        String[] parts = s.split("\\|", -1);
        int count = 0;

        for (int i = 0; i < parts.length; i += 2) {
            for (int j = 0; j < parts[i].length(); j++) {
                if (parts[i].charAt(j) == '*') {
                    count++;
                }
            }
        }

        return count;
    }

    public int countAsterisks(String s) {
        int count = 0;
        boolean insideBarPair = false;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '|') {
                insideBarPair = !insideBarPair;
            } else if (current == '*' && !insideBarPair) {
                count++;
            }
        }

        return count;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountAsterisks solution = new CountAsterisks();

        check("brute force sample one", solution.bruteForceCountAsterisks("l|*e*et|c**o|*de|"), 2);
        check("brute force sample two", solution.bruteForceCountAsterisks("iamprogrammer"), 0);

        check("sample one", solution.countAsterisks("l|*e*et|c**o|*de|"), 2);
        check("sample two", solution.countAsterisks("iamprogrammer"), 0);
        check("sample three", solution.countAsterisks("yo|uar|e**|b|e***au|tifu|l"), 5);
        check("all outside", solution.countAsterisks("***"), 3);
        check("all inside", solution.countAsterisks("|***|"), 0);
    }
}

/*
 * Brute Force:
 * I split the string around bars, then count asterisks only in the even-indexed
 * pieces that sit outside matched bar pairs.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(n), because split creates the pieces of the string.
 *
 * Optimal Interview Solution:
 * I scan the string once, toggle whether the current character is inside a bar
 * pair, and count asterisks only while outside those pairs.
 *
 * Time Complexity: O(n), where n is the length of the string.
 * Space Complexity: O(1), because only a counter and state flag are stored.
 */
