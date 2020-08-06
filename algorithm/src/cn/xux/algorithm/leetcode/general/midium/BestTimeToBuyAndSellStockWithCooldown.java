package cn.xux.algorithm.leetcode.general.midium;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
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















    public int maxProfit1(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }
        int n = prices.length;
        int[] holds = new int[n];
        int[] solds = new int[n];
        holds[0] = -prices[0];
        holds[1] = Math.max(holds[0], -prices[1]);
        solds[0] = 0;
        solds[1] = Math.max(prices[1]+holds[0], solds[0]);
        for(int i=2;i<n;i++) {
            holds[i] = Math.max(holds[i-1], solds[i-2]-prices[i]);
            solds[i] = Math.max(holds[i-1]+prices[i], solds[i-1]);
        }
        return solds[n-1];
    }

}
