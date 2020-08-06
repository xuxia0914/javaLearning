package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
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
