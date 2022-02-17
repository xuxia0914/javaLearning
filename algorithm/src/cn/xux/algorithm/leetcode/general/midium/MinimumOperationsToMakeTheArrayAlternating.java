package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2170. 使数组变成交替数组的最少操作数
 * 给你一个下标从 0 开始的数组 nums ，该数组由 n 个正整数组成。
 * <p>
 * 如果满足下述条件，则数组 nums 是一个 交替数组 ：
 * <p>
 * nums[i - 2] == nums[i] ，其中 2 <= i <= n - 1 。
 * nums[i - 1] != nums[i] ，其中 1 <= i <= n - 1 。
 * 在一步 操作 中，你可以选择下标 i 并将 nums[i] 更改 为 任一 正整数。
 * <p>
 * 返回使数组变成交替数组的 最少操作数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1,3,2,4,3]
 * 输出：3
 * 解释：
 * 使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,1,3,1] 。
 * 在这种情况下，操作数为 3 。
 * 可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,2,2,2]
 * 输出：2
 * 解释：
 * 使数组变成交替数组的方法之一是将该数组转换为 [1,2,1,2,1].
 * 在这种情况下，操作数为 2 。
 * 注意，数组不能转换成 [2,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MinimumOperationsToMakeTheArrayAlternating {

    public int minimumOperations(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return nums[0] == nums[1] ? 1 : 0;
        }
        Map<Integer, Integer> evenMap = new HashMap<>();
        Map<Integer, Integer> oddMap = new HashMap<>();
        for (int i = 0; i + 1 < n; i += 2) {
            evenMap.put(nums[i], evenMap.getOrDefault(nums[i], 0) + 1);
            oddMap.put(nums[i + 1], oddMap.getOrDefault(nums[i + 1], 0) + 1);
        }
        if ((n & 1) == 1) {
            evenMap.put(nums[n - 1], evenMap.getOrDefault(nums[n - 1], 0) + 1);
        }
        int even1 = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        for (Map.Entry<Integer, Integer> entry : evenMap.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            if (v > cnt1) {
                cnt2 = cnt1;
                even1 = k;
                cnt1 = v;
            } else if (v > cnt2) {
                cnt2 = v;
            }
        }
        int odd1 = 0;
        int cnt3 = 0;
        int cnt4 = 0;
        for (Map.Entry<Integer, Integer> entry : oddMap.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            if (v > cnt3) {
                cnt4 = cnt3;
                odd1 = k;
                cnt3 = v;
            } else if (v > cnt4) {
                cnt4 = v;
            }
        }
        if (even1 != odd1) {
            return n - cnt1 - cnt3;
        } else {
            return n - Math.max(cnt1 + cnt4, cnt2 + cnt3);
        }
    }

}
