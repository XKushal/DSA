class BestTimeToBuyAndSellStock {
    public int bruteForceMaxProfit(int[] prices) {
        int bestProfit = 0;

        for (int buy = 0; buy < prices.length; buy++) {
            for (int sell = buy + 1; sell < prices.length; sell++) {
                bestProfit = Math.max(bestProfit, prices[sell] - prices[buy]);
            }
        }

        return bestProfit;
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int bestProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                bestProfit = Math.max(bestProfit, price - minPrice);
            }
        }

        return bestProfit;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();

        check("brute force finds the best later sale", solution.bruteForceMaxProfit(new int[] {7, 1, 5, 3, 6, 4}), 5);
        check("brute force returns zero when prices only go down", solution.bruteForceMaxProfit(new int[] {7, 6, 4, 3, 1}), 0);

        check("profit when a later day has the best sale", solution.maxProfit(new int[] {7, 1, 5, 3, 6, 4}), 5);
        check("no profit when prices only go down", solution.maxProfit(new int[] {7, 6, 4, 3, 1}), 0);
        check("profit across the full window", solution.maxProfit(new int[] {1, 2, 3, 4, 5}), 4);
    }
}

/*
 * Brute Force:
 * I try every possible buy day and every later sell day, then keep the best
 * profit from those pairs.
 *
 * Time Complexity: O(n^2), where n is the number of prices.
 * Space Complexity: O(1), because no extra data structure is needed.
 *
 * Optimal Interview Solution:
 * I keep the cheapest price seen so far as the buy day and compare every later
 * price as a possible sell day. The answer is the best difference found while
 * scanning left to right, so the buy day always comes before the sell day.
 *
 * Time Complexity: O(n), where n is the number of prices.
 * Space Complexity: O(1), because only two integer variables are used.
 */
