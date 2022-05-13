package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 2261. 含最多 K 个可整除元素的子数组
 * 给你一个整数数组 nums 和两个整数 k 和 p ，
 * 找出并返回满足要求的不同的子数组数，
 * 要求子数组中最多 k 个可被 p 整除的元素。
 * <p>
 * 如果满足下述条件之一，则认为数组 nums1 和 nums2 是 不同 数组：
 * <p>
 * 两数组长度 不同 ，或者
 * 存在 至少 一个下标 i 满足 nums1[i] != nums2[i] 。
 * 子数组 定义为：数组中的连续元素组成的一个 非空 序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,3,2,2], k = 2, p = 2
 * 输出：11
 * 解释：
 * 位于下标 0、3 和 4 的元素都可以被 p = 2 整除。
 * 共计 11 个不同子数组都满足最多含 k = 2 个可以被 2 整除的元素：
 * [2]、[2,3]、[2,3,3]、[2,3,3,2]、[3]、[3,3]、[3,3,2]、[3,3,2,2]、[3,2]、[3,2,2] 和 [2,2] 。
 * 注意，尽管子数组 [2] 和 [3] 在 nums 中出现不止一次，但统计时只计数一次。
 * 子数组 [2,3,3,2,2] 不满足条件，因为其中有 3 个元素可以被 2 整除。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4], k = 4, p = 1
 * 输出：10
 * 解释：
 * nums 中的所有元素都可以被 p = 1 整除。
 * 此外，nums 中的每个子数组都满足最多 4 个元素可以被 1 整除。
 * 因为所有子数组互不相同，因此满足所有限制条件的子数组总数为 10 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i], p <= 200
 * 1 <= k <= nums.length
 */
public class KDivisibleElementsSubarrays {

    public int countDistinct(int[] nums, int k, int p) {
        int ans = 0;
        //去重使用
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            //保存当前子串中可被 p 整除的元素的数量
            int cnt = 0;
            StringBuilder builder = new StringBuilder();
            for (int j = i; j >= 0; j--) {
                //必须要空格，不然不能去重
                builder.append(nums[j] + " ");
                if (nums[j] % p == 0) cnt++;
                if (cnt <= k) {
                    //使用HashSet进行子数组进行去重
                    if (set.add(builder.toString())) {
                        ans++;
                    }
                } else {
                    break;
                }
            }
        }
        return ans;
    }

}
