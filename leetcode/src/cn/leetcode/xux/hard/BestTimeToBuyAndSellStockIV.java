package cn.leetcode.xux.hard;

import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * Example 1:
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        if(prices==null||prices.length==0||k<1) {
            return 0;
        }
        int len = prices.length;
        if(k>=len) {
            return maxProfit(prices);
        }
        int[] sold = new int[k+1];
        int[] hold = new int[k];
        int[] tmp;
        Arrays.fill(hold, -prices[0]);
        for(int i=1;i<len;i++) {
            tmp = sold;
            for(int j=1;j<k+1;j++) {
                sold[j] = Math.max(sold[j], hold[j-1] + prices[i]);
            }
            for(int j=0;j<k;j++) {
                hold[j] = Math.max(hold[j], tmp[j] - prices[i]);
            }
        }
        int res = sold[0];
        for(int i : sold) {
            res = Math.max(res, i);
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        int maximumProfit = 0;
        int min = prices[0];
        for(int i : prices) {
            if(i>min) {
                maximumProfit += i-min;
            }
            min = i;
        }
        return maximumProfit;
    }

}
