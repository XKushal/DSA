class NumberComplement {
    public int bruteForceFindComplement(int num) {
        String binary = Integer.toBinaryString(num);
        StringBuilder complement = new StringBuilder();

        for (int index = 0; index < binary.length(); index++) {
            complement.append(binary.charAt(index) == '0' ? '1' : '0');
        }

        return Integer.parseInt(complement.toString(), 2);
    }

    public int findComplement(int num) {
        int mask = 0;
        int remaining = num;

        while (remaining > 0) {
            mask = (mask << 1) | 1;
            remaining >>= 1;
        }

        return mask ^ num;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NumberComplement solution = new NumberComplement();

        check("brute force flips each binary digit", solution.bruteForceFindComplement(5), 2);
        check("brute force handles all ones", solution.bruteForceFindComplement(7), 0);

        check("flips each binary digit", solution.findComplement(5), 2);
        check("handles one", solution.findComplement(1), 0);
        check("handles all ones", solution.findComplement(7), 0);
        check("handles alternating bits", solution.findComplement(10), 5);
        check("handles higher bit length", solution.findComplement(50), 13);
    }
}

/*
 * Brute Force:
 * I convert the number to its binary string, flip each character, then parse
 * the flipped string back into an integer.
 *
 * Time Complexity: O(b), where b is the number of bits in num.
 * Space Complexity: O(b), because the binary string and flipped string store
 * every bit.
 *
 * Optimal Interview Solution:
 * I build a mask with the same number of bits as num, then XOR that mask with
 * num so every relevant bit is flipped.
 *
 * Time Complexity: O(b), where b is the number of bits in num.
 * Space Complexity: O(1), because only integer counters and masks are stored.
 */
