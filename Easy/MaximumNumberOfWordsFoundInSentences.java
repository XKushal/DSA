class MaximumNumberOfWordsFoundInSentences {
    public int bruteForceMostWordsFound(String[] sentences) {
        int maximumWords = 0;

        for (String sentence : sentences) {
            maximumWords = Math.max(maximumWords, sentence.split(" ").length);
        }

        return maximumWords;
    }

    public int mostWordsFound(String[] sentences) {
        int maximumWords = 0;

        for (String sentence : sentences) {
            int words = 1;

            for (int i = 0; i < sentence.length(); i++) {
                if (sentence.charAt(i) == ' ') {
                    words++;
                }
            }

            maximumWords = Math.max(maximumWords, words);
        }

        return maximumWords;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumNumberOfWordsFoundInSentences solution = new MaximumNumberOfWordsFoundInSentences();

        check("brute force sample", solution.bruteForceMostWordsFound(
            new String[] {"alice and bob love leetcode", "i think so too", "this is great thanks very much"}
        ), 6);
        check("brute force short", solution.bruteForceMostWordsFound(
            new String[] {"please wait", "continue to fight", "continue to win"}
        ), 3);

        check("sample", solution.mostWordsFound(
            new String[] {"alice and bob love leetcode", "i think so too", "this is great thanks very much"}
        ), 6);
        check("short", solution.mostWordsFound(
            new String[] {"please wait", "continue to fight", "continue to win"}
        ), 3);
        check("single word", solution.mostWordsFound(new String[] {"hello"}), 1);
    }
}

/*
 * Brute Force:
 * I split each sentence into words and keep the largest resulting word count.
 *
 * Time Complexity: O(n * m), where n is the number of sentences and m is the
 * average sentence length.
 * Space Complexity: O(m), because splitting a sentence stores its words.
 *
 * Optimal Interview Solution:
 * I count spaces in each sentence directly. Every valid sentence has one more
 * word than its number of spaces.
 *
 * Time Complexity: O(n * m), because every character is inspected once.
 * Space Complexity: O(1), because only counters are stored.
 */
