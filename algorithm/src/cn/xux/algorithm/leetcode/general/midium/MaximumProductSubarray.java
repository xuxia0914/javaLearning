package cn.xux.algorithm.leetcode.general.midium;

/**
 * 152. 乘积最大子序列
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        int tmp, curr;
        for(int i=1;i<nums.length;i++) {
            curr = nums[i];
            tmp = max;
            max = Math.max(Math.max(curr, min*curr), max*curr);
            min = Math.min(Math.min(curr, min*curr), tmp*curr);
            res = Math.max(max, res);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
        System.out.println(maxProduct(new int[]{-2,0,-1}));
        System.out.println(maxProduct(new int[]{-4, -3, -2}));
    }

}
