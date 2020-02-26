package cn.leetcode.xux.midium;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class JumpGame {

    /**
     * Runtime: 1 ms, faster than 99.47% of Java online submissions for Jump Game.
     * Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Jump Game.
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums==null||nums.length==0) {
            return false;
        }
        int n = nums.length;
        int nearly = n-1;
        for(int i=n-2;i>=0;i--) {
            if(nearly-i<=nums[i]) {
                nearly = i;
            }
        }
        return nearly==0;
    }

    /**
     * Runtime: 230 ms, faster than 15.80% of Java online submissions for Jump Game.
     * Memory Usage: 39.5 MB, less than 99.81% of Java online submissions for Jump Game.
     * @param nums
     * @return
     */
    public boolean canJump1(int[] nums) {
        if(nums==null||nums.length<2) {
            return true;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for(int i=0;i<len;i++) {
            if(dp[i]) {
                if(i+nums[i]>=len-1) {
                    return true;
                }else {
                    for(int j=1;j<=nums[i];j++) {
                        dp[i+j] = true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int[]{2,3,1,1,4}));
        System.out.println(new JumpGame().canJump(new int[]{2,2,1,0,4}));
    }

}
