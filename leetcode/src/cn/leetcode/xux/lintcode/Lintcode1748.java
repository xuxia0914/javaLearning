package cn.leetcode.xux.lintcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1748. 最长公共子序列III
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 给出1-n的两个排列P1和P2，求它们的最长公共子序列。请将复杂度控制在O(nlogn)。
 *
 * 样例
 * 样例 1:
 *
 * 输入：[3,2,1,4,5],[1,2,3,4,5]
 * 输出：3
 * 解释：最长公共子序列为[1,4,5]。
 * 样例 2:
 *
 * 输入：[6,9,4,2,8,1,3,5,7],[8,1,2,4,5,3,7,9,6]
 * 输出：4
 * 解释：最长公共子序列为[8,1,3,7]。
 */
public class Lintcode1748 {

    /**
     * @param A:
     * @param B:
     * @return: nothing
     */
    public int longestCommonSubsequenceIII(int[] A, int[] B) {
        // Write your code here
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(A[i], i);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = map.get(B[i]);
        return helper(nums);
    }

    private int helper(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int num : nums) {
            int idx = ub(dp, num);
            dp[idx] = num;
        }
        return ub(dp, Integer.MAX_VALUE);
    }

    private int ub(int[] nums, int num) {
        int l = -1;
        int r = nums.length;
        while (r - l > 1) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= num) r = mid;
            else l = mid;
        }
        return r;
    }

}
