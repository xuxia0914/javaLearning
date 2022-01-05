package cn.xux.algorithm.leetcode.general.hard;

/**
 * 992. K 个不同整数的子数组
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，
 * 则称 A 的这个连续、不一定不同的子数组为好子数组。
 * <p>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * <p>
 * 返回 A 中好子数组的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1],
 * [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * <p>
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */
public class SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int l1 = 0;
        int l2 = 0;
        // nums[l1~r] 各个整数的出现次数
        int[] freq1 = new int[n + 1];
        // nums[l1~r] 不同整数的个数
        int cnt1 = 0;
        // nums[l2~r] 各个整数的出现次数
        int[] freq2 = new int[n + 1];
        // nums[l2~r] 不同整数的个数
        int cnt2 = 0;
        int ans = 0;
        for (int num : nums) {
            cnt1 += ++freq1[num] == 1 ? 1 : 0;
            cnt2 += ++freq2[num] == 1 ? 1 : 0;
            if (cnt1 < k) {
                continue;
            }
            // l1 移动到第一个使l1~r包含的不同数字的个数等于k的位置
            while (cnt1 > k) {
                cnt1 -= --freq1[nums[l1++]] == 0 ? 1 : 0;
            }
            // l2 移动到第一个使l2~r包含的不同数字的个数等于k-1的位置
            while (cnt2 >= k) {
                cnt2 -= --freq2[nums[l2++]] == 0 ? 1 : 0;
            }
            // [l1~l2-1] ~ r 是好子数组
            ans += l2 - l1;
        }
        return ans;
    }

}
