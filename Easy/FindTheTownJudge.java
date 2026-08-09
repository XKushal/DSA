class FindTheTownJudge {
    public int bruteForceFindJudge(int n, int[][] trust) {
        for (int person = 1; person <= n; person++) {
            if (isTrustedByEveryoneElse(person, n, trust) && trustsNobody(person, trust)) {
                return person;
            }
        }

        return -1;
    }

    private boolean isTrustedByEveryoneElse(int candidate, int n, int[][] trust) {
        for (int person = 1; person <= n; person++) {
            if (person == candidate) {
                continue;
            }

            boolean trustsCandidate = false;
            for (int[] relation : trust) {
                if (relation[0] == person && relation[1] == candidate) {
                    trustsCandidate = true;
                    break;
                }
            }

            if (!trustsCandidate) {
                return false;
            }
        }

        return true;
    }

    private boolean trustsNobody(int candidate, int[][] trust) {
        for (int[] relation : trust) {
            if (relation[0] == candidate) {
                return false;
            }
        }

        return true;
    }

    public int findJudge(int n, int[][] trust) {
        int[] scores = new int[n + 1];

        for (int[] relation : trust) {
            scores[relation[0]]--;
            scores[relation[1]]++;
        }

        for (int person = 1; person <= n; person++) {
            if (scores[person] == n - 1) {
                return person;
            }
        }

        return -1;
    }

    private static int[][] relations(int[]... pairs) {
        return pairs;
    }

    private static int[] pair(int from, int to) {
        return new int[] {from, to};
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindTheTownJudge solution = new FindTheTownJudge();

        check("brute force finds judge",
                solution.bruteForceFindJudge(2, relations(pair(1, 2))), 2);
        check("brute force rejects cycles",
                solution.bruteForceFindJudge(3, relations(pair(1, 3), pair(2, 3), pair(3, 1))), -1);
        check("brute force handles single person",
                solution.bruteForceFindJudge(1, relations()), 1);

        check("finds judge", solution.findJudge(2, relations(pair(1, 2))), 2);
        check("finds judge with multiple people",
                solution.findJudge(3, relations(pair(1, 3), pair(2, 3))), 3);
        check("rejects missing universal trust",
                solution.findJudge(3, relations(pair(1, 3), pair(2, 3), pair(3, 1))), -1);
        check("handles single person", solution.findJudge(1, relations()), 1);
    }
}

/*
 * Brute Force:
 * I test each person as the judge candidate. For every candidate, I scan the
 * trust list to confirm that every other person trusts them and that the
 * candidate does not trust anyone.
 *
 * Time Complexity: O(n * (n + t)), where n is the number of people and t is
 * the number of trust relationships.
 * Space Complexity: O(1), because only loop variables are stored.
 *
 * Optimal Interview Solution:
 * I score each person by subtracting one for every outgoing trust relation and
 * adding one for every incoming trust relation. The judge is the only person
 * whose final score can be n - 1.
 *
 * Time Complexity: O(n + t), because the trust list and the people are each
 * scanned once.
 * Space Complexity: O(n), because one score is stored for each person.
 */
