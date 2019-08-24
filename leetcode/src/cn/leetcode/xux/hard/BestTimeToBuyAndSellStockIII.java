package cn.leetcode.xux.hard;

import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * Example 1:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0) {
            return 0;
        }
        int len = prices.length;
        int[][] sold = new int[len][3];
        int[][] hold = new int[len][2];
        Arrays.fill(hold[0], -prices[0]);
        for(int i=1;i<len;i++) {
            sold[i][0] = sold[i-1][0];
            sold[i][1] = Math.max(sold[i-1][1], hold[i-1][0] + prices[i]);
            sold[i][2] = Math.max(sold[i-1][2], hold[i-1][1] + prices[i]);
            hold[i][0] = Math.max(hold[i-1][0], sold[i-1][0] - prices[i]);
            hold[i][1] = Math.max(hold[i-1][1], sold[i-1][1] - prices[i]);
        }
        return Math.max(Math.max(sold[len-1][0], sold[len-1][1]), sold[len-1][2]);
    }

    /**因为sold[i]和hold[i]只与 sold[i-1]和hold[i-1]的有关，所有可以对space进行优化*/
    public int maxProfit1(int[] prices) {
        if(prices==null||prices.length==0) {
            return 0;
        }
        int len = prices.length;
        int[] sold = new int[3];
        int[] hold = new int[2];
        int[] tmp;
        Arrays.fill(hold, -prices[0]);
        for(int i=1;i<len;i++) {
            tmp = sold;
            sold[0] = sold[0];
            sold[1] = Math.max(sold[1], hold[0] + prices[i]);
            sold[2] = Math.max(sold[2], hold[1] + prices[i]);
            hold[0] = Math.max(hold[0], tmp[0] - prices[i]);
            hold[1] = Math.max(hold[1], tmp[1] - prices[i]);
        }
        return Math.max(Math.max(sold[0], sold[1]), sold[2]);
    }

}
