package cn.leetcode.xux.general.midium;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected and it will automatically contact the police
 * if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * Example 1:
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        if(nums==null||nums.length<1) {
            return 0;
        }
        int len = nums.length;
        if(len==1) {
            return nums[0];
        }
        int[] nums_clone = nums.clone();
        nums[1] = Math.max(nums[0], nums[1]);
        nums_clone[0] = 0;
        for(int i=2;i<len;i++) {
            nums[i] = Math.max(nums[i-2]+nums[i], nums[i-1]);
            nums_clone[i] = Math.max(nums_clone[i-2]+nums_clone[i], nums_clone[i-1]);
        }
        return Math.max(nums_clone[len-1], Math.max(nums[len-1]-nums[0], nums[len-2]));
    }

}
