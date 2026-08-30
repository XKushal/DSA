class DefangingAnIPAddress {
    public String bruteForceDefangIPaddr(String address) {
        StringBuilder defanged = new StringBuilder();

        for (int i = 0; i < address.length(); i++) {
            char current = address.charAt(i);

            if (current == '.') {
                defanged.append("[.]");
            } else {
                defanged.append(current);
            }
        }

        return defanged.toString();
    }

    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DefangingAnIPAddress solution = new DefangingAnIPAddress();

        check("brute force sample", solution.bruteForceDefangIPaddr("1.1.1.1"), "1[.]1[.]1[.]1");
        check("brute force mixed digits", solution.bruteForceDefangIPaddr("255.100.50.0"), "255[.]100[.]50[.]0");

        check("sample", solution.defangIPaddr("1.1.1.1"), "1[.]1[.]1[.]1");
        check("mixed digits", solution.defangIPaddr("255.100.50.0"), "255[.]100[.]50[.]0");
        check("zeros", solution.defangIPaddr("0.0.0.0"), "0[.]0[.]0[.]0");
    }
}

/*
 * Brute Force:
 * I scan every character and append the escaped text whenever the current
 * character is a dot.
 *
 * Time Complexity: O(n), because each character in the address is visited once.
 * Space Complexity: O(n), because the defanged address is stored separately.
 *
 * Optimal Interview Solution:
 * I use the built-in string replacement for the only character that needs to
 * change.
 *
 * Time Complexity: O(n), because the address must still be scanned to replace
 * each dot.
 * Space Complexity: O(n), because the returned string stores the defanged
 * address.
 */
