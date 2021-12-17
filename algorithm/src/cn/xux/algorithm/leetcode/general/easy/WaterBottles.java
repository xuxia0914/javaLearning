package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1518. 换酒问题
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * 请你计算 最多 能喝到多少瓶酒。
 * <p>
 * 示例 1：
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 * <p>
 * 示例 2：
 * 输入：numBottles = 15, numExchange = 4
 * 输出：19
 * 解释：你可以用 4 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 15 + 3 + 1 = 19 瓶酒。
 * <p>
 * 示例 3：
 * 输入：numBottles = 5, numExchange = 5
 * 输出：6
 * <p>
 * 示例 4：
 * 输入：numBottles = 2, numExchange = 3
 * 输出：2
 * <p>
 * 提示：
 * 1 <= numBottles <= 100
 * 2 <= numExchange <= 100
 */
public class WaterBottles {

    // 数学法
    public int numWaterBottles(int numBottles, int numExchange) {
        return numBottles + numBottles / (numExchange - 1)
                + (numBottles % (numExchange - 1) == 0 ? -1 : 0);
    }

    // 模拟
    public int numWaterBottles1(int numBottles, int numExchange) {
        int n = numBottles;
        int ans = n;
        while (n >= numExchange) {
            ans += n / numExchange;
            n = (n / numExchange) + (n % numExchange);
        }
        return ans;
    }

}
