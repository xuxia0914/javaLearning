package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1695. 删除子数组的最大得分
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。
 * 删除子数组的 得分 就是子数组各元素之 和 。
 * 返回 只删除一个 子数组可获得的 最大得分 。
 * 如果数组 b 是数组 a 的一个连续子序列，
 * 即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 *
 * 示例 1：
 * 输入：nums = [4,2,4,5,6]
 * 输出：17
 * 解释：最优子数组是 [2,4,5,6]
 *
 * 示例 2：
 * 输入：nums = [5,2,1,2,5,2,1,2,5]
 * 输出：8
 * 解释：最优子数组是 [5,2,1] 或 [1,2,5]
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 */
public class MaximumErasureValue {

    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        preSum[0] = nums[0];
        map.put(nums[0], 0);
        int ans = nums[0];
        int left = 0;
        for(int right=1;right<n;right++) {
            preSum[right] = nums[right]+preSum[right-1];
            if(map.containsKey(nums[right])) {
                left = Math.max(left, map.get(nums[right])+1);
            }
            map.put(nums[right], right);
            ans = Math.max(ans, preSum[right]-(left==0?0:preSum[left-1]));
        }
        return ans;
    }

}
