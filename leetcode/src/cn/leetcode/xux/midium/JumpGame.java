package cn.leetcode.xux.midium;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
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
