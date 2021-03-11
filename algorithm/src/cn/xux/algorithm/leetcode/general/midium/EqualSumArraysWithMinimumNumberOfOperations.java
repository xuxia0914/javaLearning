package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1775. 通过最少操作次数使数组的和相等
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。
 * 两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，
 * 将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。
 * 如果无法使两个数组的和相等，请返回 -1 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。
 * 以下数组下标都从 0 开始。
 * - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
 *
 * 示例 2：
 * 输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * 输出：-1
 * 解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
 *
 * 示例 3：
 * 输入：nums1 = [6,6], nums2 = [1]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。
 * 以下数组下标都从 0 开始。
 * - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
 * - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
 * - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 105
 * 1 <= nums1[i], nums2[i] <= 6
 */
public class EqualSumArraysWithMinimumNumberOfOperations {

    public int minOperations(int[] nums1, int[] nums2) {
        int[] c1 = new int[7];
        int[] c2 = new int[7];
        int sum1 = 0, sum2 = 0, count = 0;
        if (nums1.length > nums2.length * 6 || nums1.length * 6 < nums2.length) return -1;
        // sum为num总和，c为1-6在num中出现次数
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length; i++, j++) {
            if (i < nums1.length) {
                c1[nums1[i]]++;
                sum1 += nums1[i];
            }
            if (j < nums2.length) {
                c2[nums2[j]]++;
                sum2 += nums2[j];
            }
        }
        // 确保sum1小sum2大
        if (sum1 > sum2) {
            int[] tmp1 = c1;
            c1 = c2;
            c2 = tmp1;
            int tmp2 = sum1;
            sum1 = sum2;
            sum2 = tmp2;
        }
        // 计算达到目标值target需要操作几次
        int target = sum2 - sum1;
        for (int i = 1, j = 6; target > 0 && i < 6 && j > 1; i++, j--) {
            while (c1[i] > 0 || c2[j] > 0) {
                count++;
                if (target -j + 1 > 0) {
                    if (c1[i] > 0) c1[i]--;
                    else c2[j]--;
                    target -= 6 - i;
                } else
                    return count;
            }
        }
        return count;
    }

}
