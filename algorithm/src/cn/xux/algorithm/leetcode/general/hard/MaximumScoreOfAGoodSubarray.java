package cn.xux.algorithm.leetcode.general.hard;

/**
 * 1793. 好子数组的最大分数
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。
 * 一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 * 请你返回 好 子数组的最大可能 分数 。
 *
 * 示例 1：
 * 输入：nums = [1,4,3,7,4,5], k = 3
 * 输出：15
 * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
 *
 * 示例 2：
 * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
 * 输出：20
 * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 2 * 104
 * 0 <= k < nums.length
 */
public class MaximumScoreOfAGoodSubarray {

    public static void main(String[] args) {
        System.out.println(new MaximumScoreOfAGoodSubarray().maximumScore(
                new int[]{1,4,3,7,4,5}, 3
        ));
    }

    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        // min[i]表示 i->k的子数组的最小元素的下标
        int[] min = new int[n];
        min[k] = k;
        for(int i=k-1;i>=0;i--) {
            min[i] = min[i+1];
            if(nums[i]<nums[min[i+1]]) {
                min[i] = i;
            }
        }
        for(int i=k+1;i<n;i++) {
            min[i] = min[i-1];
            if(nums[i]<nums[min[i-1]]) {
                min[i] = i;
            }
        }
        int l = 0;
        int r = n-1;
        int ans = Integer.MIN_VALUE;
        while(l<=k&&r>=k) {
            ans = Math.max(ans, Math.min(nums[min[l]], nums[min[r]])*(r-l+1));
            if(nums[min[l]]<nums[min[r]]) {
                l = min[l]+1;
            }else if(nums[min[l]]>nums[min[r]]) {
                r = min[r]-1;
            }else {
                l = min[l]+1;
                r = min[r]-1;
            }
        }
        return ans;
    }

}
