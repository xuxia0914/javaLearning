package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。
 * 假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 * 示例：
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class LastStoneWeightII {

    // 背包问题
    // 相当于用加号和减号把石头的重量连起来，并使结果最小
    // 目标是求带减号的那拨石头，使其和<=sum/2,并接近于sum/2
    public int lastStoneWeightII(int[] stones) {
        if(stones==null||stones.length==0) {
            return 0;
        }
        int sum = 0;
        for(int stone : stones) {
            sum += stone;
        }
        int cap = sum/2+1;
        int[] dp = new int[cap];
        for (int stone : stones) {
            for (int j = cap - 1; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum-2*dp[cap-1];
    }

    public int lastStoneWeightII1(int[] stones) {
        if(stones==null||stones.length==0) {
            return 0;
        }
        int sum = 0;
        for(int stone : stones) {
            sum += stone;
        }
        int cap = sum/2+1;
        boolean[] dp = new boolean[cap];
        dp[0] = true;
        for(int stone : stones) {
            for(int i=cap-1;i-stone>=0;i--) {
                dp[i] = dp[i]||dp[i-stone];
            }
        }
        for(int i=cap-1;i>=0;i++) {
            if(dp[i]) {
                return sum-2*i;
            }
        }
        return 0;
    }

}
