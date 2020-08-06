package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 877. 石子游戏
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。
 * 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 * 示例：
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *
 * 提示：
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
 */
public class StoneGame {

    //dp
    public boolean stoneGame0(int[] piles) {
        int len = piles.length;
        //表示从i到j堆石头能选到的最打石头数
        int[][] dp = new int[len][len];
        int[] sums = new int[len+1];
        for(int i=1;i<len;i++) {
            sums[i] = piles[i-1]+sums[i-1];
        }
        for(int i=0;i<len-1;i++) {
            dp[i][i+1] = Math.max(piles[i], piles[i+1]);
        }
        for(int n=3;n<=len;n++) {
            for(int i=0;i<=len-n;i++) {
                int total = sums[i+n]-sums[i];
                int takeLeft = total-dp[i+1][i+n-1];
                int takeRight = total-dp[i][i+n-2];
                dp[i][i+n-1] = Math.max(takeLeft, takeRight);
            }
        }
        return dp[0][len]*2>sums[len];
    }

    //递归
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int sum = 0;
        for(int pile : piles) {
            sum += pile;
        }
        return stoneGame(piles, 0, len-1, sum)*2>sum;
    }

    Map<Integer, Integer> mem = new HashMap<>();

    public int stoneGame(int[] piles, int left, int right, int sum) {
        if(right-left==1) {
            return Math.max(piles[left], piles[right]);
        }
        int key = left*piles.length+right;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        int takeLeft = sum-stoneGame(piles, left+1, right, sum-piles[left]);
        int takeRight = sum-stoneGame(piles, left, right-1, sum-piles[right]);
        int result = Math.max(takeLeft, takeRight);
        mem.put(key, result);
        return result;
    }

}
