package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 */
public class JumpGameII {

    public int jump(int[] nums) {
        if(nums==null||nums.length==0) {
            return -1;
        }
        int len = nums.length;
        if(len==1) {
            return 0;
        }
        int step = 0;
        int pos = 0;
        int right = 1;
        while(pos+nums[pos]<len-1) {
            int nextPos = right++;
            while(right<=pos+nums[pos]) {
                if(right+nums[right]>nextPos+nums[nextPos]){
                    nextPos = right;
                }
                right++;
            }
            pos = nextPos;
            step++;
        }
        return step+1;
    }

    public int jump1(int[] nums) {
        if(nums==null||nums.length==0) {
            return -1;
        }
        int len = nums.length;
        if(len==1) {
            return 0;
        }
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<len;i++) {
            for(int j=1;j<=nums[i]&&i+j<len;j++) {
                dp[i+j] = Math.min(dp[i+j], dp[i]+1);
            }
        }
        return dp[len-1];
    }

}
