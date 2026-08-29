class GoalParserInterpretation {
    public String bruteForceInterpret(String command) {
        StringBuilder answer = new StringBuilder();
        int i = 0;

        while (i < command.length()) {
            if (command.startsWith("()", i)) {
                answer.append('o');
                i += 2;
            } else if (command.startsWith("(al)", i)) {
                answer.append("al");
                i += 4;
            } else {
                answer.append(command.charAt(i));
                i++;
            }
        }

        return answer.toString();
    }

    public String interpret(String command) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < command.length(); i++) {
            char current = command.charAt(i);

            if (current == 'G') {
                answer.append('G');
            } else if (command.charAt(i + 1) == ')') {
                answer.append('o');
                i++;
            } else {
                answer.append("al");
                i += 3;
            }
        }

        return answer.toString();
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        GoalParserInterpretation solution = new GoalParserInterpretation();

        check("brute force sample", solution.bruteForceInterpret("G()(al)"), "Goal");
        check("brute force repeated tokens", solution.bruteForceInterpret("G()()()()(al)"), "Gooooal");

        check("sample", solution.interpret("G()(al)"), "Goal");
        check("repeated tokens", solution.interpret("G()()()()(al)"), "Gooooal");
        check("mixed order", solution.interpret("(al)G(al)()()G"), "alGalooG");
    }
}

/*
 * Brute Force:
 * I scan the command and test every possible token at the current index before
 * deciding which text to append.
 *
 * Time Complexity: O(n), because each command character is consumed once.
 * Space Complexity: O(n), because the interpreted answer is stored.
 *
 * Optimal Interview Solution:
 * I use the fixed command grammar directly: G stays G, () becomes o, and the
 * remaining token must be (al).
 *
 * Time Complexity: O(n), because each command character is visited once.
 * Space Complexity: O(n), because the interpreted answer is stored.
 */
