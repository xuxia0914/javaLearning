package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 486. 预测赢家
 * 给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，
 * 然后玩家1拿，……。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。
 * 最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * 示例 1:
 * 输入: [1, 5, 2]
 * 输出: False
 * 解释: 一开始，玩家1可以从1和2中进行选择。
 * 如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
 * 所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
 * 因此，玩家1永远不会成为赢家，返回 False。
 *
 * 示例 2:
 * 输入: [1, 5, 233, 7]
 * 输出: True
 * 解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
 * 最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
 *
 * 注意:
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于10000000。
 * 如果最终两个玩家的分数相等，那么玩家1仍为赢家。
 */
public class PredictTheWinner {

    //dp
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        int[] sums = new int[len+1];
        sums[0] = 0;
        for(int i=0;i<len;i++) {
            dp[i][i] = nums[i];
            sums[i+1] = sums[i]+nums[i];
        }
        for(int i=1;i<len;i++) {
            for(int j=0;j+i<len;j++) {
                int sum = sums[j+i+1]-sums[j];
                dp[j][j+i] = Math.max(sum-dp[j+1][j+i], sum-dp[j][j+i-1]);
            }
        }
        return dp[0][len-1]*2>=sums[len];
    }

    //递归
    Map<String, Integer> mem = new HashMap<>();
    public boolean PredictTheWinner1(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        return mostScore(nums, 0, len-1, sum)*2>=sum;
    }

    public int mostScore(int[] nums, int left, int right, int total) {
        if(left==right) {
            return nums[left];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(left).append('#').append(right);
        String key = sb.toString();
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        int chooseLeft = total - mostScore(nums, left+1, right, total-nums[left]);
        int chooseRight = total - mostScore(nums, left, right-1, total-nums[right]);
        int result = Math.max(chooseLeft, chooseRight);
        mem.put(key, result);
        return result;
    }

}
