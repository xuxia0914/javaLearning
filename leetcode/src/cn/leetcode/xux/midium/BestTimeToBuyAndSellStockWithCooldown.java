package cn.leetcode.xux.midium;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }
        int len = prices.length;
        int[] sold = new int[len];
        int[] hold = new int[len];
        hold[0] = -prices[0];
        sold[1] = Math.max(sold[0], prices[1]+hold[0]);
        hold[1] = Math.max(hold[0], -prices[1]);
        for(int i=2;i<len;i++) {
            sold[i] = Math.max(sold[i-1], hold[i-1]+prices[i]);
            hold[i] = Math.max(hold[i-1], sold[i-2]-prices[i]);
        }
        return sold[len-1];
    }

}
