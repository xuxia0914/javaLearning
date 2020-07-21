package cn.leetcode.xux.lintcode;

/**
 * 191. 乘积最大子序列
 * 中文English
 * 找出一个序列中乘积最大的连续子序列（至少包含一个数）。
 *
 * 样例
 * 样例 1:
 *
 * 输入:[2,3,-2,4]
 * 输出:6
 * 样例 2:
 *
 * 输入:[-1,2,4,1]
 * 输出:8
 * 注意事项
 * 数组长度不超过20000
 * 乘积最大的子序列的积，小于2147483647
 */
public class Lintcode191 {

    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        if(nums==null||nums.length==0) {
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int ans = max;
        for(int i=1;i<nums.length;i++) {
            int tmp1 = nums[i]*max;
            int tmp2 = nums[i]*min;
            max = Math.max(Math.max(max, nums[i]), Math.max(tmp1, tmp2));
            min = Math.min(Math.min(max, nums[i]), Math.min(tmp1, tmp2));
            ans = Math.max(ans, max);
        }
        return ans;
    }

}
