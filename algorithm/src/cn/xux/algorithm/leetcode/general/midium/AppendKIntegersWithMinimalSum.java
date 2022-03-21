package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 2195. 向数组中追加 K 个整数
 * 给你一个整数数组 nums 和一个整数 k 。
 * 请你向 nums 中追加 k 个 未 出现在 nums 中的、
 * 互不相同 的 正 整数，并使结果数组的元素和 最小 。
 * <p>
 * 返回追加到 nums 中的 k 个整数之和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,4,25,10,25], k = 2
 * 输出：5
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
 * nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。
 * 示例 2：
 * <p>
 * 输入：nums = [5,6], k = 6
 * 输出：25
 * 解释：在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
 * nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
 * 所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 109
 */
public class AppendKIntegersWithMinimalSum {

    public long minimalKSum(int[] nums, int k) {
        long ans = 0;
        Arrays.sort(nums);
        int pre = 0;
        for (int num : nums) {
            if (num - pre > 1) {
                if (num - pre - 1 >= k) {
                    return ans + (2L * pre + k + 1) * k / 2;
                } else {
                    ans += ((long) pre + num) * (num - pre - 1) / 2;
                    k -= num - pre - 1;
                }
            }
            pre = num;
        }
        return ans + (2L * pre + k + 1) * k / 2;
    }

    public long minimalKSum1(int[] nums, int k) {
        long sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= k) {
                // 去重
                if (i == 0 || nums[i] != nums[i - 1]) {
                    sum += nums[i];
                    k++;
                }
            } else {
                break;
            }
        }
        return (1L + k) * k / 2 - sum;
    }

}
